package com.example.wannamovie.presentation.main.tab.mypage.reopenlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wannamovie.R
import com.example.wannamovie.data.remote.dto.response.admin.AdminMovieListResponseDto
import com.example.wannamovie.data.remote.dto.response.admin.ReopenMovieListResponseDto
import com.example.wannamovie.databinding.ItemAdminMovieListRvBinding
import com.example.wannamovie.databinding.ItemReopenMovieListRvBinding
import com.example.wannamovie.presentation.admin.AdminMovieListRVadapter

class ViewReopenRVadapter(
        private var dataSet : MutableList<ReopenMovieListResponseDto>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class MyViewHolder(val binding: ItemReopenMovieListRvBinding)
        :RecyclerView.ViewHolder(binding.root){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_reopen_movie_list_rv,
                parent,
                false
        )

        // 비율로 아이템뷰 넓이 설정하기
        /*  var params = view.layoutParams
          params.width = parent.measuredHeight / 4 * 3*/

        return MyViewHolder(ItemReopenMovieListRvBinding.bind(view))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyViewHolder){

            holder.binding.tvMovieTitle.text = dataSet[position].title

            var releaseDate = dataSet[position].release_date.substring(0 until 10) // 개봉일
            holder.binding.tvMovieReleaseDate.text = releaseDate

            var reopenDecisionDate = dataSet[position].re_open_date.substring(0 until 10) // index 9까지 자르기
            holder.binding.tvReopenDecisionDate.text = reopenDecisionDate

            Glide.with(holder.itemView.context)
                    .load(dataSet[position].poster_path)
                    .into(holder.binding.ivMoviePoster)

        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(newList : MutableList<ReopenMovieListResponseDto>){
        dataSet = newList
        notifyDataSetChanged()
    }

}