package com.baguio.anunsyoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // go to public and admin page
        val btnGetstarted = findViewById<Button>(R.id.getstarted)
        btnGetstarted.setOnClickListener {
            val getstarted_Intent = Intent(this,Public_and_Admin::class.java)
            startActivity(getstarted_Intent)
        }
    }

}