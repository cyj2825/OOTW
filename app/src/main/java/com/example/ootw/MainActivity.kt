package com.example.ootw

import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.ootw.adapter.MainSPAdapter
import com.example.ootw.databinding.ActivityMainBinding
import com.example.ootw.fragment.*
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity: AppCompatActivity() {
    // 전역 변수로 바인딩 객체 선언
    private var mBinding: ActivityMainBinding? = null
    // 매번 null 체크를 할 필요없이 편의성을 위해 바인딩 변수 재선언
    private val binding get() = mBinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnTabSelectListener(object :
            AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                when (newIndex) {
                    0 -> {
                        // val calendarFragment= FriendCalMainFragment()
                        // val calendarFragment= GroupCalMainFragment()
                        val homeFragment = HomeFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_screen_panel, homeFragment).commit()
                    }
                    1 -> {
                        val searchFragment = SearchFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_screen_panel, searchFragment).commit()
                    }

                    2 -> {
                        val closetFragment = ClosetFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_screen_panel, closetFragment).commit()
                    }
                    3 -> {
                        val bookmarkFragment = BookmarkFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_screen_panel, LikeFragment()).commit()
                    }
                    4 -> {
                        val myPageFragment = MyPageFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_screen_panel, myPageFragment).commit()
                    }
                }
            }
        })


//        configureBottomNavigation()
    }

//    private fun configureBottomNavigation(){
//        binding.vpAcMainFragPager.adapter = MainSPAdapter(supportFragmentManager, 5)
//
//        binding.tlAcMainBottomMenu.setupWithViewPager(binding.vpAcMainFragPager)
//
//        val bottomNaviLayout: View = this.layoutInflater.inflate(R.layout.bottom_navigation_tab, null, false)
//
//        binding.tlAcMainBottomMenu.getTabAt(0)!!.customView = bottomNaviLayout.findViewById(R.id.btn_bottom_navi_home_tab) as RelativeLayout
//        binding.tlAcMainBottomMenu.getTabAt(1)!!.customView = bottomNaviLayout.findViewById(R.id.btn_bottom_navi_search_tab) as RelativeLayout
//        binding.tlAcMainBottomMenu.getTabAt(2)!!.customView = bottomNaviLayout.findViewById(R.id.btn_bottom_navi_closet_tab) as RelativeLayout
//        binding.tlAcMainBottomMenu.getTabAt(3)!!.customView = bottomNaviLayout.findViewById(R.id.btn_bottom_navi_bookmark_tab) as RelativeLayout
//        binding.tlAcMainBottomMenu.getTabAt(4)!!.customView = bottomNaviLayout.findViewById(R.id.btn_bottom_navi_my_page_tab) as RelativeLayout
//
//    }

    // 액티비티가 파괴될 때..
    override fun onDestroy() {
        // onDestroy 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        mBinding = null
        super.onDestroy()
    }
}