package com.example.productdisplay.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.productdisplay.Model.Product
import com.example.productdisplay.Repository.ProductRepository

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    val productList: LiveData<Product>
        get() = repository.productList

    fun fetchProducts() {
        repository.fetchProducts()
    }
}


