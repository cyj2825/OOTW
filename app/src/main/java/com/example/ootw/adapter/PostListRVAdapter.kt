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
import com.example.ootw.model.PostDTO

class PostListRVAdapter(val context: Context, val postList: ArrayList<PostDTO>):
    RecyclerView.Adapter<PostListRVAdapter.ItemViewHolder>() {

    var mPosition = 0

    fun getPostion(): Int {
        return mPosition
    }

    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var postImage = view.findViewById<ImageView>(R.id.iv_PostItem_image)
        var postWeather = view.findViewById<TextView>(R.id.tv_PostItem_weather)
        var postIcon = view.findViewById<ImageView>(R.id.iv_PostItem_weather_icon)
        var postRegion = view.findViewById<TextView>(R.id.tv_PostItem_region)
        var postExplain = view.findViewById<TextView>(R.id.tv_PostItem_explain)

        fun bind(post: PostDTO, context: Context) {
            if (post.imageUrl != "") {
                val resourceId = context.resources.getIdentifier(post.imageUrl, "drawable", context.packageName)
                postImage?.setImageResource(resourceId)
            } else {
                postImage?.setImageResource(R.mipmap.ic_launcher)
            }

            if (post.icon != "") {
                val resourceId = context.resources.getIdentifier(post.imageUrl, "drawable", context.packageName)
                postIcon?.setImageResource(resourceId)
            } else {
                postIcon?.setImageResource(R.mipmap.ic_launcher)
            }

            postWeather?.text = post.weather
            postRegion?.text = post.region
            postExplain?.text = post.explain

            Log.d("bookmark", "post: "+post.region)
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

