package com.example.wannamovie.presentation.moviedetail.commentpage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wannamovie.R
import com.example.wannamovie.common.util.VerticalItemDecorator
import com.example.wannamovie.data.remote.dto.response.home.HomeMovieResponseDto
import com.example.wannamovie.data.remote.dto.response.search.CommentResponseDto
import com.example.wannamovie.databinding.ActivityMovieCommentBinding
import com.example.wannamovie.databinding.ActivityMovieDetailBinding
import com.example.wannamovie.presentation.main.tab.home.adapter.HomeAgeRVadapter
import com.example.wannamovie.presentation.moviedetail.MovieDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MovieCommentActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMovieCommentBinding
    private val viewModel : MovieCommentViewModel by viewModel()

    lateinit var rvAdapter: MovieCommentRVadpater
    private var testDataSet = mutableListOf<CommentResponseDto>()


    private var MOVIE_ID = 0
    private var COMMENT = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest", "MovieCommentActivity/ onCreate")

        binding = DataBindingUtil.setContentView<ActivityMovieCommentBinding>(this, R.layout.activity_movie_comment)


        // 선택한 영화 id 받기
        MOVIE_ID = intent.getIntExtra("movieId", 0)
        Log.e("AppTest", "MovieCommentActivity/ 선택한 영화 id : ${MOVIE_ID}")
        //////////////////////////////////////////////////////////////////

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 기본제목 없애기 = false
        supportActionBar!!.setTitle("댓글 쓰기")
        /////////////////////

        // RV
        rvAdapter = MovieCommentRVadpater(testDataSet)

        binding.rvMovieComment.layoutManager = LinearLayoutManager(this)
        binding.rvMovieComment.adapter = rvAdapter
        binding.rvMovieComment.addItemDecoration(VerticalItemDecorator(14))

        ///////////////////////////////////////////////////////////

        //시작 시 댓글들 가져오기
        viewModel.getMovieDetailInfo(MOVIE_ID)

        viewModel.IsGetMovieDetailSuccess.observe(this, {
            if(it){
                Log.e("AppTest", "MovieCommentActivity/ 영화 상세 정보 조회 후 댓글 리스트 가져오기 성공 -> RV 업데이트")
                rvAdapter.updateList(viewModel.CommentList)
            }
            else{
                Log.e("AppTest", "MovieCommentActivity/ 영화 상세 정보 조회 실패")
            }
        })

        viewModel.progressbarVisibility.observe(this,{
            if(it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })

        ///////////////////////////////////////////////////////////////////////

        // 댓글 내용 입력 받기
        binding.etComment.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var userinput = binding.etComment.text.toString()

                if(userinput.length > 0)
                    COMMENT = userinput
                else
                    COMMENT = ""
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        // 입력 내용으로 댓글 쓰기
        binding.btnWriteComment.setOnClickListener {
            viewModel.writeComment(MOVIE_ID, COMMENT)

            binding.etComment.text.clear() // 입력 내용 없어지는게 깔끔해 보임
        }

        viewModel.IsWriteCommentSuccess.observe(this, {
            if(it){
                Log.e("AppTest", "댓글 등록 성공 ->  영화 상세 정보 조회 후 댓글 상태 업데이트")
                Toast.makeText(this, "댓글이 등록되었습니다.", Toast.LENGTH_SHORT).show()

                viewModel.getMovieDetailInfo(MOVIE_ID)  // 댓글 등록 후 반영을 위해 영화 상세정 보 재호출
            }
            else{
                Log.e("AppTest", "댓글 등록 실패")
            }
        })

        viewModel.progressbarVisibility_writeComment.observe(this, {
            if(it)
                binding.loadingProgressBarWriteComment.visibility = View.VISIBLE
            else
                binding.loadingProgressBarWriteComment.visibility = View.INVISIBLE
        })

        //////////////////////////////////////////////////////////////////////////////


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