package com.example.movies.ViewModel

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.Adapter.PopularAdapter
import com.example.movies.Config.NetworkConfig
import com.example.movies.Model.PopularModel
import com.example.movies.Model.PopularResultsItem
import com.example.movies.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.homepage.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePage:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        NetworkConfig().getPopularService()
            .getPopulars()
            .enqueue(object : Callback<PopularModel> {
                override fun onFailure(call: Call<PopularModel>, t: Throwable) {
                    Toast.makeText(this@HomePage, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(
                    call: Call<PopularModel>,
                    response: Response<PopularModel>
                ) {
                    rvpop.adapter = PopularAdapter(response.body()?.results)
                }
            })
    }
}