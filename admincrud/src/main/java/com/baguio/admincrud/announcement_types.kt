package com.baguio.admincrud

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class announcement_types : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_announcement_types)

        // Go to CUI_admin page for power interruption
        val btnPowerInterruption = findViewById<Button>(R.id.power_interruption)
        btnPowerInterruption.setOnClickListener {
            val powerIntent = Intent(this, cui_admin::class.java)
            powerIntent.putExtra("INTERRUPTION_TYPE", "power") // Pass interruption type
            startActivity(powerIntent)
        }

        // Go to CUI_admin page for water interruption
        val btnWaterInterruption = findViewById<Button>(R.id.water_interruption)
        btnWaterInterruption.setOnClickListener {
            val waterIntent = Intent(this, cui_admin::class.java)
            waterIntent.putExtra("INTERRUPTION_TYPE", "water") // Pass interruption type
            startActivity(waterIntent)
        }
    }
}
