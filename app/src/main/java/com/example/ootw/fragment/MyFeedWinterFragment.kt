package com.example.ootw.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ootw.R
import com.example.ootw.adapter.PostAdapter
import com.example.ootw.api.PostSearchWriterServiceCreator
import com.example.ootw.data.response.ResponsePostSearchWriterData
import com.example.ootw.databinding.FragmentMyFeedAllBinding
import com.example.ootw.databinding.FragmentMyFeedWinterBinding
import com.example.ootw.model.SearchData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyFeedWinterFragment : Fragment() {
    lateinit var binding: FragmentMyFeedWinterBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyFeedWinterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Network_feed winter", "winter click")

        PostSearchWriterServiceCreator.postsearchwriterService.getwriterPosts(1).enqueue(object :
            Callback<ResponsePostSearchWriterData> {
            override fun onResponse(
                call: Call<ResponsePostSearchWriterData>,
                response: Response<ResponsePostSearchWriterData>
            ) {
                Log.d("datavalue", "feed winter_data 값 => "+ PostSearchWriterServiceCreator.postsearchwriterService.getwriterPosts(1))
                val data = response.body().toString()
                val itemdata1 = response.body()?.posts?.get(0)
                val itemdata2 = response.body()?.posts?.get(1)

                Log.d("responsevalue", "feed winter_response 값 => "+ data)
                // 네트워크 통신에 성공한 경우
                if(response.isSuccessful){
                    Log.d("NetworkTest", "feed winter success")

                    // 통신 성공시 toast 메시지
//                    Toast.makeText(requireContext(), "사용자 게시글 get 완료!!", Toast.LENGTH_SHORT).show()
                    var arr1 = itemdata1?.createdAt?.split("T")
                    var arr2 = itemdata2?.createdAt?.split("T")

                    // 우리가 사용할 어뎁터의 초기값을 넣어줌
                    postAdapter = PostAdapter()
                    // RecyclerView에 어뎁터를 우리가 만든 어뎁터로!
                    binding.rvClosetfeedwinter.adapter = postAdapter

                    //add data
                    postAdapter.postdataList.addAll(
                        listOf<SearchData>(
                            SearchData(
                                itemdata1!!.title,
                                R.drawable.coat,
                                "yejin",
                                R.drawable.emptyheart,
                                arr1!!.get(0),
                                itemdata1!!.temp.toString(),
                                itemdata1!!.item,
                                itemdata1!!.body),
                            SearchData(
                                itemdata2!!.title,
                                R.drawable.hoodt2,
                                "yejin",
                                R.drawable.fullheart,
                                arr2!!.get(0),
                                itemdata2!!.temp.toString(),
                                itemdata2!!.item,
                                itemdata2!!.body),
                        )
                    )
                    postAdapter.notifyDataSetChanged()
                }else{
                    // 이곳은 에러 발생할 경우 실행됨
                    Log.d("NetworkTest_feed winter", "여긴가?")
                }
            }
            // 네트워크 통신 자체가 실패한 경우 해당 함수를 retrofit이 실행!
            override fun onFailure(call: Call<ResponsePostSearchWriterData>, t: Throwable) {
                Log.d("NetworkTest", "사용자 게시글 get error!")
            }
        })
    }
}