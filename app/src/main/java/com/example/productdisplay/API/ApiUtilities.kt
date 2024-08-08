package com.example.productdisplay.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {
    private var retrofit: Retrofit? = null

    fun getInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")  // Replace with your actual base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    fun getApiInterface(): ApiInterface {
        return getInstance().create(ApiInterface::class.java)
    }
}
