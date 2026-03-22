package com.example.parcelbug

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DestinationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination)

        /**
         * CRASH happens here when system deserializes NavigationData from Bundle
         */
        getNavigationDataFromIntent()
    }

    private fun getNavigationDataFromIntent(): NavigationData? {
        return if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("navigation_data", NavigationData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("navigation_data") as? NavigationData
        }
    }
}
