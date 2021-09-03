package com.example.movies.ViewModel


import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast


import androidx.appcompat.app.AppCompatActivity
import com.example.movies.Adapter.PopularAdapter
import com.example.movies.Adapter.ReviewAdapter
import com.example.movies.Config.NetworkConfig
import com.example.movies.Model.ReviewModel
import com.example.movies.Model.ReviewResultsItem
import com.example.movies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail.*
import kotlinx.android.synthetic.main.homepage.*
import kotlinx.android.synthetic.main.item_popular.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Detail: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        var id = intent?.extras?.getInt("id").toString()
        var title = intent?.extras?.getString("title")
        var image = intent?.extras?.getString("image")
        var originalTitle = intent?.extras?.getString("originalTitle")
        var overview = intent?.extras?.getString("overview")

        judul.setText(title)
        originaltitle.setText(originalTitle)
        overview2.setText(overview)
        Picasso.get().load(image).into(gambardetail);

        NetworkConfig().getReviewService()
            .getReviews(id!!)
            .enqueue(object : Callback<ReviewModel> {
                override fun onFailure(call: Call<ReviewModel>, t: Throwable) {
                    Toast.makeText(this@Detail, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(call: Call<ReviewModel>, response: Response<ReviewModel>) {
                    rvreview.adapter = ReviewAdapter(response.body()?.results)
                }
            })
    }
}