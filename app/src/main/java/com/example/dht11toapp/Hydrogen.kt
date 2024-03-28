package com.example.dht11toapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ekn.gruzer.gaugelibrary.ArcGauge
import com.ekn.gruzer.gaugelibrary.Range

class Hydrogen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hydrogen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val hydrogauge = findViewById<ArcGauge>(R.id.hydrogauge)

        val range = Range()
        range.color = Color.parseColor("#FE7A65")
        range.from = 0.0
        range.to = 100.0

        hydrogauge.addRange(range)

        hydrogauge.minValue = 0.0
        hydrogauge.maxValue = 150.0

        val hydro = intent.getStringExtra("Hydrogen")

        if (hydro != null) {
            hydrogauge.value = hydro.toDouble()
        }
    }
}