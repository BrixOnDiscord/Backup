package com.baguio.admincrud

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.baguio.admincrud.databinding.ActivityToawaterInterruptionBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID
import android.widget.Toast

class TOAWater_Interruption : AppCompatActivity() {

    private lateinit var binding: ActivityToawaterInterruptionBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityToawaterInterruptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.continuebtn.setOnClickListener {
            val Date_when = binding.date.text.toString()
            val Areas_affected = binding.areasAffected.text.toString()
            val Reason = binding.reason.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Badiwa_Announcement")
            val Badiwadata = Badiwadata(Date_when, Areas_affected, Reason)
            val randomId = UUID.randomUUID().toString()
            databaseReference.child(randomId).setValue(Badiwadata).addOnSuccessListener {
                binding.date.text.clear()
                binding.areasAffected.text.clear()
                binding.reason.text.clear()

                Toast.makeText(this, "Published", Toast.LENGTH_SHORT).show()

                // Navigate to Create_Announcement activity
                val intent = Intent(this, Create_Announcement::class.java)
                startActivity(intent)

            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}