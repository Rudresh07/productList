package com.example.productdisplay.View

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.productdisplay.R
import com.example.productdisplay.databinding.ActivityProductDescriptionBinding
import com.squareup.picasso.Picasso

class ProductDescription : AppCompatActivity() {
    private lateinit var binding: ActivityProductDescriptionBinding
    private var quantity = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener { onBackPressed() }

        binding.increaseQuantity.setOnClickListener {
            quantity++
            updateQuantity()
        }

        binding.decreaseQuantity.setOnClickListener {
            if (quantity > 0) {
                quantity--
                updateQuantity()
            } else {
                Toast.makeText(this, "Quantity cannot be less than 0", Toast.LENGTH_SHORT).show()
            }
        }

        val productId = intent.getIntExtra("PRODUCT_ID", -1)
        val productTitle = intent.getStringExtra("PRODUCT_TITLE") ?: ""
        val productDescription = intent.getStringExtra("PRODUCT_DESCRIPTION") ?: ""
        val productPrice = intent.getDoubleExtra("PRODUCT_PRICE", 0.0)
        val productImageUrl = intent.getStringExtra("PRODUCT_IMAGE_URL") ?: ""
        val productCategory = intent.getStringExtra("PRODUCT_CATEGORY") ?: ""
        val productBrand = intent.getStringExtra("PRODUCT_BRAND") ?: ""

        // Now use these details to populate your UI
        findViewById<TextView>(R.id.productTitle).text = productTitle
        findViewById<TextView>(R.id.productDescription).text = productDescription
        findViewById<TextView>(R.id.productPrice).text = "Price: $${productPrice}"

        Picasso.get()
            .load(productImageUrl)
            .into(findViewById<ImageView>(R.id.productImage))
        findViewById<TextView>(R.id.category).text = productCategory
        findViewById<TextView>(R.id.productBrand).text = productBrand

        binding.addToCartButton.setOnClickListener {
            Toast.makeText(this, "Product ${productTitle} added to cart. Quantity: $quantity", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateQuantity() {
        binding.quantity.text =quantity.toString()
    }
}
