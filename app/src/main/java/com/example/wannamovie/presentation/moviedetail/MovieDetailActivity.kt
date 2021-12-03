package com.example.wannamovie.presentation.moviedetail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.wannamovie.R
import com.example.wannamovie.databinding.ActivityMainBinding
import com.example.wannamovie.databinding.ActivityMovieDetailBinding
import com.example.wannamovie.databinding.ActivityWelcomeBinding

class MovieDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private var MOVIE_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("AppTest", "MovieDetailActivity/ onCreate")

        binding = DataBindingUtil.setContentView<ActivityMovieDetailBinding>(this, R.layout.activity_movie_detail)


        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 기본제목 없애기 = false
        supportActionBar!!.setTitle("영화 상세정보")
        /////////////////////

        // 선택한 영화 id 받기
        MOVIE_ID = intent.getIntExtra("movieId", 0)
        Log.e("AppTest", "선택한 영화 id : ${MOVIE_ID}")
        //////////////////////////////////////////////////////////////////







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