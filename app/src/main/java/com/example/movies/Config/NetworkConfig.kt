package com.example.movies.Config

import com.example.movies.Model.PopularModel
import com.example.movies.Model.PopularResultsItem
import com.example.movies.Model.ResultUsers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class NetworkConfig {
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
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService() = getRetrofit().create(Popular::class.java)
}
interface Popular {
    @GET("popular?api_key=f6f98d7d8d6378d73921c90080ffcb0f&language=en-US&page=1")
    fun getPopulars(): Call<PopularModel>
}