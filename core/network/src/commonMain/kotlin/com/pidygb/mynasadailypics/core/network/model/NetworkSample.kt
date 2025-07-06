package com.pidygb.mynasadailypics.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkSample(
    val date: String,
    val explanation: String?,
    @SerialName("hdurl")
    val hdUrl: String?,
    @SerialName("media_type")
    val mediaType: String?,
    @SerialName("service_version")
    val serviceVersion: String?,
    val title: String,
    val url: String
)