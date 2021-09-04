package com.example.movies.ViewModel

import android.os.Bundle
import com.example.movies.model.FavouriteModel
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.Adapter.FavouriteAdapter

import com.example.movies.R


import kotlinx.android.synthetic.main.favourite.*

class FavouriteActivity: AppCompatActivity() {
        internal var dbHelper = FavouriteDB(this)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.favourite)
            val res = dbHelper.allData

            var favlist = ArrayList<FavouriteModel>()

            while (res.moveToNext()) {

                favlist.add(FavouriteModel(res.getString(0),res.getString(1),res.getString(2),
                res.getString(3),res.getString(4),res.getString(5).substring(0,200)+"..."))
            }

            rvfavourite.adapter = FavouriteAdapter(favlist)
        }
    }