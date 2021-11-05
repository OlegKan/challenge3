package com.simplaapliko.challenge3.livedata

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class Wrapper<out T>(val status: Status, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T?): Wrapper<T> {
            return Wrapper(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Throwable): Wrapper<T> {
            return Wrapper(Status.ERROR, null, error)
        }

        fun <T> loading(): Wrapper<T> {
            return Wrapper(Status.LOADING, null, null)
        }
    }
}
