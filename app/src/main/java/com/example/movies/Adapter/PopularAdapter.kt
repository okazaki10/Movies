package com.example.movies.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.Config.NetworkConfig
import com.example.movies.Model.PopularResultsItem

import com.example.movies.R
import com.example.movies.ViewModel.Detail
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_popular.view.*

class PopularAdapter(val data: List<PopularResultsItem>?) : RecyclerView.Adapter<PopularAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
        return MyHolder(v)
    }
    override fun getItemCount(): Int = data?.size ?: 0
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(data?.get(position))
        holder.itemView.setOnClickListener(View.OnClickListener { view ->
            val intent = Intent(holder.itemView.context, Detail::class.java)
            var data2 = data?.get(position)
            intent.putExtra("title", data2?.title)
            intent.putExtra("originalTitle",  "Original Title : "+data2?.originalTitle)
            intent.putExtra("overview", data2?.overview)
            intent.putExtra("image",NetworkConfig.URL_IMAGE+data2?.backdropPath)
            holder.itemView.context.startActivity(intent)
            Toast.makeText(holder.itemView.context, data2?.title, Toast.LENGTH_SHORT).show()
            })
    }
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: PopularResultsItem?) {
            Picasso.get().load(NetworkConfig.URL_IMAGE+get?.backdropPath).into(itemView.gambarposter);
        }
    }
}