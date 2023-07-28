package com.example.myapp.fragment

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.*
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.AddNews
import com.example.myapp.R
import com.example.myapp.WeatherActivity
import com.example.myapp.WebviewActivity
import com.example.myapp.adapter.*
import com.example.myapp.database.*



class TextDemo1(val text1:String, val text2:String, val text3:String)
class TextImageDemo1(val text1:String, val text2:String, val text3:String, val imageId: Int)

/**
 * A simple [Fragment] subclass.
 * Use the [Frag_home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home_frag : Fragment() {
    private val textDemo1List = ArrayList<TextDemo1>()     //没图
    private val textImageDemo1List = ArrayList<TextImageDemo1>()//有图

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments?.getString(ARG_PARAM1)
            mParam2 = arguments?.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragement, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val icon = activity?.findViewById<ImageView>(R.id.logo)
        icon?.setOnClickListener{
            Toast.makeText(this.context, "I'm a lovely Blue_Whale~\nemm...maybe purple?? I don't care~", Toast.LENGTH_SHORT).show()
        }

        val search_view = activity?.findViewById<SearchView>(R.id.sv1)

        search_view?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                // 当用户按下回车键或点击搜索按钮时调用该方法
                if (query != null) {
                    // 执行你的搜索操作
                    WebviewActivity.searchText = query
                    startActivity(Intent(requireActivity(), WebviewActivity::class.java))
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                // 用户在搜索框中改变文本时调用该方法
               return false
            }
        })

        val dbhelper = this.context?.let { MyDBHelper_All(it) }
        // 以读取模式获取数据存储库
        val db: SQLiteDatabase = dbhelper!!.readableDatabase
        val cursor = db.query("News1", null, null, null, null, null, null)
        var i = 0
        with(cursor) {
            while (moveToNext()) {
                val title = getString(getColumnIndexOrThrow("title"))
                val type = getString(getColumnIndexOrThrow("type"))
                val author = getString(getColumnIndexOrThrow("author"))
                textDemo1List.add(TextDemo1(title,type,author))
                i++
            }
        }
        cursor.close()

        repeat(2) {
            val cursor2 = db.query("News2", null, null, null, null, null, null)
            var k = 0
            with(cursor2) {
                while (moveToNext()) {
                    val title = getString(getColumnIndexOrThrow("title"))
                    val type = getString(getColumnIndexOrThrow("type"))
                    val author = getString(getColumnIndexOrThrow("author"))
                    val image = getInt(getColumnIndexOrThrow("image"))
                    textImageDemo1List.add(TextImageDemo1(title, type, author, image))
                    k++
                }
            }
            cursor2.close()
        }

        db.close()
//        Toast.makeText(this.context, i.toString(), Toast.LENGTH_SHORT).show()


        //recyclerview的写法
        val recyclerView1 = activity?.findViewById<RecyclerView>(R.id.recyclerView_demo1)
        val layoutManager1 = LinearLayoutManager(this.context)
        recyclerView1?.layoutManager = layoutManager1
        val adpter1 = NewsAdapter1(textDemo1List)
        recyclerView1?.adapter = adpter1

        val recyclerView2 = activity?.findViewById<RecyclerView>(R.id.recyclerView_demo2)
        val layoutManager2 = LinearLayoutManager(this.context)
        recyclerView2?.layoutManager = layoutManager2
        val adpter2 = NewsAdapter2(textImageDemo1List)
        recyclerView2?.adapter = adpter2

        //让输入法能覆盖布局元素(很重要),记得要去改AndroidMainfest里面的windowSoftInputMode为adjustPan
        val editText = activity?.findViewById<SearchView>(R.id.sv1)
        editText?.setOnTouchListener { v, event ->
            v.parent.requestDisallowInterceptTouchEvent(true)
            false
        }

        val imageView = activity?.findViewById<ImageView>(R.id.floatingActionButton1)
        imageView?.setOnClickListener {
            val intent = Intent(this.context, AddNews::class.java)
            startActivity(intent)
        }

        val textView = activity?.findViewById<TextView>(R.id.textView3)
        textView?.setOnClickListener{
            onTextViewClick(View(this.context))
        }
    }

    val LOADING_DELAY: Long = 300 // 300ms
    lateinit var progressBar: ProgressBar
    // 点击事件处理方法
    fun onTextViewClick(view: View) {
        // 显示 loading 界面
        progressBar = activity?.findViewById(R.id.progressBar1)!!
        progressBar.visibility = View.VISIBLE
        val linearLayout1 = activity?.findViewById<LinearLayout>(R.id.ll5)
        linearLayout1?.setBackgroundColor(Color.WHITE)

        // 延迟 300ms 后跳转到目标 Activity
        Handler(Looper.getMainLooper()).postDelayed({
            // 隐藏 loading 界面
            progressBar.visibility = View.GONE

            // 跳转到目标 Activity
            val intent = Intent(this.context, WeatherActivity::class.java)
            startActivity(intent)

        }, LOADING_DELAY) // 延迟 300ms
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Frag_home.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): Home_frag {
            val fragment = Home_frag()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}