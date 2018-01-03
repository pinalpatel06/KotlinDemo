package com.example.knoxpo.demo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.example.knoxpo.demo.R

/**
 * Created by knoxpo on 25/12/17.
 */
abstract class SingleFragmentActivity : AppCompatActivity() {

    abstract fun getContentFragment() : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewId())
        val fm: FragmentManager = supportFragmentManager
        val fragment: Fragment? = fm.findFragmentById(getContainerId())

        if(fragment == null){
            fm
                    .beginTransaction()
                    .add(getContainerId(),getContentFragment())
                    .commit()
        }
    }

   open protected fun getContentViewId() : Int{
        return R.layout.activity_single_fragment
    }

    open protected fun getContainerId(): Int{
        return R.id.fragment_container
    }
}