package com.example.movies.presenter


import com.example.movies.adapter.NowplayingAdapter
import com.example.movies.adapter.PopularAdapter
import com.example.movies.adapter.TopAdapter
import com.example.movies.config.NetworkConfig
import com.example.movies.contract.MainContract
import com.example.movies.model.NowplayingModel
import com.example.movies.model.PopularModel
import com.example.movies.model.TopModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenter(private val view:MainContract.ViewMain):MainContract.PresenterMain {

    override fun loadPopular(){
        NetworkConfig().getService()
            .getPopulars()
            .enqueue(object : Callback<PopularModel> {
                override fun onFailure(call: Call<PopularModel>, t: Throwable) {
                    view.onErrorShow(t.localizedMessage)

                }
                override fun onResponse(call: Call<PopularModel>, response: Response<PopularModel>) {
                    view.showPopular(PopularAdapter(response.body()?.results))
                }
            })
     }

    override fun loadTop() {
        NetworkConfig().getService()
            .getTops()
            .enqueue(object : Callback<TopModel> {
                override fun onFailure(call: Call<TopModel>, t: Throwable) {
                    view.onErrorShow(t.localizedMessage)
                }
                override fun onResponse(call: Call<TopModel>, response: Response<TopModel>) {
                    view.showTop(TopAdapter(response.body()?.results))
                }
            })
    }

    override fun loadNowplaying() {
        NetworkConfig().getService()
            .getNows()
            .enqueue(object : Callback<NowplayingModel> {
                override fun onFailure(call: Call<NowplayingModel>, t: Throwable) {
                    view.onErrorShow(t.localizedMessage)
                }
                override fun onResponse(call: Call<NowplayingModel>, response: Response<NowplayingModel>) {
                    view.showNowplaying(NowplayingAdapter(response.body()?.results))
                }
            })
    }

}