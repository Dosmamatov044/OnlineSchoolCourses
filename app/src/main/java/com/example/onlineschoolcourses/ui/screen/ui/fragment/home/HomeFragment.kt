package com.example.onlineschoolcourses.ui.screen.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.onlineschoolcourses.R
import com.example.onlineschoolcourses.base.BaseFragment
import com.example.onlineschoolcourses.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment() {
   lateinit var binding:FragmentHomeBinding
    override var bottomNavigationViewVisibility =View.VISIBLE
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }


}