package com.example.ootw.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ootw.R
import com.example.ootw.adapter.PostAdapter
import com.example.ootw.databinding.FragmentMyFeedAllBinding
import com.example.ootw.databinding.FragmentMyFeedSummerBinding
import com.example.ootw.model.SearchData

class MyFeedSummerFragment : Fragment() {
    lateinit var binding: FragmentMyFeedSummerBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyFeedSummerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 우리가 사용할 어뎁터의 초기값을 넣어줌
        postAdapter = PostAdapter()
        // RecyclerView에 어뎁터를 우리가 만든 어뎁터로!
        binding.rvClosetfeedsummer.adapter = postAdapter

        //add data
        postAdapter.postdataList.addAll(
            listOf<SearchData>(
                SearchData(
                    "제목5",
                    R.drawable.tshirt2,
                    "jeehee",
                    "1111",
                    "21",
                    "바지",
                    "모두 와서 구경해보세요~"),
                SearchData(
                    "제목6",
                    R.drawable.tshirt3,
                    "jun",
                    "222",
                    "28",
                    "안경",
                    "ㄴㅇㄹㄴㅇㄹㄴㅇㄹ")
            )
        )
        postAdapter.notifyDataSetChanged()
    }
}