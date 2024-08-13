package com.example.proemsportslib

import com.example.psa_trakcer.psa.controller.TrackerController

object TrackerManager {

    private var tracker: TrackerController? = null

    fun setTracker(tracker: TrackerController) {
        this.tracker = tracker
    }

    fun getTracker(): TrackerController? {
        return tracker
    }

}