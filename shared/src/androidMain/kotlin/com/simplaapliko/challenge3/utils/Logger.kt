package com.simplaapliko.challenge3.utils

import android.util.Log

actual object Log {
    actual fun d(tag: String, message: String) {
        if (isDebug) {
            Log.d(tag, message)
        }
    }
}
