package com.example.ootw.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ootw.databinding.ListItemSearchBinding
import com.example.ootw.model.SearchData

// Adapter는 Recyclerview.Adapter를 상속받음
// <ViewHolder> 부분으로 해당 어뎁터가 어떤 ViewHolder로 변경하는지 알려줌
class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    // Adapter는 ViewHolder로 변경할 데이터를 가지고 있어야 함
    val postdataList = mutableListOf<SearchData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        val binding = ListItemSearchBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return PostAdapter.ViewHolder(binding)
    }
    // Adapter는 ViewHolder에 Data를 전달하는 방법을 정의해야 함
    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        holder.onBind(postdataList[position])
    }
    // Adapter는 전체 아이템의 수를 알아야 함
    override fun getItemCount(): Int = postdataList.size

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