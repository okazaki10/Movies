package com.example.movies.presenter

import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.movies.adapter.PopularAdapter
import com.example.movies.adapter.ReviewAdapter
import com.example.movies.config.NetworkConfig
import com.example.movies.contract.MainContract
import com.example.movies.model.ReviewModel
import kotlinx.android.synthetic.main.detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivityPresenter(private val view: MainContract.ViewDetail): MainContract.PresenterDetail{
    override fun loadReview(id:String) {
        NetworkConfig().getService()
            .getReviews(id)
            .enqueue(object : Callback<ReviewModel> {
                override fun onFailure(call: Call<ReviewModel>, t: Throwable) {
                    view.onErrorShow(t.localizedMessage)
                }
                override fun onResponse(call: Call<ReviewModel>, response: Response<ReviewModel>) {
                    view.showReview(ReviewAdapter(response.body()?.results))
                }
            })
    }

    override fun share(message:String){
        var intent = Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        view.shareIntent(intent)
    }
}