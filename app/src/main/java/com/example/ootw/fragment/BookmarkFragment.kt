package com.example.ootw.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ootw.R
import com.example.ootw.model.Post
import kotlinx.android.synthetic.main.fragment_bookmark.view.*


class BookmarkFragment : Fragment() {
    var fragmentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.fragment_bookmark, container, false)
        fragmentView?.rv_Bookmark_post?.adapter = BookmarkFragmentRecyclerViewAdapter()
        fragmentView?.rv_Bookmark_post?.layoutManager = GridLayoutManager(activity, 3)
        return fragmentView
    }

    inner class BookmarkFragmentRecyclerViewAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var postDTOs = arrayListOf<Post>(
            Post("test1", "user1", "", "27", "", "성북구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
            Post("test2", "user2", "", "27", "", "영등포구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
            Post("test3", "user3", "", "27", "", "도봉구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
            Post("test4", "user4", "", "27", "", "노원구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
            Post("test5", "user5", "", "27", "", "영등포구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
            Post("test6", "user6", "", "27", "", "중구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
            Post("test7", "user7", "", "27", "", "강남구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
            Post("test8", "user8", "", "27", "", "송파구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
            Post("test9", "user9", "", "27", "", "서초구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
            Post("test10", "user10", "", "27", "", "양천구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
            Post("test11", "user11", "", "27", "", "강서구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
            Post("test12", "user12", "", "27", "", "금천구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
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
            Glide.with(holder.itemView.context).load(postDTOs[position].imageUrl).apply(
                RequestOptions().centerCrop()).into(imageview)
            Log.d("bookmark", "post: "+postDTOs[position].uid)

        }
    }


//    그리드 화면 출력 안됨.............
//    아래 코드 사용 안할시 PostListRVAdapter 삭제 필요

//    var postList = arrayListOf<PostDTO>(
//            PostDTO("test1", "user1", "", "27", "", "성북구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
//            PostDTO("test2", "user2", "", "27", "", "영등포구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
//            PostDTO("test3", "user3", "", "27", "", "도봉구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
//            PostDTO("test4", "user4", "", "27", "", "노원구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
//            PostDTO("test5", "user5", "", "27", "", "영등포구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
//            PostDTO("test6", "user6", "", "27", "", "중구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
//            PostDTO("test7", "user7", "", "27", "", "강남구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
//            PostDTO("test8", "user8", "", "27", "", "송파구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
//            PostDTO("test9", "user9", "", "27", "", "서초구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
//            PostDTO("test10", "user10", "", "27", "", "양천구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
//            PostDTO("test11", "user11", "", "27", "", "강서구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
//            PostDTO("test12", "user12", "", "27", "", "금천구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
//        )
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_bookmark, container, false)
//        val mAdapter = PostListRVAdapter(requireContext(), postList)
//        val recyvler_view =  view.findViewById<RecyclerView>(R.id.rv_Bookmark_post)
//        recyvler_view.adapter = mAdapter
//        val gridLayoutManager = GridLayoutManager(activity?.applicationContext, 3)
//        recyvler_view.layoutManager = gridLayoutManager
//
//        Log.d("bookmark", "onCreateView: grid layout called!")
//        return view
//    }

//    lateinit var binding: FragmentBookmarkBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.rvBookmarkPost.adapter = PostListRVAdapter(context, postList)
//    }
}