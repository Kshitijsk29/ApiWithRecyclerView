package com.nextin.apiwithrecyclerview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nextin.apiwithrecyclerview.adapter.MyViewAdapter
import com.nextin.apiwithrecyclerview.data.MyData

import com.nextin.apiwithrecyclerview.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val binding :ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val builder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = builder.getProductData()

        retrofitData.enqueue(object : Callback<MyData>{
            override fun onResponse(p0: Call<MyData>, response: Response<MyData>) {
                val responseBody = response.body()

                val productData = responseBody?.products!!

                binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                val myAdapter = MyViewAdapter(this@MainActivity , productData)
                binding.recyclerView.adapter = myAdapter

            }
            override fun onFailure(p0: Call<MyData>, p1: Throwable) {
                Log.d("Main Error","On Failure is "+ p1.message)
            }
        })

    }
}