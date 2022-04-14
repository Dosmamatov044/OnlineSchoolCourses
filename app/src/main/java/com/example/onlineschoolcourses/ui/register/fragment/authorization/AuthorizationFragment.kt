package com.example.onlineschoolcourses.ui.register.fragment.authorization

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.example.onlineschoolcourses.base.BaseFragment
import com.example.onlineschoolcourses.databinding.FragmentAuthorizationBinding
import com.example.onlineschoolcourses.helpers.replaceFragmentScreen

import com.example.onlineschoolcourses.helpers.showToast
import com.example.onlineschoolcourses.ui.register.fragment.authorization.viewModel.AuthorizationViewModel
import com.example.onlineschoolcourses.ui.register.fragment.forgetPassword.ForgetPasswordFragment
import com.example.onlineschoolcourses.ui.register.fragment.register.RegisterFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationFragment : BaseFragment() {
lateinit var binding:FragmentAuthorizationBinding
val viewModel:AuthorizationViewModel by viewModels ()

    override var bottomNavigationViewVisibility =View.GONE
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentAuthorizationBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
initView()


    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun initView() {


        binding.entryBtnAuthorization.setOnClickListener {
            val email= binding.emailEditAuthorization.text.toString()
            val password=binding.passwordEditAuthorization.text.toString()
            if (email.isNotEmpty()&&password.isNotEmpty()) {
                viewModel.entrance(email, password)
            }else{
                this.activity?.showToast("Заполните поля")
            }
        }
    binding.registerTextAuthorization.setOnClickListener {
        replaceFragmentScreen(RegisterFragment())

    }

     binding.forgetPasswordTxtAuthorization.setOnClickListener {
         replaceFragmentScreen(ForgetPasswordFragment())
     }


    }

}