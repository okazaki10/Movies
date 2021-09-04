package com.example.movies.contract

import android.content.Intent
import com.example.movies.adapter.NowplayingAdapter
import com.example.movies.adapter.PopularAdapter
import com.example.movies.adapter.ReviewAdapter
import com.example.movies.adapter.TopAdapter

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
    }

    interface PresenterMain{
        fun loadPopular()
        fun loadTop()
        fun loadNowplaying()
    }

    interface PresenterDetail{
        fun loadReview(id:String)
        fun share(message:String)
    }
}