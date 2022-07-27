package com.example.ootw.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ootw.R
import com.example.ootw.model.Post

class LikeFragment : Fragment() {
    private lateinit var callback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_like, container, false)
    }

//  뒤로가기 버튼 실행시
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("TestLog", "백프레스")
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.main_screen_panel, MyPageFragment())?.commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    inner class LikeFragmentRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var postDTOs = arrayListOf<Post>(
            // test 값
            Post("test1", "", "오늘은 참 덥네요!", "", 26, "나시", "2022-07-20T07:20:07.000Z", "2022-07-20T07:20:07.000Z", "user1", ""),

            )

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var width = resources.displayMetrics.widthPixels / 3

            var imageview = ImageView(parent.context)
            imageview.layoutParams = LinearLayoutCompat.LayoutParams(width, width)
            return CustomViewHolder(imageview)
        }

        inner class CustomViewHolder(var imageview: ImageView): RecyclerView.ViewHolder(imageview) {

        }

        override fun getItemCount(): Int {
            return postDTOs.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var imageview = (holder as CustomViewHolder).imageview
            Glide.with(holder.itemView.context).load(postDTOs[position].imgURL).apply(
                RequestOptions().centerCrop()).into(imageview)
            Log.d("LikeFragment", "post: "+postDTOs[position].id)

        }
    }
}