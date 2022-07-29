package com.example.ootw.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.commit
import com.example.ootw.LoginActivity
import com.example.ootw.R
import com.example.ootw.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(inflater, container, false)
        binding.tvMyPageUpdate.setOnClickListener(this)
        binding.tvMyPageNotice.setOnClickListener(this)
        binding.tvMyPageLogout.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_my_page_update -> {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_screen_panel, ModifyUserFragment()).commitNow()
            }
            R.id.tv_my_page_notice -> {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_screen_panel, NoticeFragment()).commitNow()
            }
            R.id.tv_my_page_logout -> {
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().overridePendingTransition(0, 0)
                Toast.makeText(requireContext(), "로그아웃", Toast.LENGTH_SHORT).show()
            }

            else -> {
            }
        }
    }

}