package com.example.knoxpo.demo.fragment


import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.knoxpo.demo.data.model.Post
import com.example.knoxpo.demo.databinding.ItemTaskBinding

/**
 * Created by knoxpo on 3/1/18.
 */
class PostAdapter(
        private var posts: List<Post>,
        private val viewModel: PostViewModel

): RecyclerView.Adapter<AdapterVH>() {
    private lateinit var binding : ItemTaskBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterVH {
        val inflator = LayoutInflater.from(parent.context)

        binding = ItemTaskBinding.inflate(inflator, parent, false)
        return AdapterVH(binding, binding.root, viewModel)
    }

    override fun onBindViewHolder(holder: AdapterVH?, position: Int) {
        Log.d("On Bind: ", position.toString())
        /*with(binding){
            post = posts[position]
            listener = userActionListener
            executePendingBindings()
        }*/

        holder?.bind(posts[position])
    }

    override fun getItemCount(): Int {
        Log.d("From Adapter: ", posts.size.toString())
        return posts.size
    }

    private fun setList(list: List<Post>){
        this.posts = list
        notifyDataSetChanged()
    }

    fun replaceData(list: List<Post>){
        setList(list)
    }
}

class AdapterVH(binding: ItemTaskBinding?, itemView: View, val viewModel: PostViewModel) : RecyclerView.ViewHolder(itemView) {
    private val mBinding = binding

    fun  bind(post: Post){
        Log.d("bind: ", post.toString())
        val userActionListener = object :PostItemUserActionListener{
            override fun onPostClicked(post: Post) {
                viewModel.openPostEvent.setValue(post)
            }
        }

        mBinding?.post = post
        mBinding?.listener = userActionListener
        mBinding?.executePendingBindings()
    }
}