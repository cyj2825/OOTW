package com.example.ootw.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ootw.R
import com.example.ootw.databinding.ListItemSearchBinding
import com.example.ootw.model.SearchData

// Adapter는 Recyclerview.Adapter를 상속받음
// <ViewHolder> 부분으로 해당 어뎁터가 어떤 ViewHolder로 변경하는지 알려줌
class GridRecyclerAdapter : RecyclerView.Adapter<GridRecyclerAdapter.ViewHolder>() {
    // Adapter는 ViewHolder로 변경할 데이터를 가지고 있어야 함
    val dataList = mutableListOf<SearchData>()

    // Adapter는 아이템마다 ViewHolder를 만드는 방법을 정의해야 함
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemSearchBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    // Adapter는 ViewHolder에 Data를 전달하는 방법을 정의해야 함
    override fun onBindViewHolder(holder: GridRecyclerAdapter.ViewHolder, position: Int) {
        holder.onBind(dataList[position])
    }
    // Adapter는 전체 아이템의 수를 알아야 함
    override fun getItemCount(): Int = dataList.size

    class ViewHolder(private val binding: ListItemSearchBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(searchData: SearchData){
            binding.tvSearchTitle.text = searchData.title
            binding.ivSearchFeedphoto.setImageResource(searchData.img)
            binding.tvSearchName.text = searchData.writer
            binding.tvSearchCreate.text = searchData.create
            binding.tvSearchTemp.text = searchData.temp
            binding.tvSearchClassify.text = searchData.item
            binding.tvSearchContent.text = searchData.body
        }
    }
}