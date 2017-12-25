package com.example.knoxpo.demo.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.knoxpo.demo.R
import com.example.knoxpo.demo.activity.DetailActivity
import com.example.knoxpo.demo.model.Post
import com.example.knoxpo.demo.retrofitPkg.RetrofitHelper
import com.example.knoxpo.demo.retrofitPkg.RetrofitInterface
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by knoxpo on 25/12/17.
 */
class MainFragment : Fragment() {

    var postList = ArrayList<Post>()

    lateinit var recyclerView: RecyclerView

    private fun getList(): ArrayList<String> {
        val titles = ArrayList<String>()
        titles.add("ABC")
        titles.add("XYZ")
        titles.add("PQR")
        return titles
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofitInterface = RetrofitHelper.getRetrofit().create(RetrofitInterface::class.java)
        val single: Single<Post> = retrofitInterface.getPost(5)

        single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    t1->
                    postList.add(t1)
                    Log.d("data", postList.get(0).toString())
                    recyclerView.adapter.notifyDataSetChanged()
                }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.fragment_main, container, false)

        recyclerView = view.findViewById(R.id.rv_list)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        recyclerView.adapter = Adaper(postList)
        return view
    }


    class Adaper(val list: ArrayList<Post>) : RecyclerView.Adapter<AdapterVH>() {

        override fun onBindViewHolder(holder: AdapterVH, position: Int) {
            holder.bind(list[position])
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVH {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
            return AdapterVH(v, parent.context)
        }
    }

    class AdapterVH(itemView: View?, context: Context) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        lateinit var mPost: Post

        override fun onClick(p0: View?) {
            val intent = Intent(mContext, DetailActivity::class.java)
            intent.putExtra(DetailActivity().EXTRA_TITLE,mPost.body)
            mContext.startActivity(intent)
        }

        lateinit var titleTV: TextView
        val mContext: Context
        init {
            mContext = context
        }

        fun bind(post: Post) {
            mPost = post
            Log.d("On Bind", post.mTitle)
            titleTV = itemView.findViewById<TextView>(R.id.tv_title)
            titleTV.setOnClickListener(this)
            titleTV.text = post.mTitle
        }
    }
}