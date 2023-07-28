package com.example.myapp

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.ContentUris
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapp.database.MyDBHelper_All
import com.google.android.material.snackbar.Snackbar
import java.util.Random

class AddNews() : AppCompatActivity() {
    private var img1: ImageView? = null
    private var img2: ImageView? = null
    private var input_title: TextView? = null
    private var input_abstract: TextView? = null
    private var input_article: TextView? = null
    private var input_author: TextView? = null
    private var R1: RadioButton? = null
    private var R2: RadioButton? = null
    private var title: String? = null
    private var abstract: String? = null
    private var article: String? = null
    private var author: String? = null
    var img_path: String? = null
    private var type: String? = null
    var sig = 0
    var sig2 = 0
    var radioGroup: RadioGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_news)
        img1 = findViewById<View>(R.id.news_pic1) as ImageView
        img2 = findViewById<View>(R.id.news_pic2) as ImageView
        img1!!.setOnClickListener(View.OnClickListener {
            //如果没有权限则申请权限
            if (ContextCompat.checkSelfPermission(
                    this@AddNews,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@AddNews,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
            }
            sig = 1
            openAlbum() //调用打开相册
        })
        img2!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                //如果没有权限则申请权限
                if (ContextCompat.checkSelfPermission(
                        this@AddNews,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this@AddNews,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        1
                    )
                }
                sig = 2
                openAlbum() //调用打开相册
            }
        })

        //返回
        findViewById<View>(R.id.back_button).setOnClickListener {
            val intent = Intent(this@AddNews, MainActivity::class.java)
            startActivity(intent)
        }

        //选项
        radioGroup = findViewById<View>(R.id.RadioGroup) as RadioGroup
        R1 = findViewById<View>(R.id.radioButton1) as RadioButton
        R2 = findViewById<View>(R.id.radioButton2) as RadioButton
        radioGroup!!.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radioButton1)
                sig2 = 1
            else if (checkedId == R.id.radioButton2)
                sig2 = 2
        }

        //提交
        findViewById<View>(R.id.submit_news).setOnClickListener {
            //获取用户填写的信息
            input_title = findViewById<View>(R.id.input_title) as EditText
            input_abstract = findViewById<View>(R.id.input_abstract) as EditText
            input_article = findViewById<View>(R.id.input_article) as EditText
            input_author = findViewById<View>(R.id.input_author) as EditText
            title = input_title!!.text.toString()
            abstract = input_abstract!!.text.toString()
            article = input_article!!.text.toString()
            author = input_author!!.text.toString()
            if (sig2 == 1)
                type = "置顶"
            else if (sig2 == 2)
                type = "热门"

            //提交条件
            if ((title!!.trim { it <= ' ' } == "") || (abstract!!.trim { it <= ' ' } == "") || (article!!.trim { it <= ' ' } == "") || (author!!.trim { it <= ' ' } == "")) {
                Toast.makeText(this@AddNews, "请填写完整后再提交", Toast.LENGTH_SHORT).show()
            } else {
                // 访问数据库，先实例化 MyDBHelper 的子类
                val dbhelper = MyDBHelper_All(this)
                // 以写入模式获取数据存储库
                val db: SQLiteDatabase = dbhelper.writableDatabase
                val values = ContentValues()

                if(type == "置顶"){
                    values.clear()
                    values.put("title", title)
                    values.put("type", type)
                    values.put("author", author)
                    db.insert("News1", null, values)
                }else{
                    //有图的情况暂时先这样把
                    values.clear()
                    values.put("title", title)
                    values.put("type", type)
                    values.put("author", author)

                    val random = Random(System.currentTimeMillis())
                    val randomNumber = random.nextInt(11)
                    when(randomNumber % 3){
                        0 -> {
                            values.put("image", R.drawable.fourth_recycle_img)
                        }
                        1 -> {
                            values.put("image", R.drawable.fifth_recycle_img)
                        }
                        else -> {
                            values.put("image", R.drawable.sixth_recycle_img)
                        }
                    }
                    db.insert("News2", null, values)
                }

                db.close()

                val intent = Intent(this@AddNews, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openAlbum() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, 2) // 打开相册
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbum()
            } else {
                Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 判断手机系统版本号
        if (Build.VERSION.SDK_INT >= 19) {
            // 4.4及以上系统使用这个方法处理图片
            handleImageOnKitKat(data)
        } else {
            // 4.4以下系统使用这个方法处理图片
            handleImageBeforeKitKat(data)
        }
    }

    @TargetApi(19)
    private fun handleImageOnKitKat(data: Intent?) {
        img_path = null
        try {
            val uri = data!!.data
            Log.d("TAG", "handleImageOnKitKat: uri is $uri")
            if (DocumentsContract.isDocumentUri(this, uri)) {
                // 如果是document类型的Uri，则通过document id处理
                val docId = DocumentsContract.getDocumentId(uri)
                if (("com.android.providers.media.documents" == uri!!.authority)) {
                    val id = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()[1] // 解析出数字格式的id
                    val selection = MediaStore.Images.Media._ID + "=" + id
                    img_path = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection)
                } else if (("com.android.providers.downloads.documents" == uri.authority)) {
                    val contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        java.lang.Long.valueOf(docId)
                    )
                    img_path = getImagePath(contentUri, null)
                }
            } else if ("content".equals(uri!!.scheme, ignoreCase = true)) {
                // 如果是content类型的Uri，则使用普通方式处理
                img_path = getImagePath(uri, null)
            } else if ("file".equals(uri.scheme, ignoreCase = true)) {
                // 如果是file类型的Uri，直接获取图片路径即可
                img_path = uri.path
            }
            displayImage(img_path) // 根据图片路径显示图片
        } catch (e: Exception) {
            e.printStackTrace() // 输出异常信息
        }
    }

    private fun handleImageBeforeKitKat(data: Intent?) {
        val uri = data!!.data
        img_path = getImagePath(uri, null)
        displayImage(img_path)
    }

    @SuppressLint("Range")
    private fun getImagePath(uri: Uri?, selection: String?): String? {
        var path: String? = null
        // 通过Uri和selection来获取真实的图片路径
        val cursor = contentResolver.query((uri)!!, null, selection, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        return path
    }

    private fun displayImage(imagePath: String?) {
        if (imagePath != null) {
            val bitmap = BitmapFactory.decodeFile(imagePath)
            if (sig == 1) img1!!.setImageBitmap(bitmap) else img2!!.setImageBitmap(bitmap)
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show()
        }
    }
}