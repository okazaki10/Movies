package com.example.movies.viewmodel

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.adapter.PopularAdapter
import com.example.movies.config.NetworkConfig
import com.example.movies.model.PopularModel
import com.example.movies.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_popular.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.content.Intent
import android.view.MenuItem
import com.example.movies.adapter.NowplayingAdapter
import com.example.movies.adapter.TopAdapter
import com.example.movies.config.Top
import com.example.movies.model.NowplayingModel
import com.example.movies.model.TopModel


class MainActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        NetworkConfig().getPopularService()
            .getPopulars()
            .enqueue(object : Callback<PopularModel> {
                override fun onFailure(call: Call<PopularModel>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(call: Call<PopularModel>, response: Response<PopularModel>) {
                    rvpop.adapter = PopularAdapter(response.body()?.results)
                }
            })

        NetworkConfig().getTopService()
            .getTops()
            .enqueue(object : Callback<TopModel> {
                override fun onFailure(call: Call<TopModel>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(call: Call<TopModel>, response: Response<TopModel>) {
                    rvtop.adapter = TopAdapter(response.body()?.results)
                }
            })

        NetworkConfig().getNowService()
            .getNows()
            .enqueue(object : Callback<NowplayingModel> {
                override fun onFailure(call: Call<NowplayingModel>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(call: Call<NowplayingModel>, response: Response<NowplayingModel>) {
                    rvnow.adapter = NowplayingAdapter(response.body()?.results)
                }
            })



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