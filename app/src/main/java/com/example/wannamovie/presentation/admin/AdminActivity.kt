package com.example.wannamovie.presentation.admin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wannamovie.R
import com.example.wannamovie.common.util.VerticalItemDecorator
import com.example.wannamovie.data.remote.dto.response.admin.AdminMovieListResponseDto
import com.example.wannamovie.databinding.ActivityAdminBinding
import org.koin.android.viewmodel.ext.android.viewModel

class AdminActivity: AppCompatActivity() {

    private lateinit var binding : ActivityAdminBinding
    private val viewModel : AdminViewModel by viewModel()

    private var KEYWORD_TYPE = ""
    private var KEYWORD_VALUE = ""

    private var AGE = ""
    private var SEX = ""
    private var ADDRESS = ""

    private var TOP_COUNT = 0

    lateinit var rvAdapter: AdminMovieListRVadapter
    private var testDataSet = mutableListOf<AdminMovieListResponseDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("AppTest","admin activity onCreate")

        binding = DataBindingUtil.setContentView<ActivityAdminBinding>(this, R.layout.activity_admin)


        // 키워드 type spinner
        var keywordTypeData = resources.getStringArray(R.array.keywordType_admin)
        var typeAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, keywordTypeData)
        binding.spinnerKeywordType.adapter = typeAdapter

        binding.spinnerKeywordType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Log.e("AppTest", "spinner item position : ${position}")

                when(position){
                    0 -> {
                        KEYWORD_TYPE = "age"
                        binding.spinnerKeywordAge.visibility = View.VISIBLE
                        binding.spinnerKeywordSex.visibility = View.INVISIBLE
                        binding.spinnerKeywordAddress.visibility = View.INVISIBLE
                    }
                    1-> {
                        KEYWORD_TYPE = "sex"
                        binding.spinnerKeywordAge.visibility = View.INVISIBLE
                        binding.spinnerKeywordSex.visibility = View.VISIBLE
                        binding.spinnerKeywordAddress.visibility = View.INVISIBLE
                    }
                    2->{
                        KEYWORD_TYPE = "address"
                        binding.spinnerKeywordAge.visibility = View.INVISIBLE
                        binding.spinnerKeywordSex.visibility = View.INVISIBLE
                        binding.spinnerKeywordAddress.visibility = View.VISIBLE
                    }
                }
                Log.e("AppTest", "선택한 검색 키워드 : ${KEYWORD_TYPE}")  // 뷰 최초 실행시 로그 찍힘 -> 최초에도 자동 선택인식 되는듯
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        //////////////////////////////////////////

        // 키워드 - age
        var keywordTypeAge = resources.getStringArray(R.array.keywordType_admin_age)
        var ageAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, keywordTypeAge)
        binding.spinnerKeywordAge.adapter = ageAdapter

        binding.spinnerKeywordAge.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Log.e("AppTest", "spinner item position : ${position}")

                AGE = ((position + 1) * 10).toString()
                Log.e("AppTest", "현재 keyword : ${KEYWORD_TYPE}\n" +
                        "선택한 나이 : ${AGE}")  // 뷰 최초 실행시 로그 찍힘 -> 최초에도 자동 선택인식 되는듯
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        /////////////////////////////


        // 키워드 - sex
        var keywordTypeSex = resources.getStringArray(R.array.keywordType_admin_sex)
        var sexAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, keywordTypeSex)
        binding.spinnerKeywordSex.adapter = sexAdapter

        binding.spinnerKeywordSex.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Log.e("AppTest", "spinner item position : ${position}")

               when(position){
                   0-> {
                       SEX = "M"
                   }
                   1->{
                       SEX = "F"
                   }
               }

                Log.e("AppTest", "현재 keyword : ${KEYWORD_TYPE}\n" +
                        "선택한 성별 : ${SEX}")  // 뷰 최초 실행시 로그 찍힘 -> 최초에도 자동 선택인식 되는듯
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }



        // 키워드 - address

        var keywordTypeAddress = resources.getStringArray(R.array.keywordType_admin_address)
        var addressAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, keywordTypeAddress)
        binding.spinnerKeywordAddress.adapter = addressAdapter

        binding.spinnerKeywordAddress.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Log.e("AppTest", "spinner item position : ${position}")

                when(position){
                    0-> {
                        ADDRESS = "강남구"
                    }
                    1->{
                        ADDRESS = "관악구"
                    }
                    2->{
                        ADDRESS = "서대문구"
                    }
                    3->{
                        ADDRESS = "동작구"
                    }
                }

                Log.e("AppTest", "현재 keyword : ${KEYWORD_TYPE}\n" +
                        "선택한 지역 : ${ADDRESS}")  // 뷰 최초 실행시 로그 찍힘 -> 최초에도 자동 선택인식 되는듯
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        ///////////////////////////////

        // top count 선택

        var topCountData = resources.getStringArray(R.array.datacount_dataList)
        var topCountAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topCountData)
        binding.spinnerDataCount.adapter = topCountAdapter

        binding.spinnerDataCount.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Log.e("AppTest", "spinner item position : ${position}")

                TOP_COUNT = position + 1

                Log.e("AppTest", "선택한 top count : ${TOP_COUNT}")  // 뷰 최초 실행시 로그 찍힘 -> 최초에도 자동 선택인식 되는듯
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        /////////////////////////////////////////////////////////

        // RV
        rvAdapter = AdminMovieListRVadapter(testDataSet,
            reopenMovie = {
                Log.e("AppTest", "선택한 영화 id : ${it}")
                reopen(it)
        })

        binding.rvAdminMovieList.layoutManager = LinearLayoutManager(this)
        binding.rvAdminMovieList.adapter = rvAdapter
        binding.rvAdminMovieList.addItemDecoration(VerticalItemDecorator(13))



        // 조회 버튼 누를 시
        binding.btnGetStatistics.setOnClickListener {
            Log.e("AppTest", "통계 조회 버튼 클릭")

            when(KEYWORD_TYPE){
                "age"->{
                    KEYWORD_VALUE = AGE
                }
                "sex"->{
                    KEYWORD_VALUE = SEX
                }
                "address"->{
                    KEYWORD_VALUE = ADDRESS
                }
            }

            Log.e("AppTest", " 키워드 : ${KEYWORD_TYPE}, 키워드값 : ${KEYWORD_VALUE}, topCount : ${TOP_COUNT}")

            viewModel.getMovieList(TOP_COUNT, KEYWORD_TYPE, KEYWORD_VALUE)
        }

        viewModel.progressbarVisibility.observe(this, {
            if(it)
                binding.progressBar.visibility = View.VISIBLE
            else
                binding.progressBar.visibility = View.INVISIBLE
        })

        viewModel.IsGetMovieListSuccess.observe(this, {
            if(it){
                Log.e("AppTest", "AdminActivity/ 영화 조회 성공, RV 업데이트")

                rvAdapter.updateList(viewModel.AdminMovieList)
            }
            else{
                Log.e("AppTest", "AdminActivity/ 영화 조회 실패, RV 업데이트x")
                Toast.makeText(this, "영화 리스트 조회에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }

        })

        ///////////////////////

        // 재개봉 확정 요청 시
        viewModel.IsReopenMovieSuccess.observe(this, {
            if(it)
                Toast.makeText(this, "재개봉 확정을 성공했습니다.", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "재개봉 확정을 실패했습니다.", Toast.LENGTH_SHORT).show()
        })

        ///////////////////////////



    }

    // 재개봉 확정하기
    fun reopen(movie_id: Int){
        viewModel.reopenMovie(movie_id)
    }
}