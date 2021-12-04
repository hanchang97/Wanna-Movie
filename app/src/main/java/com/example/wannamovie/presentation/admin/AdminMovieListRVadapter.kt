package com.example.wannamovie.presentation.admin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wannamovie.R
import com.example.wannamovie.data.remote.dto.response.admin.AdminMovieListResponseDto
import com.example.wannamovie.data.remote.dto.response.home.HomeMovieResponseDto
import com.example.wannamovie.data.remote.dto.response.search.CommentResponseDto
import com.example.wannamovie.databinding.ItemAdminMovieListRvBinding
import com.example.wannamovie.databinding.ItemHomeMovieRvBinding
import com.example.wannamovie.presentation.main.tab.home.adapter.HomeAddressRVadapter

class AdminMovieListRVadapter(
        private var dataSet : MutableList<AdminMovieListResponseDto>,
        val reopenMovie:(movieId : Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class MyViewHolder(val binding: ItemAdminMovieListRvBinding)
        :RecyclerView.ViewHolder(binding.root){

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_admin_movie_list_rv,
                parent,
                false
        )

        // 비율로 아이템뷰 넓이 설정하기
      /*  var params = view.layoutParams
        params.width = parent.measuredHeight / 4 * 3*/

        return MyViewHolder(ItemAdminMovieListRvBinding.bind(view))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyViewHolder){

            holder.binding.tvMovieTitle.text = dataSet[position].title
            holder.binding.tvMovieRequestNum.text = dataSet[position].request_count.toString()

            var releaseDate = dataSet[position].release_date.substring(0 until 10) // 개봉일
            holder.binding.tvMovieReleaseDate.text = releaseDate

            Glide.with(holder.itemView.context)
                    .load(dataSet[position].poster_path)
                    .into(holder.binding.ivMoviePoster)

            // 해당 무비id 로 재개봉 확정하기
            holder.binding.btnReopen.setOnClickListener {
                reopenMovie.invoke(dataSet[position].movie_id)
            }

        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


    fun updateList(newList : MutableList<AdminMovieListResponseDto>){
        dataSet = newList
        notifyDataSetChanged()
    }


}