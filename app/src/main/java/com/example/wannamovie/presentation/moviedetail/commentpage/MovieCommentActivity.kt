package com.example.wannamovie.presentation.moviedetail.commentpage

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.wannamovie.R
import com.example.wannamovie.databinding.ActivityMovieCommentBinding
import com.example.wannamovie.databinding.ActivityMovieDetailBinding

class MovieCommentActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMovieCommentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest", "MovieCommentActivity/ onCreate")

        binding = DataBindingUtil.setContentView<ActivityMovieCommentBinding>(this, R.layout.activity_movie_comment)


        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 기본제목 없애기 = false
        supportActionBar!!.setTitle("댓글 쓰기")
        /////////////////////







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