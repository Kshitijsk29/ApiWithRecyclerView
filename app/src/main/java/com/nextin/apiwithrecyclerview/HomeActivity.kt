package com.nextin.apiwithrecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nextin.apiwithrecyclerview.databinding.ActivityHomeBinding
import com.squareup.picasso.Picasso

class HomeActivity : AppCompatActivity() {
    val binding : ActivityHomeBinding by lazy{
        ActivityHomeBinding.inflate(layoutInflater)
    }

    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val imageList = intent.getStringArrayListExtra("images")

        Picasso.get().load(imageList!![count]).into(binding.imageView);

        binding.backBtn.setOnClickListener {
            Picasso.get().load(imageList[count]).into(binding.imageView);
            count = (imageList.size + count-1)%imageList.size
        }
        binding.nextBtn.setOnClickListener {
            count = (imageList.size + count+1)%imageList.size
            Picasso.get().load(imageList[count]).into(binding.imageView);
        }

    }
}