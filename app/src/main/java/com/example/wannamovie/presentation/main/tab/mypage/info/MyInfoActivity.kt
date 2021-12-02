package com.example.wannamovie.presentation.main.tab.mypage.info

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.wannamovie.R
import com.example.wannamovie.common.Constants2
import com.example.wannamovie.databinding.ActivityMyinfoBinding

class MyInfoActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMyinfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest","myinfo activity onCreate")

        binding = DataBindingUtil.setContentView<ActivityMyinfoBinding>(this, R.layout.activity_myinfo)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 기본제목 없애기
        supportActionBar!!.setTitle("내 정보")
        /////////////////////

        // 유저 정보 가져오기
        binding.etName.setText(Constants2.USER_NAME)
        binding.etAge.setText(Constants2.USER_AGE.toString())
        binding.etSex.setText(Constants2.USER_SEX)
        binding.etAddress.setText(Constants2.USER_ADDRESS)
        binding.etId.setText(Constants2.USER_EMAIL)


    }


    // toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                // 뒤로가기 버튼 누를 시
                Log.e("AppTest", "toolbar back btn clicked")
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}