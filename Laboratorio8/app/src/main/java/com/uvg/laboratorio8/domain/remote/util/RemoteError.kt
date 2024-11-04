package com.uvg.laboratorio8.domain.remote.util

interface Error

enum class DataError: Error {
    NO_INTERNET,
    GENERIC_FAILURE
}