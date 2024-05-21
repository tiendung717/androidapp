package com.solid.module.tracking

import android.os.Bundle
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

object Tracker {
    fun logEvent(event: String, params: Bundle? = null) {
        Firebase.analytics.logEvent(event, params)
    }
}