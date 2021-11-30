package com.example.wannamovie.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.wannamovie.R
import com.example.wannamovie.common.Constants2
import com.example.wannamovie.databinding.ActivityMainBinding
import com.example.wannamovie.presentation.login.LoginViewModel
import com.example.wannamovie.presentation.main.tab.home.HomeFragment
import com.example.wannamovie.presentation.main.tab.mypage.MyPageFragment
import com.example.wannamovie.presentation.main.tab.search.SearchFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : LoginViewModel by viewModel()

    val homeFragment = HomeFragment()
    val searchFragment = SearchFragment()
    val myPageFragment = MyPageFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest","main activity onCreate")

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        initBottomNavigation()

        binding.bottomNavigationView.selectedItemId = R.id.wm_home


    }

    fun initBottomNavigation(){

        var menu = binding.bottomNavigationView.menu

        // 아이콘 변경 정상적으로 되게 하기 위함
        //binding.bottomNavigationView.itemIconTintList = null

        binding.bottomNavigationView.setOnItemSelectedListener { item ->

            var fragmentManager = supportFragmentManager

            when(item.itemId){
                R.id.wm_home -> {

                    Constants2.BOTTOM_NAVI_NUM = 0

                    //replaceFragment(homeFragment)
                    if (fragmentManager.findFragmentByTag("home") != null) {
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("home")!!).commit()
                    } else {
                        fragmentManager.beginTransaction().add(R.id.framelayout_main, homeFragment, "home").commit()
                    }

                    if (fragmentManager.findFragmentByTag("search") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("search")!!).commit()
                    }
                    if (fragmentManager.findFragmentByTag("mypage") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("mypage")!!).commit()
                    }

                    /*supportFragmentManager.beginTransaction().show(homeFragment).commit()
                    supportFragmentManager.beginTransaction().hide(searchFragment).commit()
                    supportFragmentManager.beginTransaction().hide(communityFragment).commit()
                    supportFragmentManager.beginTransaction().hide(chatFragment).commit()
                    supportFragmentManager.beginTransaction().hide(myPageFragment).commit()*/
                }
                R.id.wm_search -> {
                    Constants2.BOTTOM_NAVI_NUM = 1

                    //replaceFragment(homeFragment)
                    if (fragmentManager.findFragmentByTag("search") != null) {
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("search")!!).commit()
                    } else {
                        fragmentManager.beginTransaction().add(R.id.framelayout_main, searchFragment, "search").commit()
                    }

                    if (fragmentManager.findFragmentByTag("home") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("home")!!).commit()
                    }
                    if (fragmentManager.findFragmentByTag("mypage") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("mypage")!!).commit()
                    }
                }

                R.id.wm_mypage -> {

                    Constants2.BOTTOM_NAVI_NUM = 2

                    //replaceFragment(homeFragment)
                    if (fragmentManager.findFragmentByTag("mypage") != null) {
                        fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("mypage")!!).commit()
                    } else {
                        fragmentManager.beginTransaction().add(R.id.framelayout_main, myPageFragment, "mypage").commit()
                    }

                    if (fragmentManager.findFragmentByTag("home") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("home")!!).commit()
                    }
                    if (fragmentManager.findFragmentByTag("search") != null) {
                        fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("search")!!).commit()
                    }
                }
            }
            true

        }

    }
}