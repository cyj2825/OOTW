package com.example.ootw.adapter

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.ootw.fragment.*

class MainSPAdapter(fm : FragmentManager, val fragmentCount : Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return HomeFragment()
            1 -> return SearchFragment()
            2 -> return ClosetFragment()
            3 -> return BookmarkFragment()
            4 -> return MyPageFragment()
            else -> throw IllegalStateException("position $position is invalid for this viewpager")
        }
    }

    override fun getCount(): Int = fragmentCount

}