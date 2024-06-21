package com.nextin.apiwithrecyclerview.data

data class MyData(
    val limit: Int,
    val products: ArrayList<Product>,
    val skip: Int,
    val total: Int
)