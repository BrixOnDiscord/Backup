package com.baguio.admincrud

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class cui_admin : AppCompatActivity() {
    private var interruptionType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cui_admin)

        // Retrieve the interruption type from the intent
        interruptionType = intent.getStringExtra("INTERRUPTION_TYPE")

        val createbtn = findViewById<Button>(R.id.cud_create)
        createbtn.setOnClickListener {
            val createIntent: Intent = when (interruptionType) {
                "power" -> Intent(this, Benecointerruption_create::class.java) // Create for power interruption
                "water" -> Intent(this, TOAWater_Interruption::class.java) // Create for water interruption
                else -> Intent(this, Benecointerruption_create::class.java) // Default case
            }
            startActivity(createIntent)
        }

        // Add logic for delete and update buttons similarly
    }
}
