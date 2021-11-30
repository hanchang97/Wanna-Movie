package com.example.wannamovie.presentation.main.tab.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.wannamovie.R
import com.example.wannamovie.databinding.FragmentHomeBinding
import com.example.wannamovie.presentation.main.MainActivityViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class HomeFragment: Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val viewModel : MainActivityViewModel by sharedViewModel()

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
    }
}