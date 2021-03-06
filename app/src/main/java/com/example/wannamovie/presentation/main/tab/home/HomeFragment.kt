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

        // ??? ???????????? ?????? ??????,  ?????? top5, ?????? top5, ?????? top5 ??????
        viewModel.getUserInfo()
        viewModel.getHomeMovieListByAge()
        viewModel.getHomeMovieListBySex()
        viewModel.getHomeMovieListByAddress()

        //////////////////////////////////////////////////////////////////////////
        // ???????????? ??????
        viewModel.IsGetUserInfoSuccess.observe(viewLifecycleOwner, {
            if(it){ // ?????? ???
                if(Constants2.USER_SEX.equals("M"))
                    binding.tvTop5Sex.text = "?????? Top5 ????????? ?????? ??????"
                else
                    binding.tvTop5Sex.text = "?????? Top5 ????????? ?????? ??????"

                binding.tvTop5Address.text = Constants2.USER_ADDRESS + " Top5 ????????? ?????? ??????"

                setAgeDes(Constants2.USER_AGE)

            }
            else{
                Log.e("AppTest", "HomeFragment/ ?????? ?????? ?????? ??????")
            }
        })

        viewModel.progressbarVisibility_userInfo.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBarUserInfo.visibility = View.VISIBLE
            else
                binding.loadingProgressBarUserInfo.visibility = View.INVISIBLE
        })

        ///////////////////////////////////////////////////////////////////////

        // ?????? top5 ??????
        rvAdapter_age = HomeAgeRVadapter(testDataSet_age,
        selectMovie = {
            movieId ->
                Log.e("AppTest", "HomeFragment/ age top5 -> movie detail")
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("movieId", movieId)
            startActivity(intent)
        })  // ?????? ?????? ??? ?????? ?????? ???????????? ??????

        binding.rvTop5Age.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL  // ?????? ?????? recyclerview
        }
        binding.rvTop5Age.adapter = rvAdapter_age
        binding.rvTop5Age.addItemDecoration(HorizontalItemDecorator(12))

                // AGE RV ??????
        viewModel.IsGetMovieListAge.observe(viewLifecycleOwner, {
            if(it){
                Log.e("AppTest", "HomeFragment/ ?????? top5 ????????? ?????? ?????? -> RV ????????????")
                rvAdapter_age.updateList(viewModel.HomeMovieList_Age)
            }
            else{
                Log.e("AppTest", "HomeFragment/ ?????? top5 ????????? ?????? ??????")
            }
        })

                // ?????? ??????????????????
        viewModel.progressbarVisibility_age.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBarAge.visibility = View.VISIBLE
            else
                binding.loadingProgressBarAge.visibility = View.INVISIBLE
        })
        ///////////////////////////////////////////////////////////////////////////////////

        // ?????? top5 ??????
        rvAdapter_sex = HomeSexRVadapter(testDataSet_sex,
                selectMovie = {
                    movieId ->
                    Log.e("AppTest", "HomeFragment/ sex top5 -> movie detail")
                    val intent = Intent(context, MovieDetailActivity::class.java)
                    intent.putExtra("movieId", movieId)
                    startActivity(intent)
                })
        binding.rvTop5Sex.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL  // ?????? ?????? recyclerview
        }
        binding.rvTop5Sex.adapter = rvAdapter_sex
        binding.rvTop5Sex.addItemDecoration(HorizontalItemDecorator(12))

        // AGE RV ??????
        viewModel.IsGetMovieListSex.observe(viewLifecycleOwner, {
            if(it){
                Log.e("AppTest", "HomeFragment/ ?????? top5 ????????? ?????? ?????? -> RV ????????????")
                rvAdapter_sex.updateList(viewModel.HomeMovieList_Sex)
            }
            else{
                Log.e("AppTest", "HomeFragment/ ?????? top5 ????????? ?????? ??????")
            }
        })

        // ?????? ??????????????????
        viewModel.progressbarVisibility_sex.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBarSex.visibility = View.VISIBLE
            else
                binding.loadingProgressBarSex.visibility = View.INVISIBLE
        })
        ///////////////////////////////////////////////////////////////////////////////////


        // ?????? top5 ??????
        rvAdapter_address = HomeAddressRVadapter(testDataSet_address,
                selectMovie = {
                    movieId ->
                    Log.e("AppTest", "HomeFragment/ address top5 -> movie detail")
                    val intent = Intent(context, MovieDetailActivity::class.java)
                    intent.putExtra("movieId", movieId)
                    startActivity(intent)
                })
        binding.rvTop5Address.layoutManager = LinearLayoutManager(context).also {
            it.orientation = LinearLayoutManager.HORIZONTAL  // ?????? ?????? recyclerview
        }
        binding.rvTop5Address.adapter = rvAdapter_address
        binding.rvTop5Address.addItemDecoration(HorizontalItemDecorator(12))

        // AGE RV ??????
        viewModel.IsGetMovieListAddress.observe(viewLifecycleOwner, {
            if(it){
                Log.e("AppTest", "HomeFragment/ ?????? top5 ????????? ?????? ?????? -> RV ????????????")
                rvAdapter_address.updateList(viewModel.HomeMovieList_Address)
            }
            else{
                Log.e("AppTest", "HomeFragment/ ?????? top5 ????????? ?????? ??????")
            }
        })

        // ?????? ??????????????????
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
                binding.tvTop5Age.text = "????????? Top5 ????????? ?????? ??????"
            }
            in 10..19 -> {
                binding.tvTop5Age.text = "10??? Top5 ????????? ?????? ??????"
            }
            in 20..29 -> {
                binding.tvTop5Age.text = "20??? Top5 ????????? ?????? ??????"
            }
            in 30..39 -> {
                binding.tvTop5Age.text = "30??? Top5 ????????? ?????? ??????"
            }
            in 40..49 -> {
                binding.tvTop5Age.text = "40??? Top5 ????????? ?????? ??????"
            }
            in 50..59 -> {
                binding.tvTop5Age.text = "50??? Top5 ????????? ?????? ??????"
            }
            in 60..69 -> {
                binding.tvTop5Age.text = "60??? Top5 ????????? ?????? ??????"
            }
            else -> {
                binding.tvTop5Age.text = "????????? Top5 ????????? ?????? ??????"
            }
        }
    }
}