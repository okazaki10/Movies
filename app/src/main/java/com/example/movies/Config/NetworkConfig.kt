package com.example.movies.config


import com.example.movies.config.NetworkConfig.Companion.API_KEY
import com.example.movies.model.NowplayingModel
import com.example.movies.model.PopularModel
import com.example.movies.model.ReviewModel
import com.example.movies.model.TopModel

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

    fun getNowService() = getRetrofit().create(Now::class.java)
    fun getTopService() = getRetrofit().create(Top::class.java)
    fun getPopularService() = getRetrofit().create(Popular::class.java)
    fun getReviewService() = getRetrofit().create(Review::class.java)
}
interface Now {
    @GET("now_playing?api_key="+API_KEY+"&language=en-US&page=1")
    fun getNows(): Call<NowplayingModel>
}

interface Top {
    @GET("top_rated?api_key="+API_KEY+"&language=en-US&page=1")
    fun getTops(): Call<TopModel>
}

interface Popular {
    @GET("popular?api_key="+API_KEY+"&language=en-US&page=1")
    fun getPopulars(): Call<PopularModel>
}

interface Review {
    @GET("{id}/reviews?api_key="+API_KEY+"&language=en-US&page=1")
    fun getReviews(@Path("id")id:String): Call<ReviewModel>
}