package com.example.productdisplay.View

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.productdisplay.Model.ProductX
import com.example.productdisplay.R
import com.squareup.picasso.Picasso

class ProductListAdapter(
    private val context: Activity,
    var productArrayList: List<ProductX>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_row, parent, false)
        return MyViewHolder(itemView, itemClickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productArrayList[position]
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
        Picasso.get().load(currentItem.thumbnail).into(holder.image)
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    fun updateProductList(newProductList: List<ProductX>) {
        productArrayList = newProductList
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View, itemClickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var title: TextView = itemView.findViewById(R.id.productName)
        var image: ImageView = itemView.findViewById(R.id.productImage)
        var description: TextView = itemView.findViewById(R.id.productDescription)
        private val listener: OnItemClickListener = itemClickListener

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onItemClick(adapterPosition)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
