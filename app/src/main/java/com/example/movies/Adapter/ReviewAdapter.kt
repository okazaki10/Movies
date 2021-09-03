package com.example.movies.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.Config.NetworkConfig
import com.example.movies.Model.ReviewResultsItem
import com.example.movies.R
import com.example.movies.ViewModel.Detail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_popular.view.*
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter(val data: List<ReviewResultsItem>?) : RecyclerView.Adapter<ReviewAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return MyHolder(v)
    }
    override fun getItemCount(): Int = data?.size ?: 0
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(data?.get(position))

    }
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: ReviewResultsItem?) {
            itemView.author.setText(get?.author)
            itemView.reviewcontent.setText(get?.content)
            Picasso.get().load(NetworkConfig.URL_IMAGE+get?.authorDetails?.avatarPath).into(itemView.foto_profil);
        }
    }
}