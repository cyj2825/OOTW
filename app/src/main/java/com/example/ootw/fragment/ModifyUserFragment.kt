package com.example.ootw.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.ootw.PostActivity
import com.example.ootw.R
import com.example.ootw.databinding.FragmentHomeBinding
import com.example.ootw.databinding.FragmentModifyUserBinding

class ModifyUserFragment : Fragment() {
    private lateinit var callback: OnBackPressedCallback
    lateinit var binding: FragmentModifyUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModifyUserBinding.inflate(inflater, container, false)

        binding.ivModifyUserProfileImg.setOnClickListener {
            val intent = Intent(requireContext(), PostActivity::class.java)
            startActivity(intent)
            requireActivity().overridePendingTransition(0, 0)
        }

        // 뒤로가기 imageView 클릭시
        binding.ivModifyUserBack.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.main_screen_panel, MyPageFragment())?.commit()
        }

        return binding.root
    }

//  핸드폰 뒤로가기 실행시
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