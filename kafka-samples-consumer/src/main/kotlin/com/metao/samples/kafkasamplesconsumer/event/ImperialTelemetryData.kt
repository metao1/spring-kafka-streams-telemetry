package com.metao.samples.kafkasamplesconsumer.event

import com.fasterxml.jackson.annotation.JsonProperty

data class ImperialTelemetryData(
    @JsonProperty("traveledDistanceFeet")
    val totalDistanceTraveledFeet: Double,
    @JsonProperty("maxSpeedMph")
    val maxSpeedMph: Double)