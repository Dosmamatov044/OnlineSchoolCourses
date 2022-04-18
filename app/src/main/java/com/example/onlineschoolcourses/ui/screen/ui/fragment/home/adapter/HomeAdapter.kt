package com.example.onlineschoolcourses.ui.screen.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineschoolcourses.R
import com.example.onlineschoolcourses.ui.screen.ui.fragment.home.model.CommonModel

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var list = mutableListOf<CommonModel>()

fun update(list: MutableList<CommonModel>){
this.list=list
    notifyDataSetChanged()

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
  holder.names.text=list[position].courseNames
     holder.userName.text=list[position].user_name






    }

    override fun getItemCount(): Int = list.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val names: TextView = view.findViewById(R.id.names_item)
         val bookmark = view.findViewById<ImageView>(R.id.bookmark)
         val userName = view.findViewById<TextView>(R.id.nameUser_item)
         val money = view.findViewById<TextView>(R.id.money_item)
         val comment = view.findViewById<TextView>(R.id.add_comment_item)
         val more = view.findViewById<TextView>(R.id.more_item)
    }

}