package com.baguio.anunsyoapp

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Public_and_Admin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_public_and_admin)

        // go to public and admin page
        val btnpublicuser = findViewById<Button>(R.id.publicbtn)
        btnpublicuser.setOnClickListener {
            val userbtn_Intent = Intent(this,Userpage::class.java)
            startActivity(userbtn_Intent) }


    }
}
