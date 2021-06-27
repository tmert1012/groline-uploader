package com.isidorefarm.groline.uploader.entities

import java.time.LocalDateTime

data class Reading(
    val datetime: LocalDateTime,

    val phMin: Double,
    val phMax: Double,
    val phAvg: Double,

    val ecMin: Double,
    val ecMax: Double,
    val ecAvg: Double,

    val tempMin: Double,
    val tempMax: Double,
    val tempAvg: Double
) {



}



