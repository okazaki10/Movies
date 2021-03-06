package com.example.movies.presenter

import android.content.Intent
import com.example.movies.adapter.ReviewAdapter
import com.example.movies.config.NetworkConfig
import com.example.movies.contract.MainContract
import com.example.movies.model.ReviewModel
import com.example.movies.sqlitedb.FavouriteDB
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

    override fun addFavourite(dbHelper: FavouriteDB, id:String, title:String, image:String, originalTitle:String, overview:String) {
        try {
            dbHelper.insertData(id, title, image, originalTitle, overview)
        } catch (e: Exception) {
            view.onInfoShow(e.message.toString())
        }
    }

    override fun deleteFavourite(dbHelper: FavouriteDB, id:String) {
        try {
            dbHelper.deleteData(id)
        } catch (e: Exception) {
            view.onInfoShow(e.message.toString())
        }
    }
}