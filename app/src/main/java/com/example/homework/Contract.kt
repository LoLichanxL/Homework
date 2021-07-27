package com.example.homework

import com.example.homework.model.Item

interface Contract {
    interface FilterFragmentView {
        fun createRecyclerView(data: List<String>)
    }

    interface MainActivityView {
        fun createRecyclerView(data: List<Item>)
    }

    interface Presenter {
        fun onGitHubButtonPressed()
        fun onFilterButtonPressed()
        fun onFilterFragmentArrowBackPressed()
        fun onFilterFragmentAcceptButtonPressed()
        fun onCreateRecyclerView(): List<Item>
        fun onCreateFilterRecycler(): List<String>
    }
}