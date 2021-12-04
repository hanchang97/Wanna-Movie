package com.example.wannamovie.presentation.main.tab.mypage.reopenlist

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wannamovie.R
import com.example.wannamovie.common.util.VerticalItemDecorator
import com.example.wannamovie.data.remote.dto.response.admin.AdminMovieListResponseDto
import com.example.wannamovie.data.remote.dto.response.admin.ReopenMovieListResponseDto
import com.example.wannamovie.databinding.ActivityMovieCommentBinding
import com.example.wannamovie.databinding.ActivityViewReopenListBinding
import com.example.wannamovie.presentation.admin.AdminMovieListRVadapter
import com.example.wannamovie.presentation.admin.AdminViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ViewReopenMovieListActivity: AppCompatActivity() {

    private lateinit var binding: ActivityViewReopenListBinding
    private val viewModel : ViewReopenViewModel by viewModel()


    lateinit var rvAdapter: ViewReopenRVadapter
    private var testDataSet = mutableListOf<ReopenMovieListResponseDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest", "ViewReopenMovieListActivity/ onCreate")

        binding = DataBindingUtil.setContentView<ActivityViewReopenListBinding>(this, R.layout.activity_view_reopen_list)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 기본제목 없애기 = false
        supportActionBar!!.setTitle("재개봉 확정 리스트")
        /////////////////////

        // RV
        rvAdapter = ViewReopenRVadapter(testDataSet)

        binding.rvReopenList.layoutManager = LinearLayoutManager(this)
        binding.rvReopenList.adapter = rvAdapter
        binding.rvReopenList.addItemDecoration(VerticalItemDecorator(13))


        // 액티비티 시작 시  재개봉 확정 리스트 바로 받아오기
        viewModel.getReopenMovieList()


        viewModel.progressbarVisibility.observe(this, {
            if(it)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.INVISIBLE
        })

        viewModel.IsGetReopenMovieListSuccess.observe(this, {
            if(it){
                Log.e("AppTest", "ViewReopenMovieListActivity/ 재개봉 확정 영화 조회 성공, RV 업데이트")

                rvAdapter.updateList(viewModel.ReopenMovieList)
            }
            else{
                Log.e("AppTest", "AdminActivity/ 재개봉 확정 영화 조회 실패, RV 업데이트x")
                Toast.makeText(this, "재개봉 확정 영화 리스트 조회에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }

        })



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