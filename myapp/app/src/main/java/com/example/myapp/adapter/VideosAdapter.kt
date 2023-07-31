import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.ActivityVideoPlayer
import com.example.myapp.R
import com.example.myapp.fragment.VideoDemo1
import com.google.android.material.snackbar.Snackbar
import java.util.Random

class VideosAdapter(val videoList: ArrayList<VideoDemo1>):
    RecyclerView.Adapter<VideosAdapter.ViewHolder>(){
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val videoImg: ImageView = view.findViewById(R.id.video_img)
        val videoTitle: TextView = view.findViewById(R.id.video_title)
        val videoAuthor: TextView = view.findViewById(R.id.video_author)

        val thumb: ImageView = view.findViewById(R.id.thumb)
        val favorite: ImageView = view.findViewById(R.id.favorite)
        var comment: ImageView = view.findViewById(R.id.comment)

        init {
            // 点赞功能
            thumb.setOnClickListener{
                if(thumb.drawable.constantState == ContextCompat.getDrawable(itemView.context, R.drawable.thumb_off)?.constantState ){
                    thumb.setImageResource(R.drawable.thumb_on)
//                    Toast.makeText(itemView.context, "已点赞", Toast.LENGTH_SHORT).show()
                    val snackBar = Snackbar.make(itemView, "已点赞", Snackbar.LENGTH_SHORT)
                    snackBar.duration = 600
                    snackBar.show()
                }else{
                    thumb.setImageResource(R.drawable.thumb_off)
//                    Toast.makeText(itemView.context, "取消点赞", Toast.LENGTH_SHORT).show()
                    val snackBar = Snackbar.make(itemView, "取消点赞", Snackbar.LENGTH_SHORT)
                    snackBar.duration = 600
                    snackBar.show()
                }
            }
            // 收藏功能
            favorite.setOnClickListener{
                if(favorite.drawable.constantState == ContextCompat.getDrawable(itemView.context, R.drawable.favorite_off)?.constantState ){
                    favorite.setImageResource(R.drawable.favorite_on)
//                    Toast.makeText(itemView.context, "已收藏", Toast.LENGTH_SHORT).show()
                    val snackBar = Snackbar.make(itemView, "已收藏", Snackbar.LENGTH_SHORT)
                    snackBar.duration = 600
                    snackBar.show()
                }else{
                    favorite.setImageResource(R.drawable.favorite_off)
//                    Toast.makeText(itemView.context, "取消收藏", Toast.LENGTH_SHORT)
                    val snackBar = Snackbar.make(itemView, "取消收藏", Snackbar.LENGTH_SHORT)
                    snackBar.duration = 600
                    snackBar.show()
                }
            }
            //评论功能：敬请期待哦~
            comment.setOnClickListener{
                val snackBar = Snackbar.make(itemView, "工程师已经在快马加鞭的写了，评论功能敬请期待哦~", Snackbar.LENGTH_SHORT)
                snackBar.duration = 1000
                snackBar.show()
            }

            // 在 ViewHolder 构造函数中设置点击监听器
            videoImg.setOnClickListener {
                // 在这里处理点击事件
//                Toast.makeText(itemView.context, "正在播放 ${videoTitle.text}", Toast.LENGTH_SHORT).show()
                val snackBar = Snackbar.make(itemView, "正在播放 ${videoTitle.text}", Snackbar.LENGTH_SHORT)
                snackBar.duration = 1500
                snackBar.show()

                val intent = Intent(view.context, ActivityVideoPlayer::class.java)
                intent.putExtra("video_title",videoTitle.text)
                intent.putExtra("video_src",videoList[adapterPosition].video_src)
                view.context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideosAdapter.ViewHolder, position: Int) {
        val video_demo = videoList[position]

        val random = Random(System.currentTimeMillis())
        val randomNumber = random.nextInt(3)
        when(randomNumber){
            0 -> holder.videoImg.setImageResource(R.drawable.cover1)
            1 -> holder.videoImg.setImageResource(R.drawable.cover2)
            else -> holder.videoImg.setImageResource(R.drawable.cover3)
        }
        holder.videoTitle.text = video_demo.video_title
        holder.videoAuthor.text = video_demo.author_name
    }
    override fun getItemCount() = videoList.size
    }