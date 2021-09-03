package com.example.movies.ViewModel


import android.graphics.drawable.ColorDrawable
import android.os.Bundle


import androidx.appcompat.app.AppCompatActivity
import com.example.movies.Config.NetworkConfig
import com.example.movies.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail.*
import kotlinx.android.synthetic.main.item_popular.view.*

class Detail: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)
        getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        var title = intent?.extras?.getString("title")
        var image = intent?.extras?.getString("image")
        var originalTitle = intent?.extras?.getString("originalTitle")
        var overview = intent?.extras?.getString("overview")

        judul.setText(title)
        originaltitle.setText(originalTitle)
        overview2.setText(overview)
        Picasso.get().load(image).into(gambardetail);
    }
}