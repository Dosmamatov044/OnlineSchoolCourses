package com.example.onlineschoolcourses.ui.screen.ui.fragment.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlineschoolcourses.R
import com.example.onlineschoolcourses.base.BaseFragment
import com.example.onlineschoolcourses.contract.HasCustomTitle


class SearchFragment : BaseFragment(), HasCustomTitle {

    override var bottomNavigationViewVisibility =View.VISIBLE
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun getTitleRes(): Int=R.string.search

}