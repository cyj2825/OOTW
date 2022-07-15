package com.example.ootw.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ootw.R
import com.example.ootw.adapter.PostListRVAdapter
import com.example.ootw.model.PostDTO

class BookmarkFragment : Fragment() {

    private var postList = arrayListOf<PostDTO>(
        PostDTO("test1", "user1", "img1", "27", "icon1", "성북구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
        PostDTO("test2", "user2", "img1", "27", "icon1", "영등포구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
        PostDTO("test3", "user3", "img1", "27", "icon1", "도봉구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
        PostDTO("test4", "user4", "img1", "27", "icon1", "노원구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
        PostDTO("test5", "user5", "img1", "27", "icon1", "영등포구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
        PostDTO("test6", "user6", "img1", "27", "icon1", "중구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
        PostDTO("test7", "user7", "img1", "27", "icon1", "강남구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
        PostDTO("test8", "user8", "img1", "27", "icon1", "송파구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
        PostDTO("test9", "user9", "img1", "27", "icon1", "서초구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
        PostDTO("test10", "user10", "img1", "27", "icon1", "양천구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
        PostDTO("test11", "user11", "img1", "27", "icon1", "강서구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),
        PostDTO("test12", "user12", "img1", "27", "icon1", "금천구", "상의", "나시", "오늘은 참 덥네요!", "22.07.15"),

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bookmark, container, false)
        val mAdapter = PostListRVAdapter(requireContext(), postList)
        val recyvler_view =  view.findViewById<RecyclerView>(R.id.rv_Bookmark_post)
        recyvler_view.adapter = mAdapter
        val gridLayoutManager = GridLayoutManager(activity?.applicationContext, 3)
        recyvler_view.layoutManager = gridLayoutManager
        return view
    }


}