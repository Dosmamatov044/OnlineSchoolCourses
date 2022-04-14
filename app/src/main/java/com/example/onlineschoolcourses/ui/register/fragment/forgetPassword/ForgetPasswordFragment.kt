package com.example.onlineschoolcourses.ui.register.fragment.forgetPassword

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.example.onlineschoolcourses.R
import com.example.onlineschoolcourses.base.BaseFragment
import com.example.onlineschoolcourses.databinding.FragmentForgetPasswordBinding
import com.example.onlineschoolcourses.helpers.reverseTimer
import com.example.onlineschoolcourses.helpers.showToast
import com.example.onlineschoolcourses.ui.register.fragment.forgetPassword.viewModel.ForgetPasswordViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.system.measureTimeMillis

@AndroidEntryPoint
class ForgetPasswordFragment : BaseFragment() {
    lateinit var binding:FragmentForgetPasswordBinding
   val viewModels:ForgetPasswordViewModel by viewModels()
    override var bottomNavigationViewVisibility =View.GONE
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding= FragmentForgetPasswordBinding.inflate(inflater,container,false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.getLinkBtnForgetPassword.setOnClickListener {
            val email=binding.emailEditForgetPassword.text.toString()

            if (email.isNotEmpty()) {
                viewModels.forgotPassword(email)

            binding.progressTxtForgetPassword.text= reverseTimer(requireActivity(),binding.progressTxtForgetPassword,binding.getLinkBtnForgetPassword).text.toString()
            }else{
                this.activity?.showToast("Заполните поле")

            }



        }


    }

}