package com.example.dht11toapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ekn.gruzer.gaugelibrary.ArcGauge
import com.ekn.gruzer.gaugelibrary.Range

class Methane : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_methane)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val methgauge = findViewById<ArcGauge>(R.id.methgauge)

        val range = Range()
        range.color = Color.parseColor("#FE4975")
        range.from = 0.0
        range.to = 100.0

        methgauge.addRange(range)

        methgauge.minValue = 0.0
        methgauge.maxValue = 150.0

        val meth = intent.getStringExtra("Methane")

        if (meth != null) {
            methgauge.value = meth.toDouble()
        }
    }
}