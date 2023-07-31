package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActivityNews: AppCompatActivity()  {
    private var imgView: ImageView? = null
    private var titleView: TextView? = null
    private var authorView: TextView? = null
    private var typeView: TextView? = null
    private var abstractView: TextView? = null
    private var articleView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        imgView = findViewById(R.id.news_image)
        titleView = findViewById(R.id.news_title)
        authorView = findViewById(R.id.news_author)
        abstractView = findViewById(R.id.news_abstract)
        articleView =  findViewById(R.id.news_content)

        var backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val icon = findViewById<ImageView>(R.id.logo)
        icon.setOnClickListener{
            Toast.makeText(this, "I'm a lovely Blue_Whale~\nemm...maybe purple?? I don't care~", Toast.LENGTH_SHORT).show()
        }

        titleView?.text = this.intent.getStringExtra("title")
        authorView?.text = this.intent.getStringExtra("author")
        typeView?.text = this.intent.getStringExtra("type")
        imgView?.setImageResource(this.intent.getIntExtra("image", R.drawable.no_img_news))

        abstractView?.text = this.intent.getStringExtra("abstract")
        articleView?.text = this.intent.getStringExtra("article")
    }
}