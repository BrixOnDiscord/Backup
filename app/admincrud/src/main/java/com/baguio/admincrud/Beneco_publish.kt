package com.baguio.admincrud

import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.baguio.admincrud.databinding.ActivityBenecoPublishBinding
import java.text.SimpleDateFormat
import java.util.*

class Beneco_publish : AppCompatActivity() {

    private lateinit var binding: ActivityBenecoPublishBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBenecoPublishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Beneco_Announcements")

        // Get the data passed from the previous activity
        val intent = intent
        val serviceAdvisory = intent.getStringExtra("serviceAdvisory")
        val date = intent.getStringExtra("date")
        val timeStarted = intent.getStringExtra("timeStarted")
        val timeRestored = intent.getStringExtra("timeRestored")
        val cause = intent.getStringExtra("cause")
        val areasAffected = intent.getStringExtra("areasAffected")
        val others = intent.getStringExtra("others")

        val formattedDate = formatDate(date)

        // Set the data to the views for review
        binding.SAdvisory.text = serviceAdvisory
        binding.date.text = formattedDate
        binding.timeStarted.text = "Time Started: $timeStarted"
        binding.timeRestored.text = "Time Restored: $timeRestored"
        binding.cause.text = "Cause: $cause"
        binding.areasAffected.text = "Areas Affected: $areasAffected"
        binding.others.text = others

        // Continue button functionality: Create a new announcement with a random ID
        binding.continuebtn.setOnClickListener {
            val randomId = UUID.randomUUID().toString()
            val benecoData = BenecoData(serviceAdvisory, formattedDate, timeStarted, timeRestored, cause, areasAffected, others)

            databaseReference.child(randomId).setValue(benecoData).addOnSuccessListener {
                clearFields()
                // Display success message
                Toast.makeText(this, "Published", Toast.LENGTH_SHORT).show()
                navigateToCreateAnnouncement()
            }.addOnFailureListener { error ->
                // Display failure message with error details
                Toast.makeText(this, "Failed to Publish: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Helper function to clear the fields after success
    private fun clearFields() {
        binding.date.text = ""
        binding.timeStarted.text = ""
        binding.timeRestored.text = ""
        binding.cause.text = ""
        binding.areasAffected.text = ""
        binding.others.text = ""
    }

    // Helper function to navigate to Create_Announcement after successful operation
    private fun navigateToCreateAnnouncement() {
        val intent = Intent(this, Create_Announcement::class.java)
        startActivity(intent)
        finish() // Optional: call finish() to remove this activity from the back stack
    }

    // Helper function to format the date
    private fun formatDate(date: String?): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
            val parsedDate = inputFormat.parse(date ?: "") // Handle null dates
            outputFormat.format(parsedDate)
        } catch (e: Exception) {
            date ?: "Invalid date"
        }
    }
}
