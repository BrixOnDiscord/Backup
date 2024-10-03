package com.baguio.admincrud

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.baguio.admincrud.databinding.ActivityClassSuspensionCreateBinding
import java.util.*

class ClassSuspension_create : AppCompatActivity() {

    private lateinit var binding: ActivityClassSuspensionCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityClassSuspensionCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Date Picker
        binding.dateClassSuspension.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.dateClassSuspension.text = selectedDate
            }, year, month, day).show()
        }

        // Set onClickListener for continue button
        binding.continuebtnClassSuspension.setOnClickListener {
            // Get the input data
            val serviceAdvisory = binding.SAdvisoryClassSuspension.text.toString()
            val date = binding.dateClassSuspension.text.toString()
            val cause = binding.causeClassSuspension.text.toString()
            val others = binding.othersClassSuspension.text.toString()

            // Get the checkbox states
            val isElementarySelected = binding.checkboxElementary.isChecked
            val isHighSchoolSelected = binding.checkboxHighschool.isChecked
            val isCollegeSelected = binding.checkboxCollege.isChecked

            // Create intent to start ClassSuspension_publish activity and pass the input data
            val intent = Intent(this, ClassSuspension_publish::class.java)
            intent.putExtra("serviceAdvisory", serviceAdvisory)
            intent.putExtra("dateClassSuspension", date)
            intent.putExtra("cause", cause)
            intent.putExtra("others", others)
            intent.putExtra("isElementarySelected", isElementarySelected)
            intent.putExtra("isHighSchoolSelected", isHighSchoolSelected)
            intent.putExtra("isCollegeSelected", isCollegeSelected)

            // Start the ClassSuspension_publish activity
            startActivity(intent)
        }
    }
}
