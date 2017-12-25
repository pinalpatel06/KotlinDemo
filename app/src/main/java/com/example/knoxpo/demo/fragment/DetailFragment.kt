package com.example.knoxpo.demo.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.knoxpo.demo.R

/**
 * Created by knoxpo on 25/12/17.
 */
class DetailFragment : Fragment() {
    private val ARGS_TITLE: String = "DetailFragment.ARGS_TITLE"

    companion object {
        fun newInstance(str: String): DetailFragment{
            val args = Bundle()
            val fragment = DetailFragment()
            args.putString(fragment.ARGS_TITLE,str)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_detail, container,false)

        val args: Bundle = arguments
        val textTV = view.findViewById<TextView>(R.id.tv_detail_title)

        val str: String = args.getString(ARGS_TITLE)
        if (!str.isEmpty()){
            textTV.text = str
        }

        return  view
    }
}