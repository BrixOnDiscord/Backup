package com.baguio.admincrud

import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.baguio.admincrud.Badiwadata // Make sure to create this data class similar to BenecoData
import com.baguio.admincrud.Create_Announcement
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.baguio.admincrud.databinding.ActivityBadiwaPublishBinding
import java.security.cert.CertPathValidatorException.Reason
import java.text.SimpleDateFormat
import java.util.*

class Badiwa_publish : AppCompatActivity() {

    private lateinit var binding: ActivityBadiwaPublishBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBadiwaPublishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the data passed from the previous activity
        val intent = intent
        val waterAdvisory = intent.getStringExtra("waterAdvisory")
        val date = intent.getStringExtra("date")
        val areasAffected = intent.getStringExtra("areasAffected")
        val others = intent.getStringExtra("others")

        val formattedDate = formatDate(date)

        binding.SWAdvisory.text = waterAdvisory
        binding.date.text = formattedDate
        binding.areasAffected.text = "Areas Affected: $areasAffected"
        binding.others.text = others

        binding.continuebtn.setOnClickListener {
            databaseReference = FirebaseDatabase.getInstance().getReference("Badiwa_Announcements")
            val randomId = UUID.randomUUID().toString()
            val waterData = Badiwadata(waterAdvisory, formattedDate, areasAffected, others)

            databaseReference.child(randomId).setValue(waterData).addOnSuccessListener {
                // Clear input fields
                binding.date.text = ""
                binding.areasAffected.text = ""
                binding.others.text = ""

                // Display success message
                Toast.makeText(this, "Published", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Create_Announcement::class.java)
                startActivity(intent)

            }.addOnFailureListener {
                // Display failure message
                Toast.makeText(this, "Failed to Publish", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun formatDate(date: String?): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
            val parsedDate = inputFormat.parse(date ?: "")  // Handle null dates
            outputFormat.format(parsedDate)
        } catch (e: Exception) {
            date ?: "-"
        }
    }
}
