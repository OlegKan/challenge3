package com.simplaapliko.challenge3.domain.business

data class BusinessModel(
    val id: String? = null,
    val name: String = "",
    val imageUrl: String? = null,
    val url: String? = null,
    val phone: String? = null,
    val distance: Double? = null,
    val formattedDistance: String = "",
    val type: String,
)
