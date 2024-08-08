package com.example.productdisplay.API

import com.example.productdisplay.Model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface
{
    @GET("products")
    fun getProduct(): Call<Product>

}