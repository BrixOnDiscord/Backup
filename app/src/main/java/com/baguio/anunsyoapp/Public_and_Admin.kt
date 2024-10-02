package com.baguio.anunsyoapp

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Public_and_Admin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_public_and_admin)

        // Go to public user page
        val btnPublicUser = findViewById<Button>(R.id.publicbtn)
        btnPublicUser.setOnClickListener {
            val userbtnIntent = Intent(this, Userpage::class.java)
            startActivity(userbtnIntent)
        }

        // Go to admin page in another package
        val btnAdminUser = findViewById<Button>(R.id.adminbtn)
        btnAdminUser.setOnClickListener {
            val packageName = "com.baguio.admincrud"
            val className = "com.baguio.admincrud.Create_Announcement"
            val intent = Intent()
            intent.component = ComponentName(packageName, className)
            startActivity(intent)
        }
    }
}
