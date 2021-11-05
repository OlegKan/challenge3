package com.simplaapliko.challenge3.domain.business

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip

class BusinessSearchInteractor(private val businessRepository: BusinessRepository) {

    fun searchOpenBusinesses(
        query: String,
        latitude: Double,
        longitude: Double,
    ): Flow<List<BusinessModel>> {
        val terms = query.split(",")

        return businessRepository.getBusinesses(
            query = terms.first().trim(),
            latitude = latitude,
            longitude = longitude,
            openNow = true,
        ).zip(
            businessRepository.getBusinesses(
                query = terms.last().trim(),
                latitude = latitude,
                longitude = longitude,
                openNow = true,
            )
        ) { a, b -> a + b }
            .map { list -> list.sortedBy { it.distance } }

        // return query.split(",").asFlow()
        //     .map { term ->
        //         businessRepository.business(
        //             query = term,
        //             latitude = latitude,
        //             longitude = longitude
        //         )
        //     }
        //     .flattenConcat()
    }
}
