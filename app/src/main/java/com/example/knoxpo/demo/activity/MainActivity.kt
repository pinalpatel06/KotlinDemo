package com.example.knoxpo.demo.activity

import android.support.v4.app.Fragment
import com.example.knoxpo.demo.fragment.MainFragment

class MainActivity : ToolbarActivity() {

    override fun getContentFragment(): Fragment {
        return MainFragment()
    }
}
