package com.example.ootw.adapter

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ootw.PostActivity
import com.example.ootw.R
import com.example.ootw.api.SearchMonthServiceCreator
import com.example.ootw.data.response.ResponseSearchMonthData
import com.example.ootw.model.SearchData
import kotlinx.android.synthetic.main.list_item_day.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class AdapterDay(val tempMonth:Int, val dayList: MutableList<Date>): RecyclerView.Adapter<AdapterDay.DayView>() {
    val ROW = 6

    inner class DayView(val layout: View): RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayView {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_day, parent, false)
        return DayView(view)
    }

    override fun onBindViewHolder(holder: DayView, position: Int) {
        holder.layout.item_day_layout.setOnClickListener {
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
                        // Toast.makeText(this@CalendarActivity, "게시물 get 완료!!", Toast.LENGTH_SHORT).show()
                        val new = Intent(holder.layout.item_day_layout?.context, PostActivity::class.java)
                        ContextCompat.startActivity(holder.layout.item_day_layout.context, new, null)
                        // var arr1 = itemdata1?.createdAt?.split("T")

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

            // Toast.makeText(holder.layout.context, "${dayList[position]}", Toast.LENGTH_SHORT).show()

        }
        holder.layout.item_day_text.text = dayList[position].date.toString()

        holder.layout.item_day_text.setTextColor(when(position % 7) {
            0 -> Color.RED
            6 -> Color.BLUE
            else -> Color.BLACK
        })

        if(tempMonth != dayList[position].month) {
            holder.layout.item_day_text.alpha = 0.4f
        }
    }

    override fun getItemCount(): Int {
        return ROW * 7
    }
}