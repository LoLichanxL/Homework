package com.example.homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.adapter.FilterAdapter
import com.example.homework.model.Data
import com.example.homework.presenter.MainActivityPresenter


class FilterFragment(val mainActivity: MainActivity) : Fragment(), Contract.FilterFragmentView {
    lateinit var presenter: MainActivityPresenter
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FilterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        presenter = MainActivityPresenter(mainActivity)
        adapter = FilterAdapter(presenter.onCreateFilterRecycler(), presenter, Data.checked)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_filter, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.filter_recycler_view)
        createRecyclerView(presenter.onCreateFilterRecycler())
        view.findViewById<Button>(R.id.accept_button).setOnClickListener(View.OnClickListener {
            presenter.onFilterFragmentAcceptButtonPressed()
        })
        view.findViewById<ImageView>(R.id.filter_fragment_arrow_back)
            .setOnClickListener(View.OnClickListener {
                presenter.onFilterFragmentArrowBackPressed()
            })
        return view
    }

    override fun createRecyclerView(data: List<String>) {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
    }

}