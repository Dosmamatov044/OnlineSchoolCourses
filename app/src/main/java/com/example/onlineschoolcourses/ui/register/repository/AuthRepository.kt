package com.example.onlineschoolcourses.ui.register.repository

import android.app.Application
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.example.onlineschoolcourses.helpers.auth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class AuthRepository @Inject constructor(private val application: Application) {

    var userMutableLiveData: MutableLiveData<FirebaseUser>

    init {
        auth = Firebase.auth
        userMutableLiveData = MutableLiveData()
    }


    @RequiresApi(Build.VERSION_CODES.P)
    fun register(password: String, email: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(application.mainExecutor) { task ->
                if (task.isSuccessful) {

                    userMutableLiveData.postValue(auth.currentUser)

                } else {
                    Log.d("Ошибка файрбейз", task.exception.toString())

                    Toast.makeText(
                        application, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }


    }


    @RequiresApi(Build.VERSION_CODES.P)
    fun entrance(password: String, email: String) {

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(application.mainExecutor) { task ->
                if (task.isSuccessful) {

                    userMutableLiveData.postValue(auth.currentUser)

                } else {
                    Log.d("Ошибка файрбейз", task.exception.toString())

                    Toast.makeText(
                        application, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
    }

    fun forgetPassword(text: String) {
        auth.sendPasswordResetEmail(text).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(
                    application,
                    "Мы отправили сообщение для сброса пароля",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                Toast.makeText(application, "Провал", Toast.LENGTH_LONG).show()

            }

        }

    }


}