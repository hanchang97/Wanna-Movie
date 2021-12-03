package com.example.wannamovie.presentation.main.tab.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wannamovie.R
import com.example.wannamovie.data.remote.dto.response.home.HomeMovieResponseDto
import com.example.wannamovie.data.remote.dto.response.search.CommentResponseDto
import com.example.wannamovie.databinding.ItemHomeMovieRv2Binding
import com.example.wannamovie.databinding.ItemHomeMovieRvBinding
import com.example.wannamovie.databinding.ItemSearchMovieMoreBinding
import com.example.wannamovie.domain.entity.MovieSearched
import java.lang.RuntimeException

class SearchMovieRVadapter(
        private var dataSet : MutableList<MovieSearched>
        , val selectMovie:(movieId: Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 뷰 타입 오버라이드! 1. 설명 텍스트뷰 2. 포트폴리오 리스트 아이템 3. 로딩 뷰
    override fun getItemViewType(position: Int): Int {
        return dataSet[position].itemview_type
    }

    // 영화 아이템 뷰
    inner class ViewType1ViewHolder(val binding: ItemHomeMovieRv2Binding)
        :RecyclerView.ViewHolder(binding.root){

    }

    // 더보기 요청 뷰
    inner class ViewType2ViewHolder(val binding: ItemSearchMovieMoreBinding)
        :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            1-> {
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_home_movie_rv,
                        parent,
                        false
                )

                // 비율로 높이 설정하기
                var params = view.layoutParams
                params.width = parent.measuredWidth * 1 / 3
                params.height = params.width * 4 / 3

                ViewType1ViewHolder(ItemHomeMovieRv2Binding.bind(view))
            }
            2-> {
                val view = LayoutInflater.from(parent.context).inflate(
                        R.layout.item_search_movie_more,
                        parent,
                        false
                )

                // 비율로 높이 설정하기
                var params = view.layoutParams
                params.width = parent.measuredWidth * 1 / 3
                params.height = params.width * 4 / 3

                ViewType2ViewHolder(ItemSearchMovieMoreBinding.bind(view))
            }
            else -> {
                throw RuntimeException("unknown item view type error")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewType1ViewHolder){
            holder.binding.tvMovieName.text = dataSet[position].movie_title

            Glide.with(holder.itemView.context)
                    .load(dataSet[position].poster_path)
                    .into(holder.binding.ivMoviePoster)

            // 아이템 뷰 클릭 시 해당 영화 상세 정보 페이지 이동
            holder.binding.root.setOnClickListener {
                selectMovie.invoke(dataSet[position].movie_id)
            }

        }
        else if(holder is ViewType2ViewHolder){

          /*  holder.binding.root.setOnClickListener {
                searchMore.invoke()
            }*/
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateList(newList : MutableList<MovieSearched>){
        dataSet = newList
        notifyDataSetChanged()
    }
}