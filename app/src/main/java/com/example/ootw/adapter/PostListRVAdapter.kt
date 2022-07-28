package com.example.ootw.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ootw.R
import com.example.ootw.fragment.LikeFragment
import com.example.ootw.model.Post

class PostListRVAdapter(val posts: ArrayList<Post>): RecyclerView.Adapter<PostListRVAdapter.ViewHolder>() {

    var mPosition = 0

    fun getPostion(): Int {
        return mPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_post, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (posts != null) {
            return posts.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvDate: TextView = itemView.findViewById(R.id.tv_PostItem_date)
        private val tvRegion: TextView = itemView.findViewById(R.id.tv_PostItem_region)
        private val tvTemperature: TextView = itemView.findViewById(R.id.tv_PostItem_temperature)
        private val ivImage: ImageView = itemView.findViewById(R.id.iv_PostItem_image)
        private val tvBody: TextView = itemView.findViewById(R.id.tv_PostItem_body)

        fun bind(item: Post) {
            tvDate.text = item.createdAt
            tvTemperature.text = item.temp.toString()
            Glide.with(itemView).load(item.imgURL).into(ivImage)
            tvBody.text = item.body

        }
    }

}

