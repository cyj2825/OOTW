package com.example.ootw.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.example.ootw.adapter.GridRecyclerAdapter
import com.example.ootw.api.SearchItemServiceCreator
import com.example.ootw.api.SearchItemServiceCreator.searchitemService
import com.example.ootw.data.response.ResponseSearchItemData
import com.example.ootw.databinding.FragmentSearchBinding
import com.example.ootw.model.SearchData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ootw.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding
    private lateinit var gridRecyclerAdapter: GridRecyclerAdapter
    var data = ""

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
                val item = query.toString()
                Log.d("searchitem", "item 값 => " + item)
                // val callSearchItem = SearchItemServiceCreator.searchitemService.postSearchItem(item)

                searchitemService.postSearchItem(item).enqueue(object : Callback<ResponseSearchItemData> {
                    override fun onResponse(
                        call: Call<ResponseSearchItemData>,
                        response: Response<ResponseSearchItemData>
                    ) {
                        Log.d("datavalue", "data 값 => "+ searchitemService.postSearchItem(item))
                        data = response.body().toString()
                        val itemdata1 = response.body()?.posts?.get(0)
                        val itemdata2 = response.body()?.posts?.get(1)
                        Log.d("responsevalue", "response 값1 => "+ data)
                        // 네트워크 통신에 성공한 경우
                        if(response.isSuccessful){
                            Log.d("NetworkTest", "search item success")

                            // 통신 성공시 toast 메시지
//                            Toast.makeText(requireContext(), "아이템 서치 완료!!", Toast.LENGTH_SHORT).show()
                            var arr1 = itemdata1?.createdAt?.split("T")
                            var arr2 = itemdata2?.createdAt?.split("T")

                            // 우리가 사용할 어뎁터의 초기값을 넣어줌
                            gridRecyclerAdapter = GridRecyclerAdapter()
                            // RecyclerView에 어뎁터를 우리가 만든 어뎁터로!
                            binding.rvSearch.adapter = gridRecyclerAdapter
                            // rv_search.layoutManager = GridLayoutManager(requireContext(),2)

                            //add data
                            gridRecyclerAdapter.dataList.addAll(
                                listOf<SearchData>(
                                    SearchData(
                                        itemdata1!!.title,
                                        R.drawable.shirt,
                                        "jeehee",
                                        R.drawable.fullheart,
                                        arr1!!.get(0),
                                        itemdata1!!.temp.toString(),
                                        itemdata1!!.item,
                                        itemdata1!!.body),
                                    SearchData(
                                        itemdata2!!.title,
                                        R.drawable.shirt2,
                                        "jun",
                                        R.drawable.emptyheart,
                                        arr2!!.get(0),
                                        itemdata2!!.temp.toString(),
                                        itemdata2!!.item,
                                        itemdata2!!.body)
                                )

                            )
                            gridRecyclerAdapter.notifyDataSetChanged()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}