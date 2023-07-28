package com.example.myapp

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp.database.MyDBHelper_All

class ActivityRegister : AppCompatActivity() {
    var username: EditText? = null
    var password: EditText? = null
    var password_confirm: EditText? = null
    var age: EditText? = null
    var phone: EditText? = null
    var sex: RadioGroup? = null
    var register: Button? = null
    var back_button: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        username = findViewById(R.id.usernameRegister)
        password = findViewById(R.id.passwordRegister)
        password_confirm = findViewById(R.id.passwordRegisterConfirm)
        age = findViewById(R.id.ageRegister)
        phone = findViewById(R.id.phoneRegister)
        sex = findViewById(R.id.sexRegister)
        register = findViewById(R.id.Register)
        back_button = findViewById(R.id.back_button)

        back_button!!.setOnClickListener{
            val intent = Intent(this@ActivityRegister, ActivityLogin::class.java)
            startActivity(intent)
        }

        register!!.setOnClickListener {
            val name = username?.text.toString().trim { it <= ' ' }
            val pass = password?.text.toString().trim { it <= ' ' }
            val ageStr = age?.text.toString().trim { it <= ' ' }
            val phoneNum = phone?.text.toString().trim { it <= ' ' }
            val sexStr = (findViewById<View>(sex!!.checkedRadioButtonId) as RadioButton).text.toString()

            when(check(name)){
                1 -> Toast.makeText(this, "用户名已存在，请更换用户名后再注册！", Toast.LENGTH_LONG).show()
                2 -> Toast.makeText(this, "两次输入的密码应保持一致！", Toast.LENGTH_LONG).show()
                3 -> Toast.makeText(this, "密码不能为空！", Toast.LENGTH_LONG).show()
                4 -> Toast.makeText(this, "电话号码不正确！", Toast.LENGTH_LONG).show()
                else -> {
                    val dbHelper = MyDBHelper_All(this)
                    val db: SQLiteDatabase = dbHelper!!.writableDatabase

                    val values = ContentValues().apply {
                        put("username", name)
                        put("password", pass)
                        put("age", ageStr)
                        put("phone", phoneNum)
                        if(sexStr == "男")
                            put("sex", "man")
                        else
                            put("sex", "woman")
                    }
                    db.insert("Accounts", null, values)
                    db.close()

                    Toast.makeText(this@ActivityRegister, "注册成功! 用户ID为：$name", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@ActivityRegister, ActivityLogin::class.java)
                    startActivity(intent)
                }
            }
        }
    }
    fun check(name : String): Int {
        val dbHelper = MyDBHelper_All(this)
        val db: SQLiteDatabase = dbHelper!!.readableDatabase
        val cursor = db.query("Accounts", null, null, null, null, null, null)
        with(cursor) {
            while (moveToNext()) {
                val username = getString(getColumnIndexOrThrow("username"))
                if (name == username) { // 用户名重复
                    return 1
                }
            }
        }
        cursor.close()
        db.close()

        if(password?.text.toString() != password_confirm?.text.toString()) { // 两次密码输入不一致
            return 2
        }
        if(password?.text.toString().isEmpty() || password_confirm?.text.toString().isEmpty()){ // 密码为空
            return 3
        }
        if(!Regex("^1[3-9]\\d{9}$").matches(phone?.text.toString())){ //电话号码合理性
            return 4
        }

        return 0
    }
}