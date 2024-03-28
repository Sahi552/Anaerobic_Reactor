package com.example.dht11toapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ekn.gruzer.gaugelibrary.ArcGauge
import com.ekn.gruzer.gaugelibrary.Range

class Soil_Moisture : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_soil_moisture)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val soilgauge = findViewById<ArcGauge>(R.id.soilgauge)

        val range = Range()
        range.color = Color.parseColor("#D091E9")
        range.from = 0.0
        range.to = 100.0

        soilgauge.addRange(range)

        soilgauge.minValue = 0.0
        soilgauge.maxValue = 150.0

        val soil = intent.getStringExtra("SOilMoisture")

        if (soil != null) {
            soilgauge.value = soil.toDouble()
        }
    }
}