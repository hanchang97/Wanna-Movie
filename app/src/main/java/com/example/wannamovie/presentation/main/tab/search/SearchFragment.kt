package com.example.wannamovie.presentation.main.tab.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.core.text.toSpannable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.wannamovie.R
import com.example.wannamovie.common.util.LinearGradientSpan
import com.example.wannamovie.databinding.FragmentMypageBinding
import com.example.wannamovie.databinding.FragmentSearchBinding
import com.example.wannamovie.presentation.main.MainActivityViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SearchFragment: Fragment() {

    private lateinit var binding : FragmentSearchBinding
    private val viewModel : MainActivityViewModel by sharedViewModel()

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

    }
}