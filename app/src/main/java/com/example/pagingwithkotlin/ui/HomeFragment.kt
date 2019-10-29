package com.example.pagingwithkotlin.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagingwithkotlin.R
import com.example.pagingwithkotlin.adapter.GitRepoAdapter
import com.example.pagingwithkotlin.viewmodel.GitViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(GitViewModel::class.java)

        rv_list.layoutManager = LinearLayoutManager(context)
        var adapter = GitRepoAdapter()

        viewModel.gitPagedList.observe(this, Observer{
            adapter.submitList(it)
        })
        rv_list.adapter = adapter
    }

}
