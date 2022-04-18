package com.example.onlineschoolcourses.ui.screen.ui.fragment.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineschoolcourses.R
import com.example.onlineschoolcourses.base.BaseFragment
import com.example.onlineschoolcourses.databinding.FragmentHomeBinding
import com.example.onlineschoolcourses.ui.screen.ui.fragment.home.adapter.HomeAdapter
import com.example.onlineschoolcourses.ui.screen.ui.fragment.home.viewModel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
   lateinit var binding:FragmentHomeBinding
   val viewModel :HomeFragmentViewModel by viewModels()
    val adapter=HomeAdapter()
   override var bottomNavigationViewVisibility =View.VISIBLE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
initAdapter()
    }

  private fun  initAdapter() {
      binding.recyclerView.adapter=adapter
      binding.recyclerView.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)


      lifecycleScope.launch {
          viewModel.usersMaterialsData.collectLatest {
              Log.d("neew",it.size.toString())
         adapter.update(it)
          }
      }



    }

}