package com.example.productdisplay.View

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productdisplay.API.ApiUtilities
import com.example.productdisplay.Model.ProductX
import com.example.productdisplay.R
import com.example.productdisplay.Repository.ProductRepository
import com.example.productdisplay.ViewModel.ProductViewModel
import com.example.productdisplay.ViewModel.ProductViewModelFactory

class ProductList : AppCompatActivity(), ProductListAdapter.OnItemClickListener {

    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModelFactory(ProductRepository(ApiUtilities.getApiInterface()))
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductListAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        recyclerView = findViewById(R.id.productRecyclerView)
        progressBar = findViewById(R.id.progressBar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProductListAdapter(this, listOf(), this)
        recyclerView.adapter = adapter

        // Show ProgressBar and hide RecyclerView while loading data
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE

        productViewModel.fetchProducts()

        productViewModel.productList.observe(this) { product ->
            val productList = product.products
            adapter.updateProductList(productList)

            // Hide ProgressBar and show RecyclerView once data is loaded
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE

            Log.d("APIDATA", "onCreate: ${productList.toString()}")
        }
    }

    override fun onItemClick(position: Int) {
        val clickedProduct = adapter.productArrayList[position]
        val intent = Intent(this, ProductDescription::class.java).apply {
            putExtra("PRODUCT_ID", clickedProduct.id)
            putExtra("PRODUCT_TITLE", clickedProduct.title)
            putExtra("PRODUCT_DESCRIPTION", clickedProduct.description)
            putExtra("PRODUCT_PRICE", clickedProduct.price)
            putExtra("PRODUCT_IMAGE_URL", clickedProduct.thumbnail)
            putExtra("PRODUCT_CATEGORY", clickedProduct.category)
            putExtra("PRODUCT_BRAND", clickedProduct.brand)
        }
        startActivity(intent)
    }
}
