package com.example.ootw.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ootw.R
import com.example.ootw.adapter.PostAdapter
import com.example.ootw.databinding.FragmentMyFeedAllBinding
import com.example.ootw.databinding.FragmentMyFeedAutumnBinding
import com.example.ootw.model.SearchData

class MyFeedAutumnFragment : Fragment() {
    lateinit var binding: FragmentMyFeedAutumnBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyFeedAutumnBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 우리가 사용할 어뎁터의 초기값을 넣어줌
        postAdapter = PostAdapter()
        // RecyclerView에 어뎁터를 우리가 만든 어뎁터로!
        binding.rvClosetfeedautumn.adapter = postAdapter

        //add data
        postAdapter.postdataList.addAll(
            listOf<SearchData>(
                SearchData(
                    "안녕",
                    R.drawable.tshirt,
                    "yejin",
                    R.drawable.fullheart,
                    "2021-10-12",
                    "18",
                    "바지",
                    "모두 와서 구경해보세요~"),
                SearchData(
                    "너무 예쁘다",
                    R.drawable.hoodt,
                    "yejin",
                    R.drawable.emptyheart,
                    "2021-09-14",
                    "21",
                    "후드티",
                    "후드티 진짜 내 마음에 쏙!!")
            )
        )
        postAdapter.notifyDataSetChanged()
    }
}