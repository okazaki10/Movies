package com.example.movies.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.configs.NetworkConfig
import com.example.movies.models.NowplayingListModel

import com.example.movies.R
import com.example.movies.ui.DetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_now.view.*

class NowplayingAdapter(val data: List<NowplayingListModel>?) : RecyclerView.Adapter<NowplayingAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_now, parent, false)
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
        fun bind(get: NowplayingListModel?) {
            itemView.now_title.setText(get?.title)
            itemView.now_originaltitle.setText(get?.originalTitle)
            Picasso.get().load(NetworkConfig.URL_IMAGE+get?.backdropPath).into(itemView.now_photo);
        }
    }
}