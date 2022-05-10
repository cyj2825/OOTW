package com.example.ootw.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.ootw.R

class ModifyUserFragment : Fragment() {
    private lateinit var callback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_modify_user, container, false)
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