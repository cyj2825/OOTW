package com.example.ootw.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.ootw.api.SearchItemServiceCreator
import com.example.ootw.api.SearchItemServiceCreator.searchitemService
import com.example.ootw.data.response.ResponseSearchItemData
import com.example.ootw.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("Network", "searchitem submit")
                val item = "'" + "기모 후드티" + "'"  //query.toString()
                Log.d("searchitem", "item 값 => " + item)
                // val callSearchItem = SearchItemServiceCreator.searchitemService.postSearchItem(item)

                searchitemService.postSearchItem(item).enqueue(object : Callback<ResponseSearchItemData> {
                    override fun onResponse(
                        call: Call<ResponseSearchItemData>,
                        response: Response<ResponseSearchItemData>
                    ) {
                        Log.d("datavalue", "data 값 => "+ searchitemService.postSearchItem(item))
                        val data = response.body().toString()
                        Log.d("responsevalue", "response 값1 => "+ data)
                        // 네트워크 통신에 성공한 경우
                        if(response.isSuccessful){
                            Log.d("NetworkTest", "search item success")

                            // 통신 성공시 toast 메시지
                            Toast.makeText(requireContext(), "아이템 서치 완료!!", Toast.LENGTH_SHORT).show()
                        }else{
                            // 이곳은 에러 발생할 경우 실행됨
                            Log.d("NetworkTest", "여긴가?")
                        }
                    }
                    // 네트워크 통신 자체가 실패한 경우 해당 함수를 retrofit이 실행!
                    override fun onFailure(call: Call<ResponseSearchItemData>, t: Throwable) {
                        Log.d("NetworkTest", "아이템 서치 error!")
                    }
                })
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("Network", "searchitem change")
                return false
            }
        })
        return binding.root
    }
}