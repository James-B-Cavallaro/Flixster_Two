package com.example.flixster_two

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Response(
    @SerialName("response")
    val response: List<Show>?
)


@Keep
@Serializable
data class Show(
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("name")
    val name: String?
)