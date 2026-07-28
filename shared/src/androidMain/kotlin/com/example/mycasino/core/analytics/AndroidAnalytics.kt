package com.example.mycasino.core.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class AndroidAnalytics(
    private val firebaseAnalytics: FirebaseAnalytics
) : Analytics {
    override fun logEvent(
        name: String,
        params: Map<String, Any>?
    ) {
        val bundle = Bundle().apply {
            params?.forEach { (key, value) ->
                when (value) {
                    is String -> putString(key, value)
                    is Int -> putInt(key, value)
                    is Long -> putLong(key, value)
                    is Double -> putDouble(key, value)
                    is Boolean -> putBoolean(key, value)
                    else -> putString(key, value.toString())
                }
            }
        }
        firebaseAnalytics.logEvent(name, bundle)
    }

    override fun setUserId(userId: String) {
        firebaseAnalytics.setUserId(userId)
    }
}