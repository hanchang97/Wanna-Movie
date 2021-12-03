package com.example.wannamovie.presentation.main.tab.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wannamovie.R
import com.example.wannamovie.common.Constants2
import com.example.wannamovie.common.util.HorizontalItemDecorator
import com.example.wannamovie.data.remote.dto.response.home.HomeMovieResponseDto
import com.example.wannamovie.databinding.FragmentHomeBinding
import com.example.wannamovie.presentation.main.MainActivityViewModel
import com.example.wannamovie.presentation.main.tab.home.adapter.HomeAddressRVadapter
import com.example.wannamovie.presentation.main.tab.home.adapter.HomeAgeRVadapter
import com.example.wannamovie.presentation.main.tab.home.adapter.HomeSexRVadapter
import com.example.wannamovie.presentation.moviedetail.MovieDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val viewModel : HomeViewModel by viewModel()

    lateinit var rvAdapter_age: HomeAgeRVadapter
    private var testDataSet_age = mutableListOf<HomeMovieResponseDto>()

    lateinit var rvAdapter_sex: HomeSexRVadapter
    private var testDataSet_sex = mutableListOf<HomeMovieResponseDto>()

    lateinit var rvAdapter_address: HomeAddressRVadapter
    private var testDataSet_address = mutableListOf<HomeMovieResponseDto>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("AppTest", "home fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "home fragment onViewCreated")

        // 홈 들어오면 유저 정보,  나이 top5, 성별 top5, 지역 top5 호출
        viewModel.getUserInfo()
        viewModel.getHomeMovieListByAge()
        viewModel.getHomeMovieListBySex()
        viewModel.getHomeMovieListByAddress()

        //////////////////////////////////////////////////////////////////////////
        // 유저정보 관련
        viewModel.IsGetUserInfoSuccess.observe(viewLifecycleOwner, {
            if(it){ // 성공 시
                if(Constants2.USER_SEX.equals("M"))
                    binding.tvTop5Sex.text = "남성 Top5 재개봉 요청 영화"
                else
                    binding.tvTop5Sex.text = "여성 Top5 재개봉 요청 영화"

                binding.tvTop5Address.text = Constants2.USER_ADDRESS + " Top5 재개봉 요청 영화"

                setAgeDes(Constants2.USER_AGE)

            }
            else{
                Log.e("AppTest", "HomeFragment/ 유저 정보 조회 실패")
            }
        })

        viewModel.progressbarVisibility_userInfo.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBarUserInfo.visibility = View.VISIBLE
            else
                binding.loadingProgressBarUserInfo.visibility = View.INVISIBLE
        })

        ///////////////////////////////////////////////////////////////////////

        // 나이 top5 관련
        rvAdapter_age = HomeAgeRVadapter(testDataSet_age,
        selectMovie = {
            movieId ->
                Log.e("AppTest", "HomeFragment/ age top5 -> movie detail")
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("movieId", movieId)
            startActivity(intent)
        })  // 영화 선택 시 상세 정보 페이지로 이동

        binding.rvTop5Age.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL  // 가로 방향 recyclerview
        }
        binding.rvTop5Age.adapter = rvAdapter_age
        binding.rvTop5Age.addItemDecoration(HorizontalItemDecorator(12))

                // AGE RV 갱신
        viewModel.IsGetMovieListAge.observe(viewLifecycleOwner, {
            if(it){
                Log.e("AppTest", "HomeFragment/ 나이 top5 리스트 조회 성공 -> RV 업데이트")
                rvAdapter_age.updateList(viewModel.HomeMovieList_Age)
            }
            else{
                Log.e("AppTest", "HomeFragment/ 나이 top5 리스트 조회 실패")
            }
        })

                // 로딩 프로그레스바
        viewModel.progressbarVisibility_age.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBarAge.visibility = View.VISIBLE
            else
                binding.loadingProgressBarAge.visibility = View.INVISIBLE
        })
        ///////////////////////////////////////////////////////////////////////////////////

        // 성별 top5 관련
        rvAdapter_sex = HomeSexRVadapter(testDataSet_sex,
                selectMovie = {
                    movieId ->
                    Log.e("AppTest", "HomeFragment/ sex top5 -> movie detail")
                    val intent = Intent(context, MovieDetailActivity::class.java)
                    intent.putExtra("movieId", movieId)
                    startActivity(intent)
                })
        binding.rvTop5Sex.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL  // 가로 방향 recyclerview
        }
        binding.rvTop5Sex.adapter = rvAdapter_sex
        binding.rvTop5Sex.addItemDecoration(HorizontalItemDecorator(12))

        // AGE RV 갱신
        viewModel.IsGetMovieListSex.observe(viewLifecycleOwner, {
            if(it){
                Log.e("AppTest", "HomeFragment/ 성별 top5 리스트 조회 성공 -> RV 업데이트")
                rvAdapter_sex.updateList(viewModel.HomeMovieList_Sex)
            }
            else{
                Log.e("AppTest", "HomeFragment/ 성별 top5 리스트 조회 실패")
            }
        })

        // 로딩 프로그레스바
        viewModel.progressbarVisibility_sex.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBarSex.visibility = View.VISIBLE
            else
                binding.loadingProgressBarSex.visibility = View.INVISIBLE
        })
        ///////////////////////////////////////////////////////////////////////////////////


        // 지역 top5 관련
        rvAdapter_address = HomeAddressRVadapter(testDataSet_address,
                selectMovie = {
                    movieId ->
                    Log.e("AppTest", "HomeFragment/ address top5 -> movie detail")
                    val intent = Intent(context, MovieDetailActivity::class.java)
                    intent.putExtra("movieId", movieId)
                    startActivity(intent)
                })
        binding.rvTop5Address.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL  // 가로 방향 recyclerview
        }
        binding.rvTop5Address.adapter = rvAdapter_address
        binding.rvTop5Address.addItemDecoration(HorizontalItemDecorator(12))

        // AGE RV 갱신
        viewModel.IsGetMovieListAddress.observe(viewLifecycleOwner, {
            if(it){
                Log.e("AppTest", "HomeFragment/ 지역 top5 리스트 조회 성공 -> RV 업데이트")
                rvAdapter_address.updateList(viewModel.HomeMovieList_Address)
            }
            else{
                Log.e("AppTest", "HomeFragment/ 지역 top5 리스트 조회 실패")
            }
        })

        // 로딩 프로그레스바
        viewModel.progressbarVisibility_address.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBarAddress.visibility = View.VISIBLE
            else
                binding.loadingProgressBarAddress.visibility = View.INVISIBLE
        })
        ///////////////////////////////////////////////////////////////////////////////////


    }



    fun setAgeDes(age: Int){
        when(age){
            in 0..9 -> {
                binding.tvTop5Age.text = "어린이 Top5 재개봉 요청 영화"
            }
            in 10..19 -> {
                binding.tvTop5Age.text = "10대 Top5 재개봉 요청 영화"
            }
            in 20..29 -> {
                binding.tvTop5Age.text = "20대 Top5 재개봉 요청 영화"
            }
            in 30..39 -> {
                binding.tvTop5Age.text = "30대 Top5 재개봉 요청 영화"
            }
            in 40..49 -> {
                binding.tvTop5Age.text = "40대 Top5 재개봉 요청 영화"
            }
            in 50..59 -> {
                binding.tvTop5Age.text = "50대 Top5 재개봉 요청 영화"
            }
            in 60..69 -> {
                binding.tvTop5Age.text = "60대 Top5 재개봉 요청 영화"
            }
            else -> {
                binding.tvTop5Age.text = "노년층 Top5 재개봉 요청 영화"
            }
        }
    }
}