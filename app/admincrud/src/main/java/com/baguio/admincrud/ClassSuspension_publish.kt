package com.baguio.admincrud

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class ClassSuspension_publish : AppCompatActivity() {

    private lateinit var serviceAdvisoryTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var causeTextView: TextView
    private lateinit var othersTextView: TextView
    private lateinit var classLevelsTextView: TextView
    private lateinit var publishButton: Button
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_suspension_publish)

        // Linking XML views to Kotlin variables
        serviceAdvisoryTextView = findViewById(R.id.S_advisory)
        dateTextView = findViewById(R.id.date)
        causeTextView = findViewById(R.id.cause)
        othersTextView = findViewById(R.id.others)
        classLevelsTextView = findViewById(R.id.class_levels)
        publishButton = findViewById(R.id.publishbtn)

        // Firebase Database Reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Class Suspension_Announcements")

        // Get data passed from the previous activity
        val serviceAdvisory = intent.getStringExtra("serviceAdvisory")
        val dateClassSuspension = intent.getStringExtra("dateClassSuspension")
        val cause = intent.getStringExtra("cause")
        val others = intent.getStringExtra("others")
        val isElementarySelected = intent.getBooleanExtra("isElementarySelected", false)
        val isHighSchoolSelected = intent.getBooleanExtra("isHighSchoolSelected", false)
        val isCollegeSelected = intent.getBooleanExtra("isCollegeSelected", false)

        // Set text to TextViews
        serviceAdvisoryTextView.text = serviceAdvisory ?: "No Advisory"
        dateTextView.text = dateClassSuspension ?: "No Date"
        causeTextView.text = cause ?: "No Cause Provided"
        othersTextView.text = others ?: "No Additional Context"

        // Create a list for selected class levels
        val selectedClasses = mutableListOf<String>()
        if (isElementarySelected) selectedClasses.add("Elementary")
        if (isHighSchoolSelected) selectedClasses.add("High School")
        if (isCollegeSelected) selectedClasses.add("College")

        classLevelsTextView.text = if (selectedClasses.isNotEmpty()) {
            "Class levels: ${selectedClasses.joinToString(", ")}"
        } else {
            "No Class Levels Selected"
        }

        // Set the onClickListener for the publish button
        publishButton.setOnClickListener {
            publishData(
                serviceAdvisory,
                dateClassSuspension,
                cause,
                others,
                isElementarySelected,
                isHighSchoolSelected,
                isCollegeSelected
            )
        }
    }

    private fun publishData(
        serviceAdvisory: String?,
        dateClassSuspension: String?,
        cause: String?,
        others: String?,
        isElementarySelected: Boolean,
        isHighSchoolSelected: Boolean,
        isCollegeSelected: Boolean
    ) {
        // Create a unique ID for the new entry
        val randomId = UUID.randomUUID().toString()

// Create the data object for Firebase
        val classSuspension_data = ClassSuspension_data(
            id = randomId,  // Assuming you generate a random ID elsewhere
            serviceAdvisory = serviceAdvisory ?: "Default Advisory",  // Provide a default if null
            dateClassSuspension = dateClassSuspension ?: "No Date",   // Provide a default if null
            cause = cause ?: "No Cause",                               // Provide a default if null
            others = others ?: "N/A",                                  // Provide a default if null
            isElementarySelected = isElementarySelected,
            isHighSchoolSelected = isHighSchoolSelected,
            isCollegeSelected = isCollegeSelected
        )


        // Push data to Firebase Realtime Database
        databaseReference.child(randomId).setValue(ClassSuspension_data)
            .addOnSuccessListener {
                Toast.makeText(this, "Class Suspension Published Successfully", Toast.LENGTH_SHORT).show()
                // Start Create_Announcement activity
                val intent = Intent(this, Create_Announcement::class.java)
                startActivity(intent)
                finish() // Close this activity
            }
            .addOnFailureListener { error ->
                // Error message if publish fails
                Toast.makeText(this, "Failed to Publish: ${error.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
