package com.simplaapliko.challenge3.data.datasource.business

import kotlinx.serialization.Serializable

@Serializable
data class BusinessEntity(
    val id: String? = null,
    val name: String? = null,
    val imageUrl: String? = null,
    val url: String? = null,
    val phone: String? = null,
    val distance: Double = 0.0,
    val type: String,
)
