package com.example.wannamovie.presentation.main.tab.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wannamovie.R
import com.example.wannamovie.common.util.AllDirectionItemDecorator
import com.example.wannamovie.common.util.LinearGradientSpan
import com.example.wannamovie.common.util.VerticalItemDecorator
import com.example.wannamovie.data.remote.dto.response.home.HomeMovieResponseDto
import com.example.wannamovie.databinding.FragmentMypageBinding
import com.example.wannamovie.databinding.FragmentSearchBinding
import com.example.wannamovie.domain.entity.MovieSearched
import com.example.wannamovie.presentation.main.tab.home.adapter.HomeAgeRVadapter
import com.example.wannamovie.presentation.moviedetail.MovieDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment: Fragment() {

    private lateinit var binding : FragmentSearchBinding
    private val viewModel : SearchViewModel by viewModel()

    private var KEYWORD_TYPE = ""

    lateinit var rvAdapter: SearchMovieRVadapter
    private var testDataSet = mutableListOf<MovieSearched>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("AppTest", "search fragment onCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("AppTest", "search fragment onViewCreated")

        // textview gradient
        val start = ContextCompat.getColor(requireContext(), R.color.splash_title_start)
        val end = ContextCompat.getColor(requireContext(), R.color.splash_title_end)

        val titleText = "WANNA MOVIE"
        val spannable = titleText.toSpannable()
        spannable[0..titleText.length] = LinearGradientSpan(titleText, titleText, start, end)
        binding.tvTitle.text = spannable
        /////////////////////////////////////

        // 검색 keyword type spinner
        //var professionList = listOf("초중고생, 대학생, 직장인, 기타")   // 직업 선택 spinner
        var keywordTypeData = resources.getStringArray(R.array.keywordType_dataList)
        var adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, keywordTypeData)
        binding.spinnerSearch.adapter = adapter

        KEYWORD_TYPE = keywordTypeData[0] // 최초 설정

        binding.spinnerSearch.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Log.e("AppTest", "spinner item position : ${position}")


                when(position){
                    0 -> {
                        KEYWORD_TYPE = "title"
                    }
                    1-> {
                        KEYWORD_TYPE = "director"
                    }
                    2->{
                        KEYWORD_TYPE = "genre"
                    }
                }
                Log.e("AppTest", "선택한 검색 키워드 : ${KEYWORD_TYPE}")  // 뷰 최초 실행시 로그 찍힘 -> 최초에도 자동 선택인식 되는듯
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
        ///////////////////////////////////

        // RV 설정
        rvAdapter = SearchMovieRVadapter(testDataSet,
                selectMovie = {
                    movieId ->
                    Log.e("AppTest", "SearchFragment/ goto movie detail, 선택한 영화 id : ${movieId}")
                    val intent = Intent(context, MovieDetailActivity::class.java)
                    intent.putExtra("movieId", movieId)
                    startActivity(intent)
                })
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvSearchMovie.layoutManager = gridLayoutManager
        binding.rvSearchMovie.adapter = rvAdapter
        binding.rvSearchMovie.addItemDecoration(VerticalItemDecorator(32))


          binding.ivSearch.setOnClickListener {

              var keyword = binding.etSearch.text.toString()
              viewModel.searchMovie(keyword, KEYWORD_TYPE)
          }

        viewModel.IsSearchMovieSuccess.observe(viewLifecycleOwner, {
            if(it){
                Log.e("AppTest", "영화 검색 성공 -> RV 업데이트")

                rvAdapter.updateList(viewModel.SearchMovieList)
            }
            else{
                Log.e("AppTest", "영화 검색 실패")
            }
        })

        viewModel.progressbarVisibility.observe(viewLifecycleOwner, {
            if(it)
                binding.loadingProgressBar.visibility = View.VISIBLE
            else
                binding.loadingProgressBar.visibility = View.INVISIBLE
        })



    }
}