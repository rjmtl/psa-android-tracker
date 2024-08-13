package com.example.proemsportslib

import android.app.Application
import com.example.psa_trakcer.psa.Psa
import com.example.psa_trakcer.psa.configuration.NetworkConfiguration
import com.example.psa_trakcer.psa.configuration.SessionConfiguration
import com.example.psa_trakcer.psa.configuration.TrackerConfiguration
import com.example.psa_trakcer.psa.network.HttpMethod
import com.example.psa_trakcer.psa.util.TimeMeasure
import java.util.concurrent.TimeUnit

class MyApp: Application()  {

    override fun onCreate() {
        super.onCreate()


        // Configure network
        val networkConfig = NetworkConfiguration(
//            "https://internal.proemsportsanalytics.com",
            "http://internal.proemsportsanalytics.com",
            HttpMethod.POST
        )

        // Configure tracker
        val trackerConfig = TrackerConfiguration("rt-android")
            .base64encoding(false)
            .sessionContext(true)
            .platformContext(true)
            .lifecycleAutotracking(false)
            .screenViewAutotracking(true)
            .screenContext(true)
            .applicationContext(true)
            .exceptionAutotracking(true)
            .installAutotracking(true)
            .userAnonymisation(false)

        // Configure session
        val sessionConfig = SessionConfiguration(
            TimeMeasure(30, TimeUnit.SECONDS),
            TimeMeasure(30, TimeUnit.SECONDS)
        )

        //        // Create and configure the tracker
        // Create and configure the tracker
        val tracker = Psa.createTracker(
            applicationContext,
            "kotlin",
            networkConfig,
            trackerConfig,
            sessionConfig
        )

        // Set the tracker instance to be accessible globally
        TrackerManager.setTracker(tracker)

    }

}

