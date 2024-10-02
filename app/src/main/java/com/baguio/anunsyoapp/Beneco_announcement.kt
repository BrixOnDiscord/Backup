package com.baguio.anunsyoapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.baguio.anunsyoapp.databinding.ActivityBenecoAnnouncementBinding

class Beneco_announcement : AppCompatActivity() {

    private lateinit var binding: ActivityBenecoAnnouncementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_beneco_announcement)

    }
}