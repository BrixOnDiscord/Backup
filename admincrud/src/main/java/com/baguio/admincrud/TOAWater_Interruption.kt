package com.baguio.admincrud

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.baguio.admincrud.databinding.ActivityToawaterInterruptionBinding
import java.util.*

class TOAWater_Interruption : AppCompatActivity() {

    private lateinit var binding: ActivityToawaterInterruptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityToawaterInterruptionBinding.inflate(layoutInflater)
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

        binding.continuebtn.setOnClickListener {
            // Get the input data
            val dateWhen = binding.date.text.toString()
            val areasAffected = binding.areasAffected.text.toString()
            val reason = binding.reason.text.toString()
            val wateradvisory = binding.SWAdvisory.text.toString()

            // Create intent to start Badiwa_publish activity and pass the input data
            val intent = Intent(this, Badiwa_publish::class.java)
            intent.putExtra("advisory", wateradvisory )
            intent.putExtra("date", dateWhen)
            intent.putExtra("areasAffected", areasAffected)
            intent.putExtra("others", reason) // Assuming "reason" is what you want to pass as "others"


            startActivity(intent)
        }
    }
}
