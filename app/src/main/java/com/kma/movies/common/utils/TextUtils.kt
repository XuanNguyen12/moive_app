package com.kma.movies.common.utils

fun String?.orNa(): String {
    return if (isNullOrEmpty()) "N/A" else this
}

fun String?.or(b: String): String {
    return if (isNullOrEmpty()) b else this
}