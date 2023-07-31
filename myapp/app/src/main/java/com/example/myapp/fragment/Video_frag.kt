package com.example.myapp.fragment

import VideosAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.WebviewActivity

class VideoDemo1(val author_name:String, val video_title:String, val video_src:String)

class Video_frag: Fragment(){
    private val videoList = ArrayList<VideoDemo1>()

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
        return inflater.inflate(R.layout.video_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val search_view = activity?.findViewById<SearchView>(R.id.sv)
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

        initText() // 初始化
        val recyclerView1 = activity?.findViewById<RecyclerView>(R.id.video_demo)
        val layoutManager1 = LinearLayoutManager(this.context)
        recyclerView1?.layoutManager = layoutManager1
        val adpter1 = VideosAdapter(videoList)
        recyclerView1?.adapter = adpter1
    }

    private fun initText(){
        repeat(1){
            videoList.add(VideoDemo1("Fred Qin","热门视频1","https://globalimg.sucai999.com/uploadfile/20211210/267440/132836000096069452.mp4"))
            videoList.add(VideoDemo1("Byron Howard","疯狂动物城1","http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"))
            videoList.add(VideoDemo1("Kenneth Graham","热门电影1","https://media.w3.org/2010/05/sintel/trailer.mp4"))
        }
    }


    companion object {
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
        fun newInstance(param1: String?, param2: String?): Video_frag {
            val fragment = Video_frag()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}