package com.example.productdisplay.Repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.productdisplay.API.ApiInterface
import com.example.productdisplay.Model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository(private val apiInterface: ApiInterface) {

    private val _productList = MutableLiveData<Product>()
    val productList: LiveData<Product>
        get() = _productList

    fun fetchProducts() {
        val call = apiInterface.getProduct()
        call.enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful) {
                    _productList.postValue(response.body())
                } else {
                    // Handle the error response
                    Log.d("APIDATA", "some error in repo")
                }
            }


            override fun onFailure(p0: Call<Product>, p1: Throwable) {
                TODO("Not yet implemented")
                Log.d("APIDATA", "api call fails")
            }
        })
    }
}
