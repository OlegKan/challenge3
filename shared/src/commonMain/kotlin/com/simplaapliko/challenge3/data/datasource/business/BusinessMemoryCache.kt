package com.simplaapliko.challenge3.data.datasource.business

class BusinessMemoryCache : BusinessCache {
    override fun clear() {
    }

    override fun get(query: String, latitude: Double, longitude: Double): List<BusinessEntity> {
        return emptyList()
    }

    override fun put(
        query: String,
        latitude: Double,
        longitude: Double,
        entities: List<BusinessEntity>
    ) {
    }
}
