package com.lifebetter.simplegymapp.domain

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import retrofit2.HttpException
import java.io.IOException


sealed interface Error {
    class Server(val code: Int) : Error
    object Connectivity : Error
    object NumberException : Error
    class Unknown(val message: String) : Error
}

fun Throwable.toError(): Error = when (this) {
    is IOException -> Error.Connectivity
    is HttpException -> Error.Server(code())
    is NumberFormatException -> Error.NumberException
    else -> Error.Unknown(message ?: "Error")
}

suspend fun <T> tryCall(action: suspend () -> T): Either<Error, T> = try {
    action().right()
} catch (e: Exception) {
    e.toError().left()
}