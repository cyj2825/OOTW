package com.example.ootw.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import com.example.ootw.R
import com.example.ootw.databinding.FragmentNoticeBinding

class NoticeFragment : Fragment() {
    private lateinit var callback: OnBackPressedCallback
    lateinit var binding: FragmentNoticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoticeBinding.inflate(inflater, container, false)

        // 뒤로가기 imageView 클릭시
        binding.ivNoticeBack.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.main_screen_panel, MyPageFragment())?.commit()
        }

        return binding.root
    }

//  뒤로가기 버튼 실행시
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("TestLog", "백프레스")
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.main_screen_panel, MyPageFragment())?.commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}