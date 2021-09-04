package com.example.movies.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.config.NetworkConfig
import com.example.movies.model.TopListModel

import com.example.movies.R
import com.example.movies.viewmodel.DetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_popular.view.*
import kotlinx.android.synthetic.main.item_top.view.*

class TopAdapter(val data: List<TopListModel>?) : RecyclerView.Adapter<TopAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_top, parent, false)
        return MyHolder(v)
    }
    override fun getItemCount(): Int = data?.size ?: 0
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(data?.get(position))
        holder.itemView.setOnClickListener(View.OnClickListener { view ->
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            var data2 = data?.get(position)
            intent.putExtra("id", data2?.id.toString())
            intent.putExtra("title", data2?.title)
            intent.putExtra("originalTitle",  "Original Title : "+data2?.originalTitle)
            intent.putExtra("overview", data2?.overview)
            intent.putExtra("image",NetworkConfig.URL_IMAGE+data2?.backdropPath)
            holder.itemView.context.startActivity(intent)
            })
    }
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: TopListModel?) {
            itemView.top_title.setText(get?.title)
            itemView.top_originaltitle.setText(get?.originalTitle)
            Picasso.get().load(NetworkConfig.URL_IMAGE+get?.backdropPath).into(itemView.top_photo);
        }
    }
}