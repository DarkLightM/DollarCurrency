package com.example.currencytask

import android.Manifest.*
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.currencytask.databinding.ActivityMainBinding
import com.example.currencytask.ui.home.HomeFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val notificationPermissionCode = 123
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        MainApplication.appComponent.inject(this)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationPolicyAccessGranted = notificationManager.isNotificationPolicyAccessGranted

        if (!notificationPolicyAccessGranted) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestNotificationPermission()
            }
        }
        val container = binding.container
        val homeFragment = HomeFragment()

        supportFragmentManager.beginTransaction()
            .replace(container.id, homeFragment)
            .commit()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        if (ContextCompat.checkSelfPermission(this, permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(permission.POST_NOTIFICATIONS),
                notificationPermissionCode
            )
        }
    }
}