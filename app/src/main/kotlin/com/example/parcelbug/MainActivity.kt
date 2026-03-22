package com.example.parcelbug

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnLaunch).setOnClickListener {
            val intent = Intent(this, DestinationActivity::class.java)
            val navData = NavigationData()
            intent.putExtra("navigation_data", navData)
            startActivity(intent)
        }
    }
}
