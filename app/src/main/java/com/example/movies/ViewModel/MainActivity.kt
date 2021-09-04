package com.example.movies.ViewModel

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.Adapter.PopularAdapter
import com.example.movies.Config.NetworkConfig
import com.example.movies.Model.PopularModel
import com.example.movies.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_popular.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.content.Intent
import android.view.MenuItem


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