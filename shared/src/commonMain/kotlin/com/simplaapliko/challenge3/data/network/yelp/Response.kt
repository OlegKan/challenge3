package com.simplaapliko.challenge3.data.network.yelp

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Business(
    val id: String? = null,
    val alias: String? = null,
    val name: String? = null,
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("is_closed") val isClosed: Boolean = false,
    val url: String? = null,
    @SerialName("review_count") val reviewCount: Int = 0,
    val categories: List<Category>? = null,
    val rating: Double = 0.0,
    val coordinates: Coordinates? = null,
    val transactions: List<String>? = null,
    val price: String? = null,
    val location: Location? = null,
    val phone: String? = null,
    @SerialName("display_phone") val displayPhone: String? = null,
    val distance: Double = 0.0,
)

@Serializable
class Category {
    val alias: String? = null
    val title: String? = null
}

@Serializable
class Coordinates {
    val latitude = 0.0
    val longitude = 0.0
}

@Serializable
class Location {
    val address1: String? = null
    val address2: String? = null
    val address3: String? = null
    val city: String? = null
    @SerialName("zip_code") val zipCode: String? = null
    val country: String? = null
    val state: String? = null
    @SerialName("display_address") val displayAddress: List<String>? = null
}

@Serializable
class Center {
    val longitude = 0.0
    val latitude = 0.0
}

@Serializable
class Region {
    val center: Center? = null
}

@Serializable
class BusinessResponse {
    val businesses: List<Business>? = null
    val total = 0
    val region: Region? = null
}
