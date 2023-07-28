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

class ActivityEdit: AppCompatActivity() {
    var username: EditText? = null
    var password: EditText? = null
    var password_confirm: EditText? = null
    var age: EditText? = null
    var phone: EditText? = null
    var sex: RadioGroup? = null
    var edit: Button? = null
    var back_button: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        username = findViewById(R.id.usernameRegister)
        password = findViewById(R.id.passwordRegister)
        password_confirm = findViewById(R.id.passwordRegisterConfirm)
        age = findViewById(R.id.ageRegister)
        phone = findViewById(R.id.phoneRegister)
        sex = findViewById(R.id.sexRegister)

        edit = findViewById(R.id.edit_button)
        back_button = findViewById(R.id.back_button)

        getUserInfo() //获取到个人信息
        val origin_pass = password?.text.toString().trim { it <= ' ' } //保存原始密码，查看密码是否修改，若修改则退出到登陆界面

        back_button!!.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent?.putExtra("key", "user")
            startActivity(intent)
        }

        edit!!.setOnClickListener {
            val name = username?.text.toString().trim { it <= ' ' }
            val pass = password?.text.toString().trim { it <= ' ' }
            val ageStr = age?.text.toString().trim { it <= ' ' }
            val phoneNum = phone?.text.toString().trim { it <= ' ' }
            val sexStr = (findViewById<View>(sex!!.checkedRadioButtonId) as RadioButton).text.toString()

            when(check()){
                2 -> Toast.makeText(this, "两次输入的密码应保持一致！", Toast.LENGTH_LONG).show()
                3 -> Toast.makeText(this, "密码不能为空！", Toast.LENGTH_LONG).show()
                4 -> Toast.makeText(this, "电话号码不正确！", Toast.LENGTH_LONG).show()
                else -> {
                    val dbHelper = MyDBHelper_All(this)
                    val db: SQLiteDatabase = dbHelper!!.writableDatabase

                    val values = ContentValues().apply {
                        put("password", pass)
                        put("age", ageStr)
                        put("phone", phoneNum)
                        if(sexStr == "男")
                            put("sex", "man")
                        else
                            put("sex", "woman")
                    }
                    db.update("Accounts", values, "username = ?", arrayOf(name))
                    db.close()

                    Toast.makeText(this, "个人信息修改成功!", Toast.LENGTH_LONG).show()

                    if(origin_pass == pass) {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("key", "user")
                        startActivity(intent)
                    }else{
                        MainActivity.username = null
                        Toast.makeText(this, "您修改了密码，请重新登录", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, ActivityLogin::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
    fun check(): Int {
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
    fun getUserInfo(): Unit{
        val dbHelper = MyDBHelper_All(this)
        val db: SQLiteDatabase = dbHelper!!.readableDatabase
        val cursor = db.query("Accounts", null, null, null, null, null, null)
        with(cursor) {
            while (moveToNext()) {
                val name = getString(getColumnIndexOrThrow("username"))
                if (MainActivity.username == name) {
                    username?.setText(MainActivity.username)
                    password?.setText(getString(getColumnIndexOrThrow("password")))
                    password_confirm?.setText(getString(getColumnIndexOrThrow("password")))
                    age?.setText(getString(getColumnIndexOrThrow("age")))
                    phone?.setText(getString(getColumnIndexOrThrow("phone")))

                    val radioButtonId = when (getString(getColumnIndexOrThrow("sex"))) {
                        "man" -> R.id.man // 如果选项为 "man"，设置 RadioButtonMale 为选中状态
                        "woman" -> R.id.woman // 如果选项为 "woman"，设置 RadioButtonFemale 为选中状态
                        else -> -1 // 如果选项不是 "man" 或者 "woman"，设置为 -1 来表示没有选中任何 RadioButton
                    }
                    sex?.check(radioButtonId)
                }
            }
        }
        cursor.close()
        db.close()
    }
}