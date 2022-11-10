@file:Suppress("unused")

package com.metao.samples.kafkasamplesconsumer.serdes

import com.metao.samples.kafkasamplesconsumer.event.ImperialTelemetryData
import org.springframework.kafka.support.serializer.JsonDeserializer

class TelemetryDataDeserializer : JsonDeserializer<ImperialTelemetryData>()