package com.baguio.admincrud

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.baguio.admincrud.databinding.ActivityBenecointerruptionCreateBinding
import java.util.*

class Benecointerruption_create : AppCompatActivity() {

    private lateinit var binding: ActivityBenecointerruptionCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBenecointerruptionCreateBinding.inflate(layoutInflater)
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
            val serviceAdvisory = binding.SAdvisory.text.toString()
            val date = binding.date.text.toString()
            val timeStarted = binding.timeStarted.text.toString()
            val timeRestored = binding.timeRestored.text.toString()
            val cause = binding.cause.text.toString()
            val areasAffected = binding.areasAffected.text.toString()
            val others = binding.others.text.toString()

            // Create intent to start Beneco_publish activity and pass the input data
            val intent = Intent(this, Beneco_publish::class.java)
            intent.putExtra("serviceAdvisory", serviceAdvisory)
            intent.putExtra("date", date)
            intent.putExtra("timeStarted", timeStarted)
            intent.putExtra("timeRestored", timeRestored)
            intent.putExtra("cause", cause)
            intent.putExtra("areasAffected", areasAffected)
            intent.putExtra("others", others)

            // Start the Beneco_publish activity
            startActivity(intent)
        }
    }
}
