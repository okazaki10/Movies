package com.example.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movies.Adapter.UsersAdapter
import com.example.movies.Config.NetConfig
import com.example.movies.Model.ResultUsers
import com.example.movies.ViewModel.HomePage
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener{ startActivity(Intent(this,HomePage::class.java))}
        NetConfig().getService()
            .getUsers()
            .enqueue(object : Callback<List<ResultUsers>> {
                override fun onFailure(call: Call<List<ResultUsers>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(
                    call: Call<List<ResultUsers>>,
                    response: Response<List<ResultUsers>>
                ) {
                    rvUser.adapter = UsersAdapter(response.body())

                }
            })
    }
}