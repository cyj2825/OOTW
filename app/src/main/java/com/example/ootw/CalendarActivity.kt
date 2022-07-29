package com.example.ootw

import android.content.Intent
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
    }

    // 액티비티가 파괴될 때..
    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }
}