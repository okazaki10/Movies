package com.example.movies.presenter


import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast


import androidx.appcompat.app.AppCompatActivity
import com.example.movies.adapter.ReviewAdapter
import com.example.movies.config.NetworkConfig
import com.example.movies.model.ReviewModel
import com.example.movies.R
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.detail.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_popular.view.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.content.DialogInterface
import android.content.Intent

import android.view.WindowManager

import android.os.Build

import com.google.android.material.bottomsheet.BottomSheetDialog


import android.view.View
import android.widget.Button
import android.widget.ImageButton

import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.like.LikeButton

import com.like.OnLikeListener
import kotlinx.android.synthetic.main.favourite.*


class DetailActivity: AppCompatActivity() {
    var sheetBehavior: BottomSheetBehavior<*>? = null
    var sheetDialog: BottomSheetDialog? = null
    var bottom_sheet: View? = null
    var id:String? = ""
    var title:String? = ""
    var image:String? = ""
    var originalTitle:String? = ""
    var overview:String? = ""
    internal var dbHelper = FavouriteDB(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        id = intent?.extras?.getString("id")
        title = intent?.extras?.getString("title")
        image = intent?.extras?.getString("image")
        originalTitle = intent?.extras?.getString("originalTitle")
        overview = intent?.extras?.getString("overview")

        judul.setText(title)
        originaltitle.setText(originalTitle)
        overview2.setText(overview)
        Picasso.get().load(image).into(gambardetail);

        NetworkConfig().getService()
            .getReviews(id!!)
            .enqueue(object : Callback<ReviewModel> {
                override fun onFailure(call: Call<ReviewModel>, t: Throwable) {
                    Toast.makeText(this@DetailActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(call: Call<ReviewModel>, response: Response<ReviewModel>) {
                    rvreview.adapter = ReviewAdapter(response.body()?.results)
                }
            })

        val isi = dbHelper.selectData(id!!)

        add_favourite.isLiked = isi.moveToNext()

        add_favourite.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton) {

                try {
                    dbHelper.insertData(id!!,title!!,image!!,originalTitle!!,overview!!)
                }catch (e: Exception){
                    e.printStackTrace()
                    Toast.makeText(this@DetailActivity, e.message.toString(), Toast.LENGTH_SHORT).show()
                }

            }
            override fun unLiked(likeButton: LikeButton) {

                try {
                    dbHelper.deleteData(id!!)
                }catch (e: Exception){
                    e.printStackTrace()
                    Toast.makeText(this@DetailActivity, e.message.toString(), Toast.LENGTH_SHORT).show()
                }

            }
        })


        bottom_sheet = findViewById(R.id.bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(bottom_sheet!!);

        sharebutton.setOnClickListener(View.OnClickListener { showBottomSheetDialog() })

    }

    fun showDialog(title : String,Message : String){
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
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

            var intent = Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "hey check this out : "+title+", i think it's great");
            startActivity(Intent.createChooser(intent, "Share"));

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