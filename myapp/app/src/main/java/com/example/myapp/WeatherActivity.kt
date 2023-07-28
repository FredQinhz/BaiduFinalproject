package com.example.myapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.adapter.WeatherAdapter

class Weatherdemo1(val text1:String, val imageId: Int)

class WeatherActivity : AppCompatActivity() {

    private val Weatherdemo1 = ArrayList<Weatherdemo1>()//有图

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val icon = findViewById<ImageView>(R.id.logo)
        icon.setOnClickListener{
            Toast.makeText(this, "I'm a lovely Blue_Whale~\nemm...maybe purple?? I don't care~", Toast.LENGTH_SHORT).show()
        }

        initWeather() // 初始化数据
        val recyclerView1 = findViewById<RecyclerView>(R.id.weatherrecyclerview1)
        val layoutManager1 = LinearLayoutManager(this)
        layoutManager1.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView1.layoutManager = layoutManager1
        val adpter1 = WeatherAdapter(Weatherdemo1)
        recyclerView1.adapter = adpter1
    }

    // 点击跳转事件处理方法
    fun goBack(view: View) {
        val LOADING_DELAY: Long = 300 // 300ms
        // 显示 loading 界面
        var progressBar: ProgressBar = findViewById(R.id.progressBar2)
        progressBar.visibility = View.VISIBLE
        val linearLayout1 =findViewById<LinearLayout>(R.id.ll5)
        linearLayout1?.setBackgroundColor(Color.WHITE)

        // 延迟 300ms 后跳转到目标 Activity
        Handler(Looper.getMainLooper()).postDelayed({
            // 隐藏 loading 界面
            progressBar.visibility = View.GONE

            // 跳转到目标 Activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, LOADING_DELAY) // 延迟 300ms
    }

    private fun initWeather(){
        Weatherdemo1.add(Weatherdemo1("现在",R.drawable.summer1))
        Weatherdemo1.add(Weatherdemo1("1小时后",R.drawable.raincloud1))
        Weatherdemo1.add(Weatherdemo1("2小时后",R.drawable.raincloud1))
        Weatherdemo1.add(Weatherdemo1("3小时后",R.drawable.rain1))
        Weatherdemo1.add(Weatherdemo1("4小时后",R.drawable.rain1))
        Weatherdemo1.add(Weatherdemo1("5小时后",R.drawable.raincloud1))
        Weatherdemo1.add(Weatherdemo1("6小时后",R.drawable.raincloud1))
        Weatherdemo1.add(Weatherdemo1("7小时后",R.drawable.summer1))
        Weatherdemo1.add(Weatherdemo1("8小时后",R.drawable.summer1))
    }

}