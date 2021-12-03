package com.example.wannamovie.presentation.moviedetail

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.wannamovie.R
import com.example.wannamovie.databinding.ActivityMainBinding
import com.example.wannamovie.databinding.ActivityMovieDetailBinding
import com.example.wannamovie.presentation.moviedetail.commentpage.MovieCommentActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel : MovieDetailViewModel by viewModel()

    private var MOVIE_ID = 0
    private var REQUEST_COMMENT = ""

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
        Log.e("AppTest", "MovieDetailActivity/ 선택한 영화 id : ${MOVIE_ID}")
        //////////////////////////////////////////////////////////////////


        // 영화 id로 상세페이지 조회하기, 조회수 증가
        viewModel.getMovieDetailInfo(MOVIE_ID)
        viewModel.increaseMovieVisit(MOVIE_ID)
        ////////////////////////////////////////////////////////////////

        viewModel.progressbarVisbility.observe(this, {
            if(it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })

        viewModel.IsGetMovieDetailSuccess.observe(this, {
            if(it){
                Log.e("AppTest", "MovieDetailActivity/ 상세정보 조회 성공, 화면 업데이트")

                binding.tvMovieTitle.text = viewModel.Movie_Title
                binding.tvMovieReleaseDate.text = viewModel.Movie_ReleaseDate
                binding.tvMovieRequestNum.text = viewModel.Movie_Request_Num.toString()

                Glide.with(this)
                        .load(viewModel.Movie_Poster_Url)
                        .into(binding.ivMoviePoster)

                if(viewModel.Is_Request)
                    binding.ivIsRequest.setImageResource(R.drawable.ic_request_yes)
                else
                    binding.ivIsRequest.setImageResource(R.drawable.ic_request_no)
            }
            else{
                Log.e("AppTest", "MovieDetailActivity/ 상세정보 조회 실패")
            }
        })
        ///////////////////////////

        // 코멘트 내용 입력 받기
        binding.etRequestComment.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etRequestComment.text.toString()

                if(userinput.length > 0)
                    REQUEST_COMMENT = userinput
                else
                    REQUEST_COMMENT = ""
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })



        // 재개봉 요청하기
        binding.btnRequestMovie.setOnClickListener {
            if(viewModel.Is_Request)
                Toast.makeText(this, "이미 요청한 영화입니다.", Toast.LENGTH_SHORT).show()
            else{

                // 코멘트 내용 보내서 요청 통신하기
                viewModel.requestMovie(MOVIE_ID, REQUEST_COMMENT)

                binding.etRequestComment.text.clear() // 내용 없어지는게 깔끔해 보임
            }
        }

        viewModel.progressbarVisbility_movieRequest.observe(this, {
            if(it)
                binding.loadingProgressBarRequestMovie.visibility = View.VISIBLE
            else
                binding.loadingProgressBarRequestMovie.visibility = View.INVISIBLE
        })

        viewModel.IsRequestMovieSuccess.observe(this, {
            if(it){
                Log.e("AppTest", "재개봉 요청 성공 ->  영화 상세 정보 조회 업데이트")
                Toast.makeText(this, "재개봉 요청이 등록되었습니다.", Toast.LENGTH_SHORT).show()

                // 요청 반영한 영화 상세정보 다시 가져오기
                viewModel.getMovieDetailInfo(MOVIE_ID)
            }
            else{
                Log.e("AppTest", "재개봉 요청 실패")
            }
        })

        //////////////////////////////////////////////////////////////////////////////////////////////


        // 댓글 읽기/쓰기로 이동
        binding.btnWriteMovieComment.setOnClickListener {
            Log.e("AppTest", "MovieDetail -> MovieComment  btn clicked")
            val intent = Intent(this, MovieCommentActivity::class.java)
            intent.putExtra("movieId", MOVIE_ID)
            startActivity(intent)
        }

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