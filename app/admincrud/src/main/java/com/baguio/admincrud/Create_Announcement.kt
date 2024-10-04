package com.baguio.admincrud

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Create_Announcement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_announcement)

        // go to announcement_types
        val btncreate_announcement = findViewById<Button>(R.id.create_announcement)
        btncreate_announcement.setOnClickListener {
            val creatAnn_Intent = Intent(this,cui_admin::class.java)
            startActivity(creatAnn_Intent)
        }
    }
}