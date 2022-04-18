package com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.adaper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onlineschoolcourses.R
import com.example.onlineschoolcourses.helpers.asTime
import com.example.onlineschoolcourses.helpers.loadImage
import com.example.onlineschoolcourses.ui.screen.ui.fragment.personalArea.adaper.model.PersonalAreaMyCourseModel

class PersonalAreaMyCourseAdapter:RecyclerView.Adapter<PersonalAreaMyCourseAdapter.ViewHolder>() {
private var list= mutableListOf<PersonalAreaMyCourseModel>()



    fun update(list:MutableList<PersonalAreaMyCourseModel>){
        this.list=list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.persona_area_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pos=list[position]
holder.imageItem.loadImage(list[position].course_image)
        holder.moneyItem.text= pos.price.toString()
        holder.namesItem.text=pos.courseNames
        holder.namesProfessionItem.text=pos.profession.toString()
        holder.dataItem.text=pos.timeStamp.toString().asTime()
    }
    override fun getItemCount(): Int {
return  list.size
    }

        class ViewHolder(view:View):RecyclerView.ViewHolder(view){
   val moneyItem=view.findViewById<TextView>(R.id.money_item)
    val imageItem=view.findViewById<ImageView>(R.id.image_item)
    val namesItem=view.findViewById<TextView>(R.id.names_item)
    val namesProfessionItem=view.findViewById<TextView>(R.id.names_profession_item)
  val dataItem=view.findViewById<TextView>(R.id.data_profession_item)
        }
}