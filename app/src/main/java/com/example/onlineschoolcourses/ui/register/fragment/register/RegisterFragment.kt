package com.example.onlineschoolcourses.ui.register.fragment.register

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.example.onlineschoolcourses.base.BaseFragment
import com.example.onlineschoolcourses.databinding.FragmentRegisterBinding
import com.example.onlineschoolcourses.helpers.showToast
import com.example.onlineschoolcourses.ui.register.fragment.register.viewModel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment() {
    lateinit var binding: FragmentRegisterBinding
    val viewModel: RegisterViewModel by viewModels()

    override var bottomNavigationViewVisibility =View.GONE
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.getLinkBtnRegister.setOnClickListener {
            val email = binding.emailEditRegister.text.toString()
            val password = binding.passwordEditRegister.text.toString()
            val confirmPassword = binding.confirmPasswordEditRegister.text.toString()
            val name = binding.nameEditRegister.text.toString()
            val surname = binding.surnameEditRegister.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword == password && name.isNotEmpty() && surname.isNotEmpty()) {
                viewModel.register(password, email)
                viewModel.userMutableLiveData.observe(viewLifecycleOwner) {
                    viewModel.uploadDatabase(name, surname, email,it)
                    activity?.showToast(it.toString())
                }


            } else {
                if (confirmPassword != password && password.isNotEmpty() && confirmPassword.isNotEmpty() && email.isNotEmpty()) {
                    this.activity?.showToast("Пароли не совпадают")

                } else {
                    this.activity?.showToast("Заполните поля")
                }
            }
        }


    }


}