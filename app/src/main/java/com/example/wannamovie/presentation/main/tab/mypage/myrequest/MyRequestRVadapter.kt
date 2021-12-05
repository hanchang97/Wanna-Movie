package com.example.wannamovie.presentation.main.tab.mypage.myrequest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wannamovie.R
import com.example.wannamovie.data.remote.dto.response.admin.ReopenMovieListResponseDto
import com.example.wannamovie.data.remote.dto.response.search.MyRequestListResponseDto
import com.example.wannamovie.databinding.ItemMyRequestListRvBinding
import com.example.wannamovie.databinding.ItemReopenMovieListRvBinding
import com.example.wannamovie.presentation.main.tab.mypage.reopenlist.ViewReopenRVadapter

class MyRequestRVadapter(
        private var dataSet : MutableList<MyRequestListResponseDto>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    inner class MyViewHolder(val binding: ItemMyRequestListRvBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_my_request_list_rv,
                parent,
                false
        )

        // 비율로 아이템뷰 넓이 설정하기
        /*  var params = view.layoutParams
          params.width = parent.measuredHeight / 4 * 3*/

        return MyViewHolder(ItemMyRequestListRvBinding.bind(view))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyViewHolder){

            holder.binding.tvMovieTitle.text = dataSet[position].title

            var releaseDate = dataSet[position].release_date.substring(0 until 10) // 개봉일
            holder.binding.tvMovieReleaseDate.text = releaseDate

            holder.binding.tvMovieRequestNum.text = dataSet[position].request_count.toString()

            Glide.with(holder.itemView.context)
                    .load(dataSet[position].poster_path)
                    .into(holder.binding.ivMoviePoster)

        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(newList : MutableList<MyRequestListResponseDto>){
        dataSet = newList
        notifyDataSetChanged()
    }
}