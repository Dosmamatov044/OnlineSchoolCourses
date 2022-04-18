package com.example.onlineschoolcourses.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.ColorSpace
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.onlineschoolcourses.R
import com.example.onlineschoolcourses.databinding.ActivityMainBinding
import com.example.onlineschoolcourses.ui.screen.ui.fragment.certificate.signatureHelpers.MySignatureView
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.innerFragment.additionalMaterials.model.UserModels
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import org.w3c.dom.Text

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
var models:UserModels?=null
    private lateinit var binding: ActivityMainBinding
    // lateinit var mySignatureView: MySignatureView

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val database = FirebaseDatabase.getInstance()
        //  mySignatureView= findViewById(R.id.mySignatureView) as MySignatureView



    }

}















