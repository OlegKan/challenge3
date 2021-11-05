package com.simplaapliko.challenge3.utils

actual object Log {
    actual fun d(tag: String, message: String) {
        if (isDebug) {
            println("$tag : $message")
        }
    }
}
