package com.shipblu.corekt.domain.httpclient.models

data class DownloadModel(
    val downloaded: Long = 0,
    val total: Long = 0,
    val progress: Long = 0
)