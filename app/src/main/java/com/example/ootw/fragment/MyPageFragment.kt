package com.example.ootw.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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
        val view = inflater.inflate(R.layout.fragment_my_page, container, false)
        val btn_notice: TextView = view.findViewById(R.id.tv_my_page_notice)
        btn_notice.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_my_page_notice -> {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.vp_ac_main_frag_pager, NoticeFragment()).commitNow()
            }

            else -> {
            }
        }
    }
}