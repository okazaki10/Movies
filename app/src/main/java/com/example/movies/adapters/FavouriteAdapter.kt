package com.example.movies.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.configs.NetworkConfig
import com.example.movies.models.FavouriteDBModel

import com.example.movies.R
import com.example.movies.ui.DetailActivity


import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favourite.view.*

class FavouriteAdapter(val data: List<FavouriteDBModel>?) : RecyclerView.Adapter<FavouriteAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_favourite, parent, false)
        return MyHolder(v)
    }
    override fun getItemCount(): Int = data?.size ?: 0
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(data?.get(position))
        holder.itemView.setOnClickListener(View.OnClickListener { view ->
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            var data2 = data?.get(position)
            intent.putExtra("id", data2?.movie_id)
            intent.putExtra("title", data2?.title)
            intent.putExtra("originalTitle", data2?.original_title)
            intent.putExtra("overview", data2?.overview)
            intent.putExtra("image",NetworkConfig.URL_IMAGE+data2?.image)

            //Toast.makeText(holder.itemView.context,data2?.movie_id,Toast.LENGTH_SHORT).show()
            holder.itemView.context.startActivity(intent)
        })
    }
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: FavouriteDBModel?) {
            itemView.fav_title.setText(get?.title)
            itemView.fav_originaltitle.setText(get?.original_title)

            var overview = get?.overview

            if (overview?.length!! > 200){
               overview = overview.substring(0,200)+"..."
            }

            itemView.fav_overview.setText(overview)
            Picasso.get().load(NetworkConfig.URL_IMAGE+get?.image).into(itemView.fav_photo);
        }
    }
}