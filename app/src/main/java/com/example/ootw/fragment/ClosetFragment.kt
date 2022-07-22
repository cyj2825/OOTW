package com.example.ootw.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.ootw.CalendarActivity
import com.example.ootw.CreatePostActivity
import com.example.ootw.R

class ClosetFragment : Fragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_closet, container, false)
        view.findViewById<Button>(R.id.btn_Closet_plus).setOnClickListener(this)
        view.findViewById<Button>(R.id.btn_Closet_Calendar).setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_Closet_plus -> {
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.main_screen_panel, CreatePostFragment()).commitNow()
                val intent = Intent(requireContext(), CreatePostActivity::class.java)
                startActivity(intent)
                requireActivity().overridePendingTransition(0, 0)
            }

            R.id.btn_Closet_Calendar -> {
                val intent = Intent(requireContext(), CalendarActivity::class.java)
                startActivity(intent)
                requireActivity().overridePendingTransition(0, 0)
            }

            else -> {
            }
        }
    }
}