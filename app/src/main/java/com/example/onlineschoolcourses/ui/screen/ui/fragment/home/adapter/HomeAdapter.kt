package com.example.onlineschoolcourses.ui.screen.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineschoolcourses.R

class HomeAdapter:RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var list= mutableListOf<String>()






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.home_item,parent,false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ViewHolder(view:View):RecyclerView.ViewHolder(view){



    }

}