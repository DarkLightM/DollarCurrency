package com.example.currencytask.ui.home

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.currencytask.MainActivity
import com.example.currencytask.MainApplication
import com.example.currencytask.R
import com.example.currencytask.api.repository.shared_prefs.ISharedPrefsRepository
import com.example.currencytask.api.usecase.IGetCurrencyListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NotificationService : Service() {

    @Inject
    lateinit var iGetCurrencyListUseCase: IGetCurrencyListUseCase

    @Inject
    lateinit var iSharedPrefsRepository: ISharedPrefsRepository

    private lateinit var notificationManager: NotificationManager
    private val serviceScope = CoroutineScope(Dispatchers.Default)
    private val currencyId = "R01235"
    private val channelId = "12345"

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        MainApplication.appComponent.inject(this)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        sendNotification()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startScheduler()
        return START_STICKY
    }

    private fun startScheduler() {
        val service = Executors.newSingleThreadScheduledExecutor()
        val handler = Handler(Looper.getMainLooper())
        service.scheduleAtFixedRate({
            handler.run {
                serviceScope.launch {
                    val defaultCurrency = iSharedPrefsRepository.getDefaultCurrency()
                    Log.e("Default currency", defaultCurrency)
                    val dateTo = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    val dateFrom =
                        LocalDate.now().plusMonths(-1)
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                    val currencyList = iGetCurrencyListUseCase.invoke(dateFrom, dateTo, currencyId)

                    if (currencyList.records?.reversed()?.get(0)?.value?.let {
                            compareStringsAsNumbers(
                                it,
                                defaultCurrency
                            )
                        } == 1
                    ) sendNotification()
                }
            }
        }, 0, 24, TimeUnit.HOURS)
    }

    private fun sendNotification() {
        val intent = Intent(this, MainActivity::class.java)
        intent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val notificationId = 101
        val channelName = "Currency Update"

        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance)
        notificationManager.createNotificationChannel(channel)

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_dollar)
            .setContentTitle("Изменение цены")
            .setContentText("Цена поднялась")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)

        startForeground(notificationId, builder.build())
    }

    private fun compareStringsAsNumbers(str1: String, str2: String): Int {
        val number1 = str1.replace(",", ".").toDouble()
        val number2 = str2.replace(",", ".").toDouble()
        Log.e("First number", number1.toString())
        Log.e("Second number", number2.toString())

        return when {
            number1 < number2 -> -1
            number1 > number2 -> 1
            else -> 0
        }
    }
}
