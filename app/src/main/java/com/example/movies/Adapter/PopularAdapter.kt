package com.example.movies.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.Model.PopularResultsItem

import com.example.movies.R
import kotlinx.android.synthetic.main.item_popular.view.*

class PopularAdapter(val data: List<PopularResultsItem>?) : RecyclerView.Adapter<PopularAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
        return MyHolder(v)
    }
    override fun getItemCount(): Int = data?.size ?: 0
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(data?.get(position))
    }
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: PopularResultsItem?) {
            itemView.pop_text.text = get?.backdropPath
        }
    }
}