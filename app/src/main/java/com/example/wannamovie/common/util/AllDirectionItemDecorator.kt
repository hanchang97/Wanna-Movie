package com.example.wannamovie.common.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class AllDirectionItemDecorator(private val divHeight : Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent : RecyclerView, state : RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = divHeight
        outRect.right = divHeight
        outRect.top = divHeight
        outRect.bottom = 15
    }
}