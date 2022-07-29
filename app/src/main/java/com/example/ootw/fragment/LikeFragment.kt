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
import com.example.ootw.api.PostLikeServiceCreator
import com.example.ootw.data.response.ResponsePostLikeData
import com.example.ootw.databinding.FragmentLikeBinding
import com.example.ootw.model.SearchData
import kotlinx.android.synthetic.main.fragment_like.*
import kotlinx.android.synthetic.main.fragment_like.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LikeFragment : Fragment() {
    lateinit var binding: FragmentLikeBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Network_like", "like post")

        PostLikeServiceCreator.postlikeService.getlikePosts(1).enqueue(object :
            Callback<ResponsePostLikeData> {
            override fun onResponse(
                call: Call<ResponsePostLikeData>,
                response: Response<ResponsePostLikeData>
            ) {
                Log.d("datavalue", "like post_data 값 => "+ PostLikeServiceCreator.postlikeService.getlikePosts(1))
                val data = response.body().toString()
                val itemdata1 = response.body()?.posts?.get(0)
                val itemdata2 = response.body()?.posts?.get(1)
                val itemdata3 = response.body()?.posts?.get(2)
                Log.d("responsevalue", "like post_response 값 => "+ data)
                // 네트워크 통신에 성공한 경우
                if(response.isSuccessful){
                    Log.d("NetworkTest", "like post success")

                    // 통신 성공시 toast 메시지
//                    Toast.makeText(requireContext(), "좋아요 게시글 get 완료!!", Toast.LENGTH_SHORT).show()
                    var arr1 = itemdata1?.Post?.createdAt?.split("T")
                    var arr2 = itemdata2?.Post?.createdAt?.split("T")
                    var arr3 = itemdata3?.Post?.createdAt?.split("T")

                    // 우리가 사용할 어뎁터의 초기값을 넣어줌
                    postAdapter = PostAdapter()
                    // RecyclerView에 어뎁터를 우리가 만든 어뎁터로!
                    binding.rvLike.adapter = postAdapter

                    //add data
                    postAdapter.postdataList.addAll(
                        listOf<SearchData>(
                            SearchData(
                                itemdata1!!.Post!!.title,
                                R.drawable.shirt2,
                                "suheon",
                                R.drawable.fullheart,
                                arr1!!.get(0),
                                itemdata1!!.Post!!.temp.toString(),
                                itemdata1!!.Post!!.item,
                                itemdata1!!.Post!!.body),
                            SearchData(
                                itemdata2!!.Post!!.title,
                                R.drawable.shirt3,
                                "myungjun",
                                R.drawable.fullheart,
                                arr2!!.get(0),
                                itemdata2!!.Post!!.temp.toString(),
                                itemdata2!!.Post!!.item,
                                itemdata2!!.Post!!.body),
                            SearchData(
                                itemdata3!!.Post!!.title,
                                R.drawable.nasi,
                                "jeehee",
                                R.drawable.fullheart,
                                arr3!!.get(0),
                                itemdata3!!.Post!!.temp.toString(),
                                itemdata3!!.Post!!.item,
                                itemdata3!!.Post!!.body)
                        )
                    )
                    postAdapter.notifyDataSetChanged()
                }else{
                    // 이곳은 에러 발생할 경우 실행됨
                    Log.d("NetworkTest_like post", "여긴가?")
                }
            }
            // 네트워크 통신 자체가 실패한 경우 해당 함수를 retrofit이 실행!
            override fun onFailure(call: Call<ResponsePostLikeData>, t: Throwable) {
                Log.d("NetworkTest", "좋아요 게시글 get error!")
            }
        })

    }

//  뒤로가기 버튼 실행시
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                Log.d("TestLog", "백프레스")
//                activity?.supportFragmentManager?.beginTransaction()
//                    ?.replace(R.id.main_screen_panel, MyPageFragment())?.commit()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
//    }
}