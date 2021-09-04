package com.example.movies.contract

import android.content.Intent
import com.example.movies.adapter.*
import com.example.movies.presenter.FavouriteDB

interface MainContract {
    interface ViewMain{
        fun showPopular(adapter: PopularAdapter)
        fun showTop(adapter:TopAdapter)
        fun showNowplaying(adapter:NowplayingAdapter)
        fun onErrorShow(message:String)
    }

    interface ViewDetail{
        fun showReview(adapter:ReviewAdapter)
        fun onErrorShow(message: String)
        fun shareIntent(intent: Intent)
        fun onInfoShow(message: String)
    }

    interface ViewFavourite{
        fun showFavourite(adapter: FavouriteAdapter)
    }

    interface PresenterMain{
        fun loadPopular()
        fun loadTop()
        fun loadNowplaying()
    }

    interface PresenterDetail{
        fun loadReview(id:String)
        fun share(message:String)
        fun addFavourite(dbHelper: FavouriteDB,id:String, title:String, image:String, originalTitle:String, overview:String)
        fun deleteFavourite(dbHelper: FavouriteDB,id:String)
    }

    interface PresenterFavourite{
        fun loadFavourite(dbHelper: FavouriteDB)
    }
}