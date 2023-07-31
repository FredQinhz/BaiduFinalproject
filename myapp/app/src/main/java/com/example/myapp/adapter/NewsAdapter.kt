package com.example.myapp.adapter

import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.ActivityNews
import com.example.myapp.R
import com.example.myapp.fragment.TextDemo1
import com.example.myapp.fragment.TextImageDemo1


class NewsAdapter1(val textList: ArrayList<TextDemo1>):
    RecyclerView.Adapter<NewsAdapter1.ViewHolder>(){
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val text1_demo1: TextView = view.findViewById(R.id.list_tv1)
        val text2_demo1: TextView = view.findViewById(R.id.list_tv2)
        val text3_demo1: TextView = view.findViewById(R.id.list_tv3)

        init {
            // 在 ViewHolder 构造函数中设置点击监听器
            text1_demo1.setOnClickListener {
                // 在这里处理点击事件
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // 处理点击事件的逻辑
//                    Toast.makeText(itemView.context, "点击了 News1 第 $position 项: " + "${text1_demo1.text}", Toast.LENGTH_SHORT).show()

                    val intent = Intent(itemView.context, ActivityNews::class.java)
                    intent.putExtra("title",text1_demo1.text)
                    intent.putExtra("type",text2_demo1.text)
                    intent.putExtra("author",text3_demo1.text)
                    intent.putExtra("abstract",textList[position].text4)
                    intent.putExtra("article",textList[position].text5)
                    intent.putExtra("image",R.drawable.no_img_news)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val text_demo1 = textList[position]
        holder.text1_demo1.text = text_demo1.text1
        holder.text2_demo1.text = text_demo1.text2
        holder.text3_demo1.text = text_demo1.text3
    }

    override fun getItemCount() = textList.size
}

class NewsAdapter2(val textList2: ArrayList<TextImageDemo1>):
    RecyclerView.Adapter<NewsAdapter2.ViewHolder2>(){
    inner class ViewHolder2(view: View) : RecyclerView.ViewHolder(view){
        val text1_demo2: TextView = view.findViewById(R.id.list2_tv1)
        val text2_demo2: TextView = view.findViewById(R.id.list2_tv2)
        val text3_demo2: TextView = view.findViewById(R.id.list2_tv3)
        val text2image_demo2: ImageView = view.findViewById((R.id.list2_img1))

        init {
            // 在 ViewHolder 构造函数中设置点击监听器
            text1_demo2.setOnClickListener {
                // 在这里处理点击事件
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    // 处理点击事件的逻辑
//                    Toast.makeText(itemView.context, "点击了 News2 第 $position 项: " + "${text1_demo2.text}", Toast.LENGTH_SHORT).show()

                    val intent = Intent(itemView.context, ActivityNews::class.java)
                    intent.putExtra("title",text1_demo2.text)
                    intent.putExtra("type",text2_demo2.text)
                    intent.putExtra("author",text3_demo2.text)
                    intent.putExtra("abstract",textList2[position].text4)
                    intent.putExtra("article",textList2[position].text5)
                    intent.putExtra("image",textList2[position].imageId)

                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news2, parent, false)
        return ViewHolder2(view)
    }

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
        val text_demo2 = textList2[position]
        holder.text1_demo2.text = text_demo2.text1
        holder.text2_demo2.text = text_demo2.text2
        holder.text3_demo2.text = text_demo2.text3
        holder.text2image_demo2.setImageResource(text_demo2.imageId)
    }

    override fun getItemCount() = textList2.size
}