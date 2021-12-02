package com.example.wannamovie.presentation.main.tab.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wannamovie.R
import com.example.wannamovie.data.remote.dto.response.home.HomeMovieResponseDto
import com.example.wannamovie.databinding.ItemHomeMovieRvBinding

class HomeAgeRVadapter(
        private var dataSet : MutableList<HomeMovieResponseDto>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class MyViewHolder(val binding: ItemHomeMovieRvBinding)
        :RecyclerView.ViewHolder(binding.root){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_home_movie_rv,
                parent,
                false
        )

        // 비율로 아이템뷰 넓이 설정하기
        var params = view.layoutParams
        params.width = parent.measuredHeight / 4 * 3

        return MyViewHolder(ItemHomeMovieRvBinding.bind(view))

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyViewHolder){

            holder.binding.tvMovieName.text = dataSet[position].title

            Glide.with(holder.itemView.context)
                    .load(dataSet[position].poster_path)
                    .into(holder.binding.ivMoviePoster)

        }

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(newList : MutableList<HomeMovieResponseDto>){
        dataSet = newList
        notifyDataSetChanged()
    }
}