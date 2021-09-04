package com.example.movies.ViewModel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.Adapter.FavouriteAdapter

import com.example.movies.R
import com.example.movies.Model.FavouriteDBModel


import kotlinx.android.synthetic.main.favourite.*

class FavouriteActivity: AppCompatActivity() {
        internal var dbHelper = FavouriteDB(this)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.favourite)
            val res = dbHelper.allData

            var favlist = ArrayList<FavouriteDBModel>()

            while (res.moveToNext()) {

                favlist.add(FavouriteDBModel(res.getString(0),res.getString(1),res.getString(2),
                res.getString(3),res.getString(4),res.getString(5)))
            }

            rvfavourite.adapter = FavouriteAdapter(favlist)
        }
    }