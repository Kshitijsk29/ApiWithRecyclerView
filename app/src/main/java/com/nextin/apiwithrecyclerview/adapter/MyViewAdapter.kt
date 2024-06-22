package com.nextin.apiwithrecyclerview.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.nextin.apiwithrecyclerview.R
import com.nextin.apiwithrecyclerview.data.Product
import com.squareup.picasso.Picasso

class MyViewAdapter(val context :Activity ,val productArray :ArrayList<Product>)
    :RecyclerView.Adapter<MyViewAdapter.MyViewHolder>(){

        lateinit var myListener :OnItemClickListener
        interface OnItemClickListener{
            fun selectedItem(position: Int)
        }
        fun setOnItemSelectListener(listener :OnItemClickListener){
            myListener = listener
        }
        class MyViewHolder(itemView :View, listener: OnItemClickListener):RecyclerView.ViewHolder(itemView){
             var  title : TextView
             var  image : ShapeableImageView
             var  price :TextView
             var rating :RatingBar

            init {
                title = itemView.findViewById(R.id.productTitle)
                image = itemView.findViewById(R.id.productImage)
                price = itemView.findViewById(R.id.productPrice)
                rating= itemView.findViewById(R.id.rating)

                itemView.setOnClickListener {
                    listener.selectedItem(adapterPosition)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.each_item_view,parent,false)
        return MyViewHolder(view,myListener)
    }
    override fun getItemCount(): Int {
        return productArray.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productArray[position]
        holder.title.text = currentItem.title
        holder.price.text = currentItem.price.toString()
        Picasso.get().load(currentItem.thumbnail).into(holder.image);
        holder.rating.rating = currentItem.rating.toFloat()

    }
}