package com.example.movies.ViewModel


import android.os.Bundle
import android.widget.Toast


import androidx.appcompat.app.AppCompatActivity
import com.example.movies.Adapter.ReviewAdapter
import com.example.movies.Config.NetworkConfig
import com.example.movies.Model.ReviewModel
import com.example.movies.R
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_popular.view.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.content.DialogInterface

import android.view.WindowManager

import android.os.Build

import com.google.android.material.bottomsheet.BottomSheetDialog


import android.view.View
import android.widget.Button
import android.widget.ImageButton

import com.google.android.material.bottomsheet.BottomSheetBehavior



class DetailActivity: AppCompatActivity() {
    var sheetBehavior: BottomSheetBehavior<*>? = null
    var sheetDialog: BottomSheetDialog? = null
    var bottom_sheet: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        var id = intent?.extras?.getInt("id").toString()
        var title = intent?.extras?.getString("title")
        var image = intent?.extras?.getString("image")
        var originalTitle = intent?.extras?.getString("originalTitle")
        var overview = intent?.extras?.getString("overview")

        judul.setText(title)
        originaltitle.setText(originalTitle)
        overview2.setText(overview)
        Picasso.get().load(image).into(gambardetail);

        NetworkConfig().getReviewService()
            .getReviews(id!!)
            .enqueue(object : Callback<ReviewModel> {
                override fun onFailure(call: Call<ReviewModel>, t: Throwable) {
                    Toast.makeText(this@DetailActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(call: Call<ReviewModel>, response: Response<ReviewModel>) {
                    rvreview.adapter = ReviewAdapter(response.body()?.results)
                }
            })

        bottom_sheet = findViewById(R.id.bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(bottom_sheet!!);


        sharebutton.setOnClickListener(View.OnClickListener { showBottomSheetDialog() })
    }

    private fun showBottomSheetDialog() {
        val view: View = layoutInflater.inflate(R.layout.sheet, null)
        if (sheetBehavior?.getState() === BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior?.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }
        view.findViewById<Button>(R.id.bt_close).setOnClickListener( {

                sheetDialog?.dismiss()

        })
        view.findViewById<ImageButton>(R.id.facebook).setOnClickListener({

                Toast.makeText(applicationContext, "Makasih ya sudah subscribe", Toast.LENGTH_SHORT)
                    .show()

        })
        sheetDialog = BottomSheetDialog(this@DetailActivity)
        sheetDialog?.setContentView(view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            sheetDialog?.getWindow()?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        sheetDialog?.show()
        sheetDialog?.setOnDismissListener(DialogInterface.OnDismissListener { sheetDialog = null })
    }
}