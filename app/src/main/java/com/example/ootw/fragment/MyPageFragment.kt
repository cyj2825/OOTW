package com.example.ootw.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.commit
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
        view.findViewById<TextView>(R.id.tv_my_page_update).setOnClickListener(this)
        view.findViewById<TextView>(R.id.tv_my_page_notice).setOnClickListener(this)
        return view
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

            else -> {
            }
        }
    }

}