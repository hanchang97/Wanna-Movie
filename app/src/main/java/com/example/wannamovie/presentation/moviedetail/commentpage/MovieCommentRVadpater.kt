package com.example.wannamovie.presentation.moviedetail.commentpage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wannamovie.R
import com.example.wannamovie.data.remote.dto.response.home.HomeMovieResponseDto
import com.example.wannamovie.data.remote.dto.response.search.CommentResponseDto
import com.example.wannamovie.databinding.ItemHomeMovieRvBinding
import com.example.wannamovie.databinding.ItemMovieCommentRvBinding
import com.example.wannamovie.presentation.main.tab.home.adapter.HomeAddressRVadapter

class MovieCommentRVadpater(
        private var dataSet : MutableList<CommentResponseDto>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class MyViewHolder(val binding: ItemMovieCommentRvBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie_comment_rv,
                parent,
                false
        )

        // 비율로 아이템뷰 넓이 설정하기
        /*var params = view.layoutParams
        params.width = parent.measuredHeight / 4 * 3*/

        return MyViewHolder(ItemMovieCommentRvBinding.bind(view))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyViewHolder){
            holder.binding.tvContent.text = dataSet[position].content
            holder.binding.tvName.text = dataSet[position].user_name

            var createDate = dataSet[position].creation_date.substring(0 until 10)
            holder.binding.tvDate.text = createDate

        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(newList : MutableList<CommentResponseDto>){
        dataSet = newList
        notifyDataSetChanged()
    }
}