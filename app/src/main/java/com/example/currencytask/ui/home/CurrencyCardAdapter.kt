package com.example.currencytask.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.currencytask.R
import com.example.currencytask.models.Currency

class CurrencyCardAdapter :
    ListAdapter<Currency, CurrencyCardAdapter.ViewHolder>(CurrencyDiffCallBack()) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val currencyValue = view.findViewById<TextView>(R.id.currencyValue)
        val currencyDate = view.findViewById<TextView>(R.id.currencyDate)

        fun bind(currency: Currency) {
            currencyValue.text = currency.value.toString()
            currencyDate.text = currency.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.currency_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class CurrencyDiffCallBack : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean =
            oldItem == newItem
    }
}