package com.baguio.admincrud

import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.baguio.admincrud.databinding.ActivityOthersPublishBinding
import java.util.UUID

class Others_publish : AppCompatActivity() {

    private lateinit var binding: ActivityOthersPublishBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOthersPublishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the data passed from the previous activity
        val intent = intent
        val title = intent.getStringExtra("title")
        val information = intent.getStringExtra("information")

        binding.AnnouncementTitle.text = title
        binding.others.text = information

        binding.continuebtn.setOnClickListener {
            // Initialize Firebase database reference
            databaseReference = FirebaseDatabase.getInstance().getReference("Others_Announcements")
            val randomId = UUID.randomUUID().toString()

            // Create an instance of OthersData
            val othersData = OthersData(title, information)

            // Save data to Firebase
            databaseReference.child(randomId).setValue(othersData).addOnSuccessListener {
                // Clear input fields or reset UI if needed
                binding.AnnouncementTitle.text = ""
                binding.others.text = ""

                // Display success message
                Toast.makeText(this, "Published", Toast.LENGTH_SHORT).show()
                // Navigate back to the announcement creation screen
                val intent = Intent(this, Create_Announcement::class.java)
                startActivity(intent)

            }.addOnFailureListener {
                // Display failure message
                Toast.makeText(this, "Failed to Publish", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
