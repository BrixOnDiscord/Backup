package com.baguio.admincrud

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.baguio.admincrud.databinding.ActivityBenecoUpdateBinding // Ensure the binding name matches the layout file
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class Beneco_update : AppCompatActivity() {

    private lateinit var binding: ActivityBenecoUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBenecoUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Date Picker
        binding.date.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.date.text = selectedDate
            }, year, month, day).show()
        }

        // Time Picker for Time Started
        binding.timeStarted.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                binding.timeStarted.text = selectedTime
            }, hour, minute, true).show()
        }

        // Time Picker for Time Restored
        binding.timeRestored.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                binding.timeRestored.text = selectedTime
            }, hour, minute, true).show()
        }

        // Set onClickListener for continue button
        binding.continuebtn.setOnClickListener {
            // Get the input data
            val id = binding.referenceid.text.toString()
            val serviceAdvisory = binding.SAdvisory.text.toString()
            val date = binding.date.text.toString()
            val timeStarted = binding.timeStarted.text.toString()
            val timeRestored = binding.timeRestored.text.toString()
            val cause = binding.cause.text.toString()
            val areasAffected = binding.areasAffected.text.toString()
            val others = binding.others.text.toString()

            updateData(id, serviceAdvisory, date, timeStarted, timeRestored, cause, areasAffected, others)
        }
    }

    private fun updateData(id: String, serviceAdvisory: String, date: String, timeStarted: String, timeRestored: String, cause: String, areasAffected: String, other: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Beneco_Announcements")

        // Check if the ID exists before attempting to update
        databaseReference.child(id).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                // ID exists, proceed with update
                val benecoData = mapOf(
                    "serviceAdvisory" to serviceAdvisory,
                    "date" to date,
                    "timeStarted" to timeStarted,
                    "timeRestored" to timeRestored,
                    "cause" to cause,
                    "areasAffected" to areasAffected,
                    "others" to other
                )

                // Update the database using the UID
                databaseReference.child(id).updateChildren(benecoData)
                    .addOnSuccessListener {
                        // Clear the input fields
                        binding.SAdvisory.text.clear()
                        binding.date.text = ""
                        binding.timeStarted.text = ""
                        binding.timeRestored.text = ""
                        binding.cause.text.clear()
                        binding.areasAffected.text.clear()
                        binding.others.text.clear()

                        Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(this, "Update Failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                // ID does not exist, show a toast message
                Toast.makeText(this, "ID does not exist. Please enter a correct UID.", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Error retrieving data: ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
