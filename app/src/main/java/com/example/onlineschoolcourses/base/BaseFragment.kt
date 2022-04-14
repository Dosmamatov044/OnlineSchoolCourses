package com.example.onlineschoolcourses.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

import com.example.onlineschoolcourses.ui.screen.ui.activity.ScreenActivity

abstract class BaseFragment : Fragment() {

    protected open var bottomNavigationViewVisibility = View.VISIBLE


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity is ScreenActivity) {
            val screenActivity = activity as ScreenActivity
            screenActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
        }

    }

    override fun onResume() {
        super.onResume()
        if (activity is ScreenActivity) {
            val screenActivity = activity as ScreenActivity
            screenActivity.setBottomNavigationVisibility(bottomNavigationViewVisibility)
        }
    }
}