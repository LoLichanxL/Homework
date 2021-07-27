package com.example.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.adapter.ItemsAdapter
import com.example.homework.model.Item
import com.example.homework.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity(), Contract.MainActivityView {
    lateinit var recyclerView: RecyclerView
    lateinit var mainScreen: ConstraintLayout
    lateinit var filterFragmentScreen: FrameLayout
    lateinit var fragment: FilterFragment
    val presenter: MainActivityPresenter = MainActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragment = FilterFragment(this)
        mainScreen = findViewById(R.id.main_screen)
        filterFragmentScreen = findViewById(R.id.fragment_container)
        createRecyclerView(presenter.onCreateRecyclerView())
    }

    override fun createRecyclerView(data: List<Item>) {
        recyclerView = findViewById(R.id.main_screen_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = ItemsAdapter(data, this, presenter)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

    }
}