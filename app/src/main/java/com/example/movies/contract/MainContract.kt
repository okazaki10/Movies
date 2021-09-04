package com.example.movies.contract

import com.example.movies.adapter.NowplayingAdapter
import com.example.movies.adapter.PopularAdapter
import com.example.movies.adapter.TopAdapter

interface MainContract {
    interface View{
        fun showPopular(adapter: PopularAdapter)
        fun showTop(adapter:TopAdapter)
        fun showNowplaying(adapter:NowplayingAdapter)
        fun onErrorShow(message:String)
    }

    interface Presenter{
        fun loadPopular()
        fun loadTop()
        fun loadNowplaying()
    }
}