package com.example.ootw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.ootw.adapter.AdapterMonth
import com.example.ootw.adapter.GridRecyclerAdapter
import com.example.ootw.api.SearchItemServiceCreator
import com.example.ootw.api.SearchMonthServiceCreator
import com.example.ootw.data.response.ResponseSearchItemData
import com.example.ootw.data.response.ResponseSearchMonthData
import com.example.ootw.databinding.ActivityCalendarBinding
import com.example.ootw.model.SearchData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CalendarActivity : AppCompatActivity() {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityCalendarBinding? = null
    // 매번 null 체크를 할 필요없이 편의성을 위해 바인딩 변수 재선언
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩
        mBinding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val monthListManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val monthListAdapter = AdapterMonth()

        binding.calendarCustom.apply {
            layoutManager = monthListManager
            adapter = monthListAdapter
            scrollToPosition(Int.MAX_VALUE/2)
        }
        val snap = PagerSnapHelper()
        snap.attachToRecyclerView(binding.calendarCustom)

        binding.ivCalendarBack.setOnClickListener {
            onBackPressed()
        }

        val item = Calendar.MONTH+5
        Log.d("searchmonth", "item 값 => " + item)
        // val callSearchItem = SearchItemServiceCreator.searchitemService.postSearchItem(item)

        SearchMonthServiceCreator.searchmonthService.postSearchMonth(item).enqueue(object :
            Callback<ResponseSearchMonthData> {
            override fun onResponse(
                call: Call<ResponseSearchMonthData>,
                response: Response<ResponseSearchMonthData>
            ) {
                Log.d("datavalue_searchmonth", "data 값 => "+ SearchMonthServiceCreator.searchmonthService.postSearchMonth(item))
                val data = response.body().toString()
                // val itemdata1 = response.body()?.posts?.get(0)
                // val itemdata2 = response.body()?.posts?.get(1)
                Log.d("responsevalue_searchmonth", "response 값 => "+ data)
                // 네트워크 통신에 성공한 경우
                if(response.isSuccessful){
                    Log.d("NetworkTest", "searchmonth success")

                    // 통신 성공시 toast 메시지
                    Toast.makeText(this@CalendarActivity, "게시물 get 완료!!", Toast.LENGTH_SHORT).show()
                    // var arr1 = itemdata1?.createdAt?.split("T")
                    // var arr2 = itemdata2?.createdAt?.split("T")

                }else{
                    // 이곳은 에러 발생할 경우 실행됨
                    Log.d("NetworkTest_searchmonth", "여긴가?")
                }
            }
            // 네트워크 통신 자체가 실패한 경우 해당 함수를 retrofit이 실행!
            override fun onFailure(call: Call<ResponseSearchMonthData>, t: Throwable) {
                Log.d("NetworkTest_searchmonth", "monthitem error!")
            }
        })
    }

    // 액티비티가 파괴될 때..
    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }
}