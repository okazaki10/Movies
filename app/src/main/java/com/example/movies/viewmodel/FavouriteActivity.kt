package com.example.movies.viewmodel

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.adapter.FavouriteAdapter

import com.example.movies.R
import com.example.movies.model.FavouriteDBModel


import kotlinx.android.synthetic.main.favourite.*

class FavouriteActivity: AppCompatActivity() {
        internal var dbHelper = FavouriteDB(this)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.favourite)

        }

    override fun onStart() {
        super.onStart()

        val res = dbHelper.allData

        var favlist = ArrayList<FavouriteDBModel>()

        while (res.moveToNext()) {

            favlist.add(FavouriteDBModel(res.getString(0),res.getString(1),res.getString(2),
                res.getString(3),res.getString(4),res.getString(5)))
        }

        rvfavourite.adapter = FavouriteAdapter(favlist)
  
    }

    }