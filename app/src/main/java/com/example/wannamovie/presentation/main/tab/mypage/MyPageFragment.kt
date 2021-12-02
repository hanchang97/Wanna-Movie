package com.example.wannamovie.presentation.main.tab.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.wannamovie.R
import com.example.wannamovie.common.Constants2
import com.example.wannamovie.databinding.FragmentHomeBinding
import com.example.wannamovie.databinding.FragmentMypageBinding
import com.example.wannamovie.presentation.main.MainActivityViewModel
import com.example.wannamovie.presentation.main.tab.mypage.info.MyInfoActivity
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MyPageFragment: Fragment() {

    private lateinit var binding : FragmentMypageBinding
    private val viewModel : MainActivityViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("AppTest", "mypage fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mypage, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "mypage fragment onViewCreated")


        // 유저 이름 받아오기
        binding.tvUserName.text = Constants2.USER_NAME + "님"


        // 내 정보 activity 로 이동
        binding.llGotoUserinfo.setOnClickListener {
            Log.e("AppTest","MyPageFragment/ go to myinfo activity")
            val intent = Intent(context, MyInfoActivity::class.java)
            startActivity(intent)
        }

    }
}