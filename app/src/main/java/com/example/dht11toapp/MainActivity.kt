package com.example.dht11toapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.schedule


private lateinit var database: DatabaseReference

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val methane = findViewById<CardView>(R.id.meth)
        val hydrogen = findViewById<CardView>(R.id.hydro)
        val temperature = findViewById<CardView>(R.id.temp)
        val humidity = findViewById<CardView>(R.id.humy)
        val soilMoisture = findViewById<CardView>(R.id.soil)

        val methtxt = findViewById<TextView>(R.id.methtxt)
        val hydrotxt = findViewById<TextView>(R.id.hydrotxt)
        val temptxt = findViewById<TextView>(R.id.temptxt)
        val humytxt = findViewById<TextView>(R.id.humytxt)
        val soil = findViewById<TextView>(R.id.soiltxt)

        var x = 1;
        while (x<10){
            Timer().schedule(10000){
                //calls this function after delay
                delayfunction(methtxt,hydrotxt,temptxt,humytxt,soil,methane,hydrogen,temperature,humidity,soilMoisture)
            }
            x=x+1
        }
    }

    private fun delayfunction(
        methtxt: TextView,
        hydrotxt: TextView,
        temptxt: TextView,
        humytxt: TextView,
        soil: TextView,
        methane: CardView,
        hydrogen: CardView,
        temperature: CardView,
        humidity: CardView,
        soilMoisture: CardView
    ) {
        database = FirebaseDatabase.getInstance().getReference("DHT")
        database.get().addOnSuccessListener {
            if (it.exists()){

                Toast.makeText(this,"Successfully retreived data",Toast.LENGTH_LONG).show()

                val Methane = it.child("methane").value.toString()
                val Hydrogen = it.child("hydrogen").value.toString()
                val Temperature = it.child("temperature").value.toString()
                val Humidity = it.child("humidity").value.toString()
                val Soil_Moisture = it.child("moisture").value.toString()

                methtxt.text = Methane
                hydrotxt.text = Hydrogen
                temptxt.text = Temperature
                humytxt.text = Humidity
                soil.text = Soil_Moisture

                methane.setOnClickListener {
                    val intent = Intent(this@MainActivity, com.example.dht11toapp.Methane:: class.java)
                    intent.putExtra("Methane",Methane)
                    startActivity(intent)
                }

                hydrogen.setOnClickListener {
                    val intent  =Intent(this@MainActivity, com.example.dht11toapp.Hydrogen:: class.java)
                    intent.putExtra("Hydrogen",Hydrogen)
                    startActivity(intent)
                }

                temperature.setOnClickListener {
                    val intent  =Intent(this@MainActivity, com.example.dht11toapp.Temperature:: class.java)
                    intent.putExtra("Temperature",Temperature)
                    startActivity(intent)
                }

                humidity.setOnClickListener {
                    val intent  =Intent(this@MainActivity, com.example.dht11toapp.Humidity:: class.java)
                    intent.putExtra("Humidity",Humidity)
                    startActivity(intent)
                }

                soilMoisture.setOnClickListener {
                    val intent  =Intent(this@MainActivity, com.example.dht11toapp.Soil_Moisture:: class.java)
                    intent.putExtra("SOilMoisture",Soil_Moisture)
                    startActivity(intent)
                }

            }else{
                Toast.makeText(this,"Data is not retreived",Toast.LENGTH_LONG).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Failed to access database",Toast.LENGTH_LONG).show()
        }

    }

}