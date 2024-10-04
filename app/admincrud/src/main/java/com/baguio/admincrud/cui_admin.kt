package com.baguio.admincrud

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class cui_admin : AppCompatActivity() {

    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cui_admin)

        // Initialize Spinner
        spinner = findViewById(R.id.announcementlist)
        val listannouncement = listOf("Class Suspension", "Work Suspension", "Power Interruption", "Water Interruption", "Road Blocks", "Others")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listannouncement)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        val createbtn = findViewById<Button>(R.id.cud_create)
        createbtn.setOnClickListener {
            // Get the selected item from the spinner
            val selectedItem = spinner.selectedItem.toString()

            // Create the appropriate Intent based on the selected item CREATE
            val createIntent: Intent = when (selectedItem) {
                "Power Interruption" -> Intent(this, Benecointerruption_create::class.java) // Create for power interruption
                "Water Interruption" -> Intent(this, TOAWater_Interruption::class.java) // Create for water interruption
                "Class Suspension" -> Intent(this, ClassSuspension_create::class.java) // Replace with your actual class
                //"Work Suspension" -> Intent(this, WorkSuspensionActivity::class.java) // Replace with your actual class
                "Road Blocks" -> Intent(this, RoadBlocks_create::class.java) // Replace with your actual class
                "Others" -> Intent(this, Others_create::class.java) // Replace with your actual class
                else -> Intent(this, Create_Announcement::class.java) // Default case
            }
            startActivity(createIntent)
        }

        // update button logic
        val updateBtn = findViewById<Button>(R.id.cud_update)
        updateBtn.setOnClickListener {
            // Get the selected item from the spinner
            val selectedItem = spinner.selectedItem.toString()


            // Create the appropriate Intent based on the selected item for UPDATE
            val updateIntent: Intent = when (selectedItem) {
                "Power Interruption" -> Intent(this, Beneco_update::class.java) // Update for power interruption
                //"Water Interruption" -> Intent(this, TOAWater_Interruption::class.java) // Update for water interruption
                "Class Suspension" -> Intent(this, ClassSuspension_update::class.java) // Replace with your actual class
                //"Work Suspension" -> Intent(this, WorkSuspensionUpdateActivity::class.java) // Replace with your actual class
                //"Road Blocks" -> Intent(this, RoadBlocksUpdateActivity::class.java) // Replace with your actual class
                //"Others" -> Intent(this, OthersUpdateActivity::class.java) // Replace with your actual class
                else -> Intent(this, Create_Announcement::class.java) // Default case
            }
            startActivity(updateIntent)
        }

        // Delete button logic
        val deleteBtn = findViewById<Button>(R.id.cud_delete)
        deleteBtn.setOnClickListener {
            // Get the selected item from the spinner
            val selectedItem = spinner.selectedItem.toString()


            // Create the appropriate Intent based on the selected item for DELETE
            val deleteIntent: Intent = when (selectedItem) {
                "Power Interruption" -> Intent(
                    this,
                    Beneco_delete::class.java
                ) // Update for power interruption
                //"Water Interruption" -> Intent(this, TOAWater_Interruption::class.java) // Update for water interruption
                //"Class Suspension" -> Intent(this, ClassSuspensionUpdateActivity::class.java) // Replace with your actual class
                //"Work Suspension" -> Intent(this, WorkSuspensionUpdateActivity::class.java) // Replace with your actual class
                //"Road Blocks" -> Intent(this, RoadBlocksUpdateActivity::class.java) // Replace with your actual class
                //"Others" -> Intent(this, OthersUpdateActivity::class.java) // Replace with your actual class
                else -> Intent(this, Create_Announcement::class.java) // Default case
            }
            startActivity(deleteIntent)
        }
    }
}
