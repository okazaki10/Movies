package com.example.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.config.NetworkConfig
import com.example.movies.model.DetailReviewListModel
import com.example.movies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter(val data: List<DetailReviewListModel>?) : RecyclerView.Adapter<ReviewAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return MyHolder(v)
    }
    override fun getItemCount(): Int = data?.size ?: 0
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(data?.get(position))

    }
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: DetailReviewListModel?) {
            itemView.author.setText(get?.author)
            itemView.reviewcontent.setText(get?.content)
            Picasso.get().load(NetworkConfig.URL_IMAGE+get?.authorDetails?.avatarPath).into(itemView.foto_profil);
        }
    }
}