package com.baguio.admincrud

import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID
import com.baguio.admincrud.databinding.ActivityBenecointerruptionCreateBinding

class Benecointerruption_create : AppCompatActivity() {

    private lateinit var binding: ActivityBenecointerruptionCreateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBenecointerruptionCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set onClickListener for continue button
        binding.continuebtn.setOnClickListener {
            val serviceAdvisory = binding.SAdvisory.text.toString()
            val date = binding.date.text.toString()
            val timeStarted = binding.timeStarted.text.toString()
            val timeRestored = binding.timeRestored.text.toString()
            val cause = binding.cause.text.toString()
            val areasAffected = binding.areasAffected.text.toString()
            val others = binding.others.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Beneco_Announcements")
            val randomId = UUID.randomUUID().toString()
            val BenecoData = BenecoData(serviceAdvisory, date, timeStarted, timeRestored, cause, areasAffected, others)

            databaseReference.child(randomId).setValue(BenecoData).addOnSuccessListener {
                binding.date.text.clear()
                binding.timeStarted.text.clear()
                binding.timeRestored.text.clear()
                binding.cause.text.clear()
                binding.areasAffected.text.clear()
                binding.others.text.clear()

                // Navigate to Create_Announcement activity
                val intent = Intent(this, Create_Announcement::class.java)
                startActivity(intent)

                // Display success message
                    Toast.makeText(this, "Announcement Published", Toast.LENGTH_SHORT).show()

                }.addOnFailureListener {
                    // Display failure message
                    Toast.makeText(this, "Failed to Publish Announcement", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
