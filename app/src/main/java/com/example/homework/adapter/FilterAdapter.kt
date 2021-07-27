package com.example.homework.adapter

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R
import com.example.homework.model.Data
import com.example.homework.presenter.MainActivityPresenter
import com.google.android.material.snackbar.Snackbar
import java.util.*
import java.util.zip.Inflater

class FilterAdapter(
    val data: List<String>,
    val presenter: MainActivityPresenter,
    var checked: BooleanArray
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_filter, parent, false)
        return ItemsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemsViewHolder = holder as ItemsViewHolder
        if (checked[position])
            itemsViewHolder.checkBox.isChecked = true
        else
            itemsViewHolder.checkBox.isChecked = false
        itemsViewHolder.filter.setText(data[position])
        itemsViewHolder.checkBox.setOnClickListener(View.OnClickListener {
            changeCheckBoxValue(position)
            if (position == 0 && checked[0]) {
                for (i in 0..checked.size - 1) {
                    checked[i] = true
                    Log.d("CheckBox", checked[i].toString())
                    Log.d("CheckBox", i.toString())
                }
            }
            if (position == 0 && checked[0] == false) {
                for (i in 0..checked.size - 1) {
                    checked[i] = false
                }
            }
            if (position != 0) {
                for (i in 0..checked.size - 1) {
                    if (checked[i] == false) {
                        checked[0] = false
                        Log.d("CheckBox", checked[i].toString())
                    }
                }
            }
            notifyDataSetChanged()
        })
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun changeCheckBoxValue(position: Int) {
        if (checked[position] == true)
            checked[position] = false
        else
            checked[position] = true
    }

    class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.filter_checkbox)
        val filter: TextView = itemView.findViewById(R.id.filter_text_view)
    }
}