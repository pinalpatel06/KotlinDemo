package com.example.knoxpo.demo

import android.support.annotation.NonNull
import android.support.v4.app.Fragment

/**
 * Created by knoxpo on 2/1/18.
 */
class ViewModelHolder<VM: Any> : Fragment() {

    lateinit var mViewHolder: VM

    companion object {
        fun <M> createContainer(@NonNull viewModel: Any) : ViewModelHolder<Any>{
            val viewModelContainer =  ViewModelHolder<Any>()
            viewModelContainer.mViewHolder = viewModel
            return viewModelContainer
        }
    }

}