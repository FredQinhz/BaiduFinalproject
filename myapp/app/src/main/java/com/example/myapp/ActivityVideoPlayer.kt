package com.example.myapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class ActivityVideoPlayer: AppCompatActivity() {
    private var videoTitle: TextView? = null
    private var vView: VideoView? = null
    private var mediaController: MediaController? = null

    var backButton: ImageView? = null

    var thumb: ImageView? = null
    var share: ImageView? = null
    var comment: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        thumb = findViewById(R.id.thumb)
        comment= findViewById(R.id.comment)
        share = findViewById(R.id.share)
        // 点赞功能
        thumb?.setOnClickListener{
            if(thumb?.drawable?.constantState == ContextCompat.getDrawable(this, R.drawable.thumb_off)?.constantState ){
                thumb?.setImageResource(R.drawable.thumb_on)
//                    Toast.makeText(itemView.context, "已点赞", Toast.LENGTH_SHORT).show()
                val snackBar = Snackbar.make(findViewById(android.R.id.content), "已点赞", Snackbar.LENGTH_SHORT)
                snackBar.duration = 600
                snackBar.show()
            }else{
                thumb?.setImageResource(R.drawable.thumb_off)
//                    Toast.makeText(itemView.context, "取消点赞", Toast.LENGTH_SHORT).show()
                val snackBar = Snackbar.make(findViewById(android.R.id.content), "取消点赞", Snackbar.LENGTH_SHORT)
                snackBar.duration = 600
                snackBar.show()
            }
        }
        //评论功能：敬请期待
        comment?.setOnClickListener{
            val snackBar = Snackbar.make(findViewById(android.R.id.content), "工程师已经在快马加鞭的写了，评论功能敬请期待哦~", Snackbar.LENGTH_SHORT)
            snackBar.duration = 1000
            snackBar.show()
        }
        //分享功能
        share?.setOnClickListener{
            val snackBar = Snackbar.make(findViewById(android.R.id.content), "已分享", Snackbar.LENGTH_SHORT)
            snackBar.duration = 1000
            snackBar.show()
        }

        videoTitle = findViewById(R.id.video_title)
        vView = findViewById(R.id.video_player)
        // 设置title
        videoTitle?.text = this.intent.getStringExtra("video_title")
        // 设置uri
        val uri = this.intent.getStringExtra("video_src")
        vView?.setVideoURI(Uri.parse(uri))
//        vView?.setVideoPath(uri)

        // 添加MediaController
        mediaController = MediaController(this)
        vView?.setMediaController(mediaController)
        mediaController?.setAnchorView(vView)

        vView?.start() // 播放视频


        backButton = findViewById(R.id.back_button)
        // 返回
        backButton?.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("key","video")
            startActivity(intent)
        }
    }
}