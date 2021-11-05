package com.simplaapliko.challenge3.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun <T : Any> Flow<T?>.wrap(): Flow<Wrapper<T>> = transform { value ->
    if (value != null) {
        emit(Wrapper.success(value))
    } else {
        emit(Wrapper.error(IllegalArgumentException("Flow emitted null value")))
    }
}.catch { emit(Wrapper.error(it)) }

private const val DEFAULT_TIMEOUT = 5000L

fun <T> Flow<T>.asWrappedLiveData(
    context: CoroutineContext = EmptyCoroutineContext,
    timeoutInMs: Long = DEFAULT_TIMEOUT
): LiveData<Wrapper<T>> {
    val wrappedLiveData = MediatorLiveData<Wrapper<T>>()

    wrappedLiveData.addSource(LoadingLiveData<T>()) {
        wrappedLiveData.postValue(it)
    }

    val liveData = liveData(context, timeoutInMs) {
        wrap().collect {
            emit(it)
        }
    }

    wrappedLiveData.addSource(liveData) {
        wrappedLiveData.postValue(it)
    }

    return wrappedLiveData
}

private class LoadingLiveData<T> : LiveData<Wrapper<T>>(Wrapper.loading())
