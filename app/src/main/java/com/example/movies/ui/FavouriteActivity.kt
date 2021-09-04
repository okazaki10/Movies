package com.example.movies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.adapter.FavouriteAdapter

import com.example.movies.R
import com.example.movies.contract.MainContract
import com.example.movies.presenter.FavouriteActivityPresenter
import com.example.movies.presenter.FavouriteDB


import kotlinx.android.synthetic.main.favourite.*

class FavouriteActivity: AppCompatActivity(),MainContract.ViewFavourite {
    internal var dbHelper = FavouriteDB(this)
    lateinit var presenter: MainContract.PresenterFavourite

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.favourite)
        presenter = FavouriteActivityPresenter(this)
        }

    override fun onStart() {
        super.onStart()
        presenter.loadFavourite(dbHelper)

    }

    override fun showFavourite(adapter: FavouriteAdapter) {
       rvfavourite.adapter = adapter
    }

    }