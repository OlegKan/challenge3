package com.simplaapliko.challenge3.data.network.yelp

import com.simplaapliko.challenge3.utils.Log
import com.simplaapliko.challenge3.utils.isDebug
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter

internal expect val httpClientEngine: HttpClientEngineFactory<*>

private const val API_KEY = "2ROaa2Rh9qu3WVTCms8FoVE4mSfHQHC7QJua95-kKT-PqzIlLSrs4tmHVdtdFw_66-JNfRiJmbCByHTvFNy5dQq-tpfS4FrPpupIzKlgELR3br-r5trpeFhrCRgwWnYx"

internal fun AppHttpClient(): HttpClient = HttpClient(httpClientEngine) {
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
    install(HttpTimeout) {
        val timeout = 10_000L
        connectTimeoutMillis = timeout
        requestTimeoutMillis = timeout
        socketTimeoutMillis = timeout
    }
    install(Logging) {
        if (isDebug) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("HttpClient", message)
                }
            }
            level = LogLevel.ALL
        }
    }
    defaultRequest {
        header("Authorization", "Bearer $API_KEY")
    }
}

class YelpApi {
    companion object {
        const val BASE_URL = "https://api.yelp.com/v3/"
    }

    private val httpClient: HttpClient

    constructor() {
        httpClient = AppHttpClient()
    }

    constructor(httpClient: HttpClient) {
        this.httpClient = httpClient
    }

    suspend fun searchBusiness(
        query: String,
        categories: String?,
        latitude: Double,
        longitude: Double,
        openNow: Boolean,
    ): BusinessResponse {
        return httpClient.get("${BASE_URL}businesses/search") {
            parameter("term", query)
            parameter("latitude", latitude)
            parameter("longitude", longitude)
            parameter("open_now", openNow)
            parameter("sort_by", "distance")
            if (categories != null) {
                parameter("categories", categories)
            }
        }
    }
}
