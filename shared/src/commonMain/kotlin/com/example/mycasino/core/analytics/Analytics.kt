package com.example.mycasino.core.analytics

interface Analytics {
    fun logEvent(name: String, params: Map<String, Any>? = null)
    fun setUserId(userId: String)
}

enum class AnalyticsEvent {
    LOGIN,
    REGISTER,
    LOGOUT
}