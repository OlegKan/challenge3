package com.simplaapliko.challenge3.data.network.yelp

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.ios.Ios

internal actual val httpClientEngine: HttpClientEngineFactory<*> = Ios
