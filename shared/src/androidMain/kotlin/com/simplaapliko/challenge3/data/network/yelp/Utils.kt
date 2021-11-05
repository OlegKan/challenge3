package com.simplaapliko.challenge3.data.network.yelp

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

internal actual val httpClientEngine: HttpClientEngineFactory<*> = OkHttp
