package com.example.movies.presenter

import com.example.movies.adapter.FavouriteAdapter
import com.example.movies.contract.MainContract
import com.example.movies.model.FavouriteDBModel

class FavouriteActivityPresenter(private val view: MainContract.ViewFavourite): MainContract.PresenterFavourite {
    override fun loadFavourite(dbHelper:FavouriteDB){
        val res = dbHelper.allData
        var favlist = ArrayList<FavouriteDBModel>()
        while (res.moveToNext()) {
            favlist.add(
                FavouriteDBModel(res.getString(0),res.getString(1),res.getString(2),
                res.getString(3),res.getString(4),res.getString(5))
            )
        }
        view.showFavourite(FavouriteAdapter(favlist))
    }
}