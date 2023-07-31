package com.example.myapp.fragment

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapp.ActivityEdit
import com.example.myapp.ActivityLogin
import com.example.myapp.MainActivity
import com.example.myapp.R
import com.example.myapp.database.MyDataBase

class User_frag: Fragment(){
    private val login: Button? = null
    private var username: TextView? = null
    private var phone: TextView? = null
    private var age: TextView? = null
    private var sex: TextView? = null

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
        return if (MainActivity.username == null)
            inflater.inflate(R.layout.user_unlogin_fragment, container, false)
        else
            inflater.inflate(R.layout.user_login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(MainActivity.username == null){
            val icon = activity?.findViewById<ImageView>(R.id.logo_logoff)
            icon?.setOnClickListener{
                Toast.makeText(this.context, "I'm a lovely Blue_Whale~\nemm...maybe purple?? I don't care~", Toast.LENGTH_SHORT).show()
            }

            val button_login = activity?.findViewById<Button>(R.id.person_login)
            button_login?.setOnClickListener{
                var intent: Intent? = null
                intent = Intent(this.context, ActivityLogin::class.java)
                startActivity(intent);
            }
        }else{
            val icon = activity?.findViewById<ImageView>(R.id.logo_login)
            icon?.setOnClickListener{
                Toast.makeText(this.context, "I'm a lovely Blue_Whale~\nemm...maybe purple?? I don't care~", Toast.LENGTH_SHORT).show()
            }

            // 编 辑
            val button_edit = activity?.findViewById<Button>(R.id.edit)
            button_edit?.setOnClickListener{
                var intent: Intent? = null
                intent = Intent(this.context, ActivityEdit::class.java)
                startActivity(intent);
            }

            // 登 出
            val button_logoff = activity?.findViewById<Button>(R.id.logoff)
            button_logoff?.setOnClickListener{
                MainActivity.username = null

//                 // 虽然但是，不能接受一次activity的跳转动画
//                var intent: Intent? = null
//                intent = Intent(this.context, MainActivity::class.java)
//                intent?.putExtra("key", "user")
//                startActivity(intent);

                val fm = requireActivity().supportFragmentManager
                val ft = fm.beginTransaction()
                val f_u = User_frag()
                ft.replace(R.id.frag, f_u)
                ft.commit()
            }

            //获取用户信息
            val dbhelper =  this.context?.let { MyDataBase(it) }
            val db: SQLiteDatabase = dbhelper!!.readableDatabase
            val cursor = db.query("Accounts", null, null, null, null, null, null)
            with(cursor) {
                while (moveToNext()) {
                    var user_name = getString(getColumnIndexOrThrow("username"))
                    if(MainActivity.username == user_name){
                        username = activity?.findViewById(R.id.user_name)
                        phone = activity?.findViewById(R.id.user_phone)
                        sex = activity?.findViewById(R.id.user_sex)
                        age = activity?.findViewById(R.id.user_age)

                        username?.text = getString(getColumnIndexOrThrow("username"))
                        age?.text = getString(getColumnIndexOrThrow("age"))
                        phone?.text = getString(getColumnIndexOrThrow("phone"))
                        if(getString(getColumnIndexOrThrow("sex"))== "man")
                            sex?.text = "男"
                        else
                            sex?.text = "女"
                        break;
                    }
                }
            }
            cursor.close()
            db.close()
        }

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
        fun newInstance(param1: String?, param2: String?): User_frag {
            val fragment = User_frag()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}