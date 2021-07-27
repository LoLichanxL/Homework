package com.example.homework.presenter

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework.Contract
import com.example.homework.FilterFragment
import com.example.homework.MainActivity
import com.example.homework.R
import com.example.homework.adapter.ItemsAdapter
import com.example.homework.model.Data
import com.example.homework.model.Item

class MainActivityPresenter(val mainActivity: MainActivity) : Contract.Presenter {
    val filterFragment: FilterFragment = FilterFragment(mainActivity)
    lateinit var copyOfArray: BooleanArray
    override fun onGitHubButtonPressed() {
        val url: String = Data.GITHUB_URL
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        mainActivity.startActivity(i)
    }

    override fun onFilterButtonPressed() {
        mainActivity.supportFragmentManager.beginTransaction().replace(mainActivity.filterFragmentScreen.id, filterFragment)
                .addToBackStack("MainActivity").commit()
        mainActivity.mainScreen.visibility = View.GONE
        mainActivity.filterFragmentScreen.visibility = View.VISIBLE
    }

    override fun onFilterFragmentArrowBackPressed() {
        mainActivity.supportFragmentManager.beginTransaction().hide(mainActivity.fragment).commit()
        mainActivity.mainScreen.visibility = View.VISIBLE
        mainActivity.filterFragmentScreen.visibility = View.GONE
        mainActivity.recyclerView.adapter = ItemsAdapter(Data.getData(), mainActivity, this)
    }

    override fun onFilterFragmentAcceptButtonPressed() {
        mainActivity.supportFragmentManager.beginTransaction().hide(mainActivity.fragment).commit()
        mainActivity.mainScreen.visibility = View.VISIBLE
        mainActivity.filterFragmentScreen.visibility = View.GONE
        mainActivity.recyclerView.adapter = ItemsAdapter(Data.getData(), mainActivity, this)
    }

    override fun onCreateRecyclerView(): List<Item> {
        return Data.getData()
    }

    override fun onCreateFilterRecycler(): List<String> {
        return Data.getFilters()
    }


}