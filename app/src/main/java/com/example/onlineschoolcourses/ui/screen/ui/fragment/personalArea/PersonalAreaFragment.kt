package com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineschoolcourses.base.BaseFragment
import com.example.onlineschoolcourses.databinding.FragmentPersonalAreaBinding
import com.example.onlineschoolcourses.helpers.replaceFragmentScreen
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.adaper.PersonalAreaMyCourseAdapter
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.innerFragment.additionalMaterials.AdditionOfFoodMaterialsFragment
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.innerFragment.updateInformation.UpdateInformationAboutUserFragment
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.viewModel.PersonalAreaViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PersonalAreaFragment : BaseFragment() {
    lateinit var binding: FragmentPersonalAreaBinding
    val viewModel: PersonalAreaViewModel by viewModels()
    val adapter = PersonalAreaMyCourseAdapter()
    override var bottomNavigationViewVisibility =View.VISIBLE
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonalAreaBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initAdapter()




        lifecycleScope.launch {
            viewModel.usersMaterialsData.collectLatest {

                adapter.update(it)

            }

        }


    }
    private fun initView() {
        binding.addCourse.setOnClickListener {
            replaceFragmentScreen(AdditionOfFoodMaterialsFragment())

        }

        binding.updateUserInfo.setOnClickListener {
            replaceFragmentScreen(UpdateInformationAboutUserFragment())

        }
    }



    private fun initAdapter() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

    }
}