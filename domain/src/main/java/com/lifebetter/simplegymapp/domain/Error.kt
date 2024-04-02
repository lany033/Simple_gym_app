package com.lifebetter.simplegymapp.domain


sealed interface Error {
    class Server(val code: Int) : Error
    object Connectivity : Error
    object NumberException : Error
    class Unknown(val message: String) : Error
}

