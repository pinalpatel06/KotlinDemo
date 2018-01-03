package com.example.knoxpo.demo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.knoxpo.demo.R
import com.example.knoxpo.demo.activity.MainActivity
import com.example.knoxpo.demo.databinding.FragmentMainBinding

/**
 * Created by knoxpo on 25/12/17.
 */
class MainFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mFragmentBinding: FragmentMainBinding
    private lateinit var mAdapter: PostAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter(){
        val viewModel = mFragmentBinding.viewmodel
        if(viewModel != null){
            mAdapter = PostAdapter(ArrayList(0))
            mFragmentBinding.rvList.adapter = mAdapter
        }else{
            Log.d("Main Fragment", "ViewModel not initialized when attempting to set up adapter.")
        }

    }

    override fun onResume() {
        super.onResume()
        mFragmentBinding.viewmodel?.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mFragmentBinding = FragmentMainBinding.inflate(inflater,container,false).apply {
            viewmodel = (activity as MainActivity).obtainViewModel()
        }

        val view = mFragmentBinding.root

        mRecyclerView = view.findViewById(R.id.rv_list)
        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        return view
    }
}