package com.example.ootw.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ootw.R
import com.example.ootw.adapter.PostAdapter
import com.example.ootw.databinding.FragmentMyFeedAllBinding
import com.example.ootw.databinding.FragmentMyFeedSpringBinding
import com.example.ootw.model.SearchData

class MyFeedSpringFragment : Fragment() {
    lateinit var binding: FragmentMyFeedSpringBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyFeedSpringBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 우리가 사용할 어뎁터의 초기값을 넣어줌
        postAdapter = PostAdapter()
        // RecyclerView에 어뎁터를 우리가 만든 어뎁터로!
        binding.rvClosetfeedspring.adapter = postAdapter

        //add data
        postAdapter.postdataList.addAll(
            listOf<SearchData>(
                SearchData(
                    "구경해보실래요?",
                    R.drawable.tshirt2,
                    "yejin",
                    R.drawable.emptyheart,
                    "2022-07-11",
                    "24",
                    "바지",
                    "모두 와서 구경해보세요~"),
                SearchData(
                    "반팔티 필수 아이템!",
                    R.drawable.tshirt3,
                    "yejin",
                    R.drawable.fullheart,
                    "2022-07-18",
                    "28",
                    "반팔티",
                    "반팔티 너무 예쁘지 않나요?? 하나 꼭 장만해야할 아이템!")
            )
        )
        postAdapter.notifyDataSetChanged()
    }

}