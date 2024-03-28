package com.example.dht11toapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ekn.gruzer.gaugelibrary.ArcGauge
import com.ekn.gruzer.gaugelibrary.Range

class Humidity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_humidity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val humygauge = findViewById<ArcGauge>(R.id.humygauge)

        val range = Range()
        range.color = Color.parseColor("#FE7A65")
        range.from = 0.0
        range.to = 100.0

        humygauge.addRange(range)

        humygauge.minValue = 0.0
        humygauge.maxValue = 150.0

        val humy = intent.getStringExtra("Humidity")

        if (humy != null) {
            humygauge.value = humy.toDouble()
        }
    }
}