package com.example.myapp

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.database.*
import com.example.myapp.fragment.*


class MainActivity : AppCompatActivity() {
    private var main_home_btn: Button? = null
    private var main_video_btn: Button? = null
    private var main_user_btn: Button? = null
    private var bundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bundle = this.intent.extras

        main_home_btn = findViewById(R.id.main_home_btn)
        main_video_btn = findViewById(R.id.main_video_btn)
        main_user_btn = findViewById(R.id.main_user_btn)

        val frag = intent.getStringExtra("key")
        if(frag != null){
            when(frag){
                "home" -> {
                    turn_frag(findViewById(R.id.main_home_btn))
                }
                "video" -> {
                    turn_frag(findViewById(R.id.main_video_btn))
                }
                "user" -> {
                    turn_frag(findViewById(R.id.main_user_btn))
                }
                else ->{
                    turn_frag(findViewById(R.id.main_home_btn))
                }
            }
        }
        else{
            turn_frag(findViewById(R.id.main_home_btn))
        }


        //三个主键的点击事件
        main_home_btn?.setOnClickListener(View.OnClickListener {
            turn_frag(findViewById(R.id.main_home_btn))
        })
        main_video_btn?.setOnClickListener(View.OnClickListener {
            turn_frag(findViewById(R.id.main_video_btn))
        })
        main_user_btn?.setOnClickListener(View.OnClickListener {
            turn_frag(findViewById(R.id.main_user_btn))
        })

        // 访问数据库，先实例化 MyDBHelper 的子类
        val dbhelper = MyDBHelper_All(this)
        // 以写入模式获取数据存储库
        val db: SQLiteDatabase = dbhelper.writableDatabase

        // 查找当前注册的用户数量
        var cursor = db.query("Accounts", null, null, null, null, null, null)
        var i = 0
        with(cursor) {
            while (moveToNext()) {
                i++
            }
        }
        if(i == 0){
            val values = ContentValues()
            values.put("username","fred")
            values.put("password","123456")
            values.put("age", "18")
            values.put("phone", "18878315329")
            values.put("sex", "man")
            db.insert("Accounts", null, values)
            Toast.makeText(this, "已注册用户数： 1, 默认用户为fred", Toast.LENGTH_SHORT).show() // 首次进入程序提示
        }else
            Toast.makeText(this, "已注册用户数： $i", Toast.LENGTH_SHORT).show()

//-------------------------------------------------------------------------------------------------------------------------------------

        cursor = db.query("News1", null, null, null, null, null, null)
        i = 0
        with(cursor) {
            while (moveToNext()) {
                i++
            }
        }
        if(i == 0){
            val values = ContentValues()
            values.put("title","微粒贷的风险和危险你知道吗？你还敢强开？")
            values.put("type","置顶")
            values.put("author","康波财经")
            db.insert("News1", null, values)

            values.clear()
            values.put("title","这些重大问题，习近平的回答掷地有声")
            values.put("type","置顶")
            values.put("author","新华社新媒体")
            db.insert("News1", null, values)

            values.clear()
            values.put("title","习近平会见”元老会“代表团")
            values.put("type","置顶")
            values.put("author","央视网新闻")
            db.insert("News1", null, values)

            values.clear()
            values.put("title","国家发改委等部门印发《关于促进汽车消费的若干措施》的通知")
            values.put("type","置顶")
            values.put("author","新浪新闻")
            db.insert("News1", null, values)
        }

//-------------------------------------------------------------------------------------------------------------------------------------

        cursor = db.query("News2", null, null, null, null, null, null)
        i = 0
        with(cursor) {
            while (moveToNext()) {
                i++
            }
        }
        if(i == 0){
        val values = ContentValues()
        values.put("title","美媒：五角大楼盯上谷歌在华AI中心 谷歌忙安抚")
        values.put("type","热点")
        values.put("author","参考消息")
        values.put("image",R.drawable.first_recycle_img)
        db.insert("News2", null, values)

        values.clear()
        values.put("title","蔡英文财产曝光：存款5406万 名下拥有6笔不动产")
        values.put("type","热点")
        values.put("author","人民日报海外网")
        values.put("image",R.drawable.second_recycle_img)
        db.insert("News2", null, values)

        values.clear()
        values.put("title","苹果Mac状况频出：硬件软件都出了问题")
        values.put("type","热点")
        values.put("author","IT168")
        values.put("image",R.drawable.third_recycle_img)
        db.insert("News2", null, values)
        }
        cursor.close()

        db.close()
//==================================================================================================


//        // 步骤1：创建一个SharedPreferences对象
//        val sharedPreferences = getPreferences(MODE_PRIVATE)
//        // 步骤2：实例化SharedPreferences.Editor对象
//        val editor = sharedPreferences.edit()
//        editor.clear()
//        // 步骤3：将获取过来的值放入文件
//        editor.putString("title","美媒：五角大楼盯上谷歌在华AI中心 谷歌忙安抚")
//        editor.putString("type","热点")
//        editor.putString("author","参考消息")
//        editor.putInt("image",R.drawable.first_recycle_img)
//
//        editor.putString("title","蔡英文财产曝光：存款5406万 名下拥有6笔不动产")
//        editor.putString("type","热点")
//        editor.putString("author","人民日报海外网")
//        editor.putInt("image",R.drawable.second_recycle_img)
//        // 步骤4：提交（异步写入）
//        editor.apply()
//        // 步骤4：提交（异步写入）
//        editor.commit()
    }


    fun turn_frag(b: Button) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val f_h = Home_frag()
        val f_v = Video_frag()
        val f_u = User_frag()
        val home_button = findViewById<Button>(R.id.main_home_btn)
        val video_button = findViewById<Button>(R.id.main_video_btn)
        val user_button = findViewById<Button>(R.id.main_user_btn)
        when (b.id) {
            R.id.main_home_btn -> {
                home_button.setBackgroundResource(R.drawable.main_on)
                video_button.setBackgroundResource(R.drawable.main_video_off)
                user_button.setBackgroundResource(R.drawable.main_user_off)
                ft.replace(R.id.frag, f_h)
            }
            R.id.main_video_btn -> {
                home_button.setBackgroundResource(R.drawable.main_off)
                video_button.setBackgroundResource(R.drawable.main_video_on)
                user_button.setBackgroundResource(R.drawable.main_user_off)
                ft.replace(R.id.frag, f_v)
            }
            R.id.main_user_btn -> {
                home_button.setBackgroundResource(R.drawable.main_off)
                video_button.setBackgroundResource(R.drawable.main_video_off)
                user_button.setBackgroundResource(R.drawable.main_user_on)
                ft.replace(R.id.frag, f_u)
            }
        }
        ft.commit()
    }
    companion object {
        var username: String? = null
    }
}