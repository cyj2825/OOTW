package com.example.ootw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ootw.adapter.PostAdapter
import com.example.ootw.api.SearchItemServiceCreator
import com.example.ootw.data.response.ResponseSearchItemData
import com.example.ootw.databinding.ActivityLoginBinding
import com.example.ootw.databinding.ActivityPostListBinding
import com.example.ootw.model.SearchData
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostListActivity : AppCompatActivity() {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityPostListBinding? = null
    // 매번 null 체크를 할 필요없이 편의성을 위해 바인딩 변수 재선언
    private val binding get() = mBinding!!
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩
        mBinding = ActivityPostListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("Network", "homeitem submit")
        val item = "얇은 셔츠"
        Log.d("homeitem", "item 값 => " + item)

        SearchItemServiceCreator.searchitemService.postSearchItem(item).enqueue(object :
            Callback<ResponseSearchItemData> {
            override fun onResponse(
                call: Call<ResponseSearchItemData>,
                response: Response<ResponseSearchItemData>
            ) {
                Log.d("datavalue_homeitem", "data 값 => "+ SearchItemServiceCreator.searchitemService.postSearchItem(item))
                val data = response.body().toString()
                // val itemdata1 = response.body()?.posts?.get(0)
                // val itemdata2 = response.body()?.posts?.get(1)
                Log.d("responsevalue_homeitem", "response 값 => "+ data)
                // 네트워크 통신에 성공한 경우
                if(response.isSuccessful){
                    Log.d("NetworkTest", "homeitem success")

                    // 통신 성공시 toast 메시지
                    Toast.makeText(this@PostListActivity, "홈 아이템 완료!!", Toast.LENGTH_SHORT).show()
                    // var arr1 = itemdata1?.createdAt?.split("T")
                    // var arr2 = itemdata2?.createdAt?.split("T")
                    // 우리가 사용할 어뎁터의 초기값을 넣어줌
                    postAdapter = PostAdapter()
                    // RecyclerView에 어뎁터를 우리가 만든 어뎁터로!
                    binding.rvPostList.adapter = postAdapter

                    //add data
                    postAdapter.postdataList.addAll(
                        listOf<SearchData>(
                            SearchData(
                                "제목",
                                R.drawable.hoodt3,
                                "suheon",
                                R.drawable.fullheart,
                                "2343",
                                "21",
                                "ㄴㅇㄹ",
                                "324234"
                            )
                        )
                    )
                    postAdapter.notifyDataSetChanged()

                }else{
                    // 이곳은 에러 발생할 경우 실행됨
                    Log.d("NetworkTest_home item", "여긴가?")
                }
            }
            // 네트워크 통신 자체가 실패한 경우 해당 함수를 retrofit이 실행!
            override fun onFailure(call: Call<ResponseSearchItemData>, t: Throwable) {
                Log.d("NetworkTest", "홈 아이템 error!")
            }
        })
    }
}