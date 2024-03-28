package com.example.dht11toapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ekn.gruzer.gaugelibrary.ArcGauge
import com.ekn.gruzer.gaugelibrary.Range

class Temperature : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_temperature)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tempgauge = findViewById<ArcGauge>(R.id.tempgauge)

        val range = Range()
        range.color = Color.parseColor("#EE1D1D")
        range.from = 0.0
        range.to = 100.0

        tempgauge.addRange(range)

        tempgauge.minValue = 0.0
        tempgauge.maxValue = 150.0

        val temp = intent.getStringExtra("Temperature")

        if (temp != null) {
            tempgauge.value = temp.toDouble()
        }
    }
}