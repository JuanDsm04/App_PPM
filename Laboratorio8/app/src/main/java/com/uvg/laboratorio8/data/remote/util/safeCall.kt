package com.uvg.laboratorio8.data.remote.util

import com.uvg.laboratorio8.domain.remote.util.DataError
import com.uvg.laboratorio8.domain.remote.util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, DataError> {

    val response = try {
        execute()

    } catch(e: UnresolvedAddressException) {
        return Result.Error(DataError.NO_INTERNET)

    } catch(e: SerializationException) {
        return Result.Error(DataError.GENERIC_FAILURE)

    } catch(e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(DataError.GENERIC_FAILURE)

    }

    return responseToResult(response)
}