package com.baguio.admincrud

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.baguio.admincrud.databinding.ActivityOthersCreateBinding
import com.baguio.admincrud.databinding.ActivityRoadBlocksCreateBinding

class RoadBlocks_create : AppCompatActivity() {
    private lateinit var binding: ActivityRoadBlocksCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRoadBlocksCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set onClickListener for continue button
        binding.continuebtn.setOnClickListener {
            // Get the input data
            val AnnouncementTitle = binding.AnnouncementTitle.text.toString()
            val info = binding.information.text.toString()

            // Create intent to start publish activity and pass the input data
            val intent = Intent(this, RoadBlocks_publish::class.java)
            intent.putExtra("title", AnnouncementTitle)
            intent.putExtra("information", info)
            startActivity(intent)
        }
    }
}