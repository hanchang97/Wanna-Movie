package com.example.wannamovie.presentation.main.tab.mypage.myrequest

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
import com.example.wannamovie.data.remote.dto.response.admin.ReopenMovieListResponseDto
import com.example.wannamovie.data.remote.dto.response.search.MyRequestListResponseDto
import com.example.wannamovie.databinding.ActivityMyRequestListBinding
import com.example.wannamovie.databinding.ActivityMyinfoBinding
import com.example.wannamovie.databinding.ActivityViewReopenListBinding
import com.example.wannamovie.presentation.main.tab.mypage.reopenlist.ViewReopenRVadapter
import com.example.wannamovie.presentation.main.tab.mypage.reopenlist.ViewReopenViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MyRequestListActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMyRequestListBinding
    private val viewModel : MyRequestListViewModel by viewModel()

    lateinit var rvAdapter: MyRequestRVadapter
    private var testDataSet = mutableListOf<MyRequestListResponseDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest", "MyRequestListActivity/ onCreate")

        binding = DataBindingUtil.setContentView<ActivityMyRequestListBinding>(this, R.layout.activity_my_request_list)

        // toolbar 설정!!
        setSupportActionBar(binding.toolbar)  // 액션바로 xml에 만들어준 toolbar를 사용한다
        supportActionBar!!.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼 활성화
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_icon_back)
        supportActionBar!!.setDisplayShowTitleEnabled(true) // 기본제목 없애기 = false
        supportActionBar!!.setTitle("나의 재개봉 요청 리스트")
        /////////////////////


        // RV
        rvAdapter = MyRequestRVadapter(testDataSet)

        binding.rvMyRequestList.layoutManager = LinearLayoutManager(this)
        binding.rvMyRequestList.adapter = rvAdapter
        binding.rvMyRequestList.addItemDecoration(VerticalItemDecorator(13))


        // 나의 요청 리스트 가져오기
        viewModel.getMyRequestMovieList()
        /////////////////////////////////////////////////////////////////////////////

        viewModel.IsGetMyRequestMovieListSuccess.observe(this, {
            if(it){
                Log.e("AppTest", "MyRequestListActivity/ 나의 요청 영화 리스트 조회 성공, RV 업데이트")

                rvAdapter.updateList(viewModel.MyRequestMovieList)
            }
            else{
                Log.e("AppTest", "MyRequestListActivity/ 나의 요청 영화 리스트 조회 실패, RV 업데이트x")
                Toast.makeText(this, "나의 재개봉 요청 리스트 조회에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.progressbarVisibility.observe(this, {
            if(it)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.INVISIBLE
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