package com.baguio.admincrud

data class BenecoData(
    val serviceAdvisory: String,
    val date: String,
    val timeStarted: String,
    val timeRestored: String,
    val cause: String,
    val areasAffected: String,
    val others: String
)
