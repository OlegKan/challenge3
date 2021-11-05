package com.simplaapliko.challenge3.data.datasource.business

interface BusinessCache {

    fun clear()

    fun get(
        query: String,
        latitude: Double,
        longitude: Double,
    ): List<BusinessEntity>

    fun put(
        query: String,
        latitude: Double,
        longitude: Double,
        entities: List<BusinessEntity>,
    )
}
