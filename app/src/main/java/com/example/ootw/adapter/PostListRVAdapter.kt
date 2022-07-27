package com.example.ootw.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ootw.R
import com.example.ootw.model.Post

class PostListRVAdapter(val context: Context, val postList: ArrayList<Post>):
    RecyclerView.Adapter<PostListRVAdapter.ItemViewHolder>() {

    var mPosition = 0

    fun getPostion(): Int {
        return mPosition
    }

    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var postImage = view.findViewById<ImageView>(R.id.iv_PostItem_image)
        var postWeather = view.findViewById<TextView>(R.id.tv_PostItem_temperature)
        var postIcon = view.findViewById<ImageView>(R.id.iv_PostItem_weather_icon)
        var postRegion = view.findViewById<TextView>(R.id.tv_PostItem_region)
        var postBody = view.findViewById<TextView>(R.id.tv_PostItem_body)

        fun bind(post: Post, context: Context) {
            if (post.imgURL != "") {
                val resourceId = context.resources.getIdentifier(post.imgURL, "drawable", context.packageName)
                postImage?.setImageResource(resourceId)
            } else {
                postImage?.setImageResource(R.mipmap.ic_launcher)
            }

            if (post.imgURL != "") {
                val resourceId = context.resources.getIdentifier(post.imgURL, "drawable", context.packageName)
                postIcon?.setImageResource(resourceId)
            } else {
                postIcon?.setImageResource(R.mipmap.ic_launcher)
            }

            postWeather?.text = post.temp.toString()
            // TODO: User 모델클래스의 area 필드 가져와야
//            postRegion?.text = post.region
            postBody?.text = post.body

            Log.d("postList", "body: "+post.body)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_post, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(postList[position], context)

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }


}

