package com.example.myapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.Weatherdemo1

class WeatherAdapter(val weatherlist: List<Weatherdemo1>):
        RecyclerView.Adapter<WeatherAdapter.ViewHolder2>(){
    inner class ViewHolder2(view: View) : RecyclerView.ViewHolder(view){
        val text1_demo2: TextView = view.findViewById(R.id.tv1)
        val text2image_demo2: ImageView = view.findViewById((R.id.img1))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather,parent,false)
        return ViewHolder2(view)
    }

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
        val text_demo2 = weatherlist[position]
        holder.text1_demo2.text = text_demo2.text1
        holder.text2image_demo2.setImageResource(text_demo2.imageId)
    }

    override fun getItemCount() = weatherlist.size
}
