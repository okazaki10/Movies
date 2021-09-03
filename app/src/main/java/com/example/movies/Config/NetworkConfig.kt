package com.example.movies.Config


import com.example.movies.Config.NetworkConfig.Companion.API_KEY
import com.example.movies.Model.PopularModel
import com.example.movies.Model.ReviewModel

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class NetworkConfig {
    companion object{
        const val URL:String = "https://api.themoviedb.org/3/movie/"
        const val URL_IMAGE:String = "https://image.tmdb.org/t/p/w500"
        const val API_KEY:String = "f6f98d7d8d6378d73921c90080ffcb0f"
    }


    // set interceptor
    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return  okHttpClient
    }
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getPopularService() = getRetrofit().create(Popular::class.java)
    fun getReviewService() = getRetrofit().create(Review::class.java)
}
interface Popular {
    @GET("popular?api_key="+API_KEY+"&language=en-US&page=1")
    fun getPopulars(): Call<PopularModel>
}

interface Review {
    @GET("{id}/reviews?api_key="+API_KEY+"&language=en-US&page=1")
    fun getReviews(@Path("id")id:String): Call<ReviewModel>
}