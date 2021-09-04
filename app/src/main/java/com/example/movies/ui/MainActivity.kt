package com.example.movies.ui

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.adapter.PopularAdapter
import com.example.movies.R

import kotlinx.android.synthetic.main.activity_main.*

import android.content.Intent
import android.view.MenuItem
import com.example.movies.adapter.NowplayingAdapter
import com.example.movies.adapter.TopAdapter
import com.example.movies.contract.MainContract

import com.example.movies.presenter.FavouriteActivity
import com.example.movies.presenter.MainActivityPresenter


class MainActivity:AppCompatActivity(),MainContract.ViewMain {

    lateinit var presenter: MainContract.PresenterMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        presenter = MainActivityPresenter(this)
        presenter.loadPopular()
        presenter.loadTop()
        presenter.loadNowplaying()
    }

    override fun showPopular(adapter: PopularAdapter) {
        rvpop.adapter = adapter
    }

    override fun showTop(adapter: TopAdapter) {
        rvtop.adapter = adapter
    }
    override fun showNowplaying(adapter: NowplayingAdapter) {
        rvnow.adapter = adapter
    }

    override fun onErrorShow(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Menu icons are inflated just as they were with actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.toolbarmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.menu_favorite -> {
                startActivity(Intent(this, FavouriteActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}