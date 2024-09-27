package com.baguio.anunsyoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.baguio.admincrud.Create_Announcement // Import Create_Announcement from the other package

class Public_and_Admin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_and_admin)

        // go to userpage for public users
        val btnGetstarted = findViewById<Button>(R.id.publicbtn)
        btnGetstarted.setOnClickListener {
            val publicIntent = Intent(this, Userpage::class.java)
            startActivity(publicIntent)
        }

        // admin button to create announcement page
        val btnAdmin = findViewById<Button>(R.id.adminbtn)
        btnAdmin.setOnClickListener {
            goToCreateAnnouncement()  // Call the method
        }
    }

    // Method for handling admin button click
    fun goToCreateAnnouncement() {
        val adminIntent = Intent(this, com.baguio.admincrud.Create_Announcement::class.java)
        startActivity(adminIntent)
    }
}
