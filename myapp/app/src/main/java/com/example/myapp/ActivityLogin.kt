package com.example.myapp

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.database.MyDataBase

class ActivityLogin : AppCompatActivity() {
    private var login: Button? = null
    private var register: Button? = null
    private var user_id: EditText? = null
    private var password: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login = findViewById(R.id.login)
        register = findViewById(R.id.register)
        user_id = findViewById(R.id.userid)
        password = findViewById(R.id.password)

        val icon = findViewById<ImageView>(R.id.logo)
        icon.setOnClickListener{
            Toast.makeText(this, "I'm a lovely Blue_Whale~\nemm...maybe purple?? I don't care~", Toast.LENGTH_SHORT).show()
        }

        //匹配对应的用户名和密码才能登录
        login?.setOnClickListener{
            var isLogin = false
            //需要获取输入的用户名和密码
            val userid1 = user_id!!.text.toString()
            val password1 = password!!.text.toString()

            val dbhelper = MyDataBase(this)
            val db: SQLiteDatabase = dbhelper.readableDatabase
            val cursor = db.query("Accounts", null, null, null, null, null, null)
            with(cursor) {
                while (moveToNext()) {
                    val username = getString(getColumnIndexOrThrow("username"))
                    val password = getString(getColumnIndexOrThrow("password"))
                    if(userid1 == username && password1 == password){
                        isLogin = true
                        break;
                    }
                }
            }
            cursor.close()
            db.close()

            if (isLogin) {
                MainActivity.username = userid1
                Toast.makeText(this, "$userid1 登录成功！", Toast.LENGTH_SHORT).show()

                //登陆后跳转到user界面
                var intent1: Intent? = null
                intent1 = Intent(this@ActivityLogin, MainActivity::class.java) //目前跳转到首页
                intent1.putExtra("key", "user")
                startActivity(intent1)
            } else {
                Toast.makeText(this, "账号或密码有误，请重新输入！", Toast.LENGTH_SHORT).show()
            }
        }

        register?.setOnClickListener{
            var intent: Intent? = null
            intent = Intent(this@ActivityLogin, ActivityRegister::class.java)
            startActivity(intent)
        }
    }

    // 点击返回事件处理方法
    fun onImageBackClick(view: View) {
        // 在这里添加从当前 Activity 跳转到目标 Activity 的逻辑
        val intent = Intent(this@ActivityLogin, MainActivity::class.java)
        intent.putExtra("key", "user")
        startActivity(intent)
    }

    override fun onPointerCaptureChanged(hasCapture: Boolean) {
        super.onPointerCaptureChanged(hasCapture)
    }
}