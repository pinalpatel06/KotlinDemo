package com.example.knoxpo.demo.fragment

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
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by knoxpo on 25/12/17.
 */
class MainFragment : Fragment() {

    private var mPostList = ArrayList<Post>()
    private lateinit var mRecyclerView: RecyclerView

   inline fun <T> Single<T>.addData(
            func: Single<T>.() -> Unit
    ) {
        val single = this
        single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .func()
    }

    private fun getList() {
        /* val titles = ArrayList<String>()
         titles.add("ABC")
         titles.add("XYZ")
         titles.add("PQR")
         return titles*/

        val retrofitInterface = RetrofitHelper.getRetrofit().create(RetrofitInterface::class.java)
        val single: Single<Post> = retrofitInterface.getPost(5)


        single.apply {
            addData {
                this.subscribe(object : SingleObserver<Post> {
                    override fun onSuccess(value: Post) {
                        mPostList.add(value)
                        Log.d("data", mPostList.get(0).toString())
                        mRecyclerView.adapter.notifyDataSetChanged()
                    }

                    override fun onError(e: Throwable) {
                        Log.d("Error: ", e.message)
                    }

                    override fun onSubscribe(d: Disposable?) {

                    }
                })
            }
        }


        /*single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())*/
                /*.subscribe { t1 ->
                    mPostList.add(t1)
                    Log.d("data", mPostList.get(0).toString())
                    mRecyclerView.adapter.notifyDataSetChanged()

                }*/
                /*.subscribe {
                    t1,
                    t2 ->

                    if(t1 != null) {
                        mPostList.add(t1)
                        Log.d("data", mPostList.get(0).toString())
                        mRecyclerView.adapter.notifyDataSetChanged()
                    }else{
                        Log.d("Error: ", t2.message)
                    }
                }*/
                /*.subscribe(object : SingleObserver<Post> {
                    override fun onSuccess(value: Post) {
                        //val post = Post(1,1,"ABC","dfhdhfjdbjsdfhjdf ")
                        mPostList.add(value)
                        Log.d("data", mPostList.get(0).toString())
                        mRecyclerView.adapter.notifyDataSetChanged()
                    }

                    override fun onError(e: Throwable) {
                        Log.d("Error: ", e.message)
                    }

                    override fun onSubscribe(d: Disposable?) {

                    }
                })*/


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getList()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater!!.inflate(R.layout.fragment_main, container, false)

        mRecyclerView = view.findViewById(R.id.rv_list)
        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
        mRecyclerView.adapter = Adaper()
        return view
    }

    //inner is use to access outer class member like mPostList
    inner class Adaper() : RecyclerView.Adapter<AdapterVH>() {

        override fun onBindViewHolder(holder: AdapterVH, position: Int) {
            holder.bind(mPostList[position])
        }

        override fun getItemCount(): Int {
            return mPostList.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVH {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
            return AdapterVH(v)
        }
    }

    inner class AdapterVH(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        lateinit var mPost: Post
        private lateinit var titleTV: TextView

        override fun onClick(p0: View?) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_TITLE, mPost)
            activity.startActivity(intent)
        }

        fun bind(post: Post) {
            mPost = post
            Log.d("On Bind", post.getPostUserId().toString())
            titleTV = itemView.findViewById(R.id.tv_title)
            titleTV.setOnClickListener(this)
            titleTV.text = post.getPostTitle()
        }
    }
}