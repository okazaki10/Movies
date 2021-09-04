package com.example.movies.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.Config.NetworkConfig

import com.example.movies.R
import com.example.movies.model.FavouriteModel

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favourite.view.*
import kotlinx.android.synthetic.main.item_review.view.*

class FavouriteAdapter(val data: List<FavouriteModel>?) : RecyclerView.Adapter<FavouriteAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_favourite, parent, false)
        return MyHolder(v)
    }
    override fun getItemCount(): Int = data?.size ?: 0
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(data?.get(position))

    }
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: FavouriteModel?) {
            itemView.fav_title.setText(get?.TITLE)
            itemView.fav_originaltitle.setText(get?.ORIGINAL_TITLE)
            itemView.fav_overview.setText(get?.OVERVIEW)
            Picasso.get().load(NetworkConfig.URL_IMAGE+get?.IMAGE).into(itemView.fav_photo);
        }
    }
}