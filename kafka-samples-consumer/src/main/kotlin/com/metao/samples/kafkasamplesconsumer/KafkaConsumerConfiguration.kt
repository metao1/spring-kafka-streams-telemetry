package com.metao.samples.kafkasamplesconsumer

import com.metao.samples.kafkasamplesconsumer.event.ImperialTelemetryData
import com.metao.samples.kafkasamplesconsumer.event.MetricTelemetryData
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import java.util.function.Consumer

@Configuration
class KafkaConsumerConfiguration {

    private val logger = KotlinLogging.logger {}

    @Bean
    fun processNasaTelemetryData(): Consumer<Message<ImperialTelemetryData>> =
        Consumer { telemetryMessage ->
            try {
                val imperialTelemetryData = telemetryMessage.payload
                logger.info {
                    "\nReceived telemetry data for NASA probe '${telemetryMessage.headers["kafka_receivedMessageKey"]}':" +
                            "\n\tMax Speed: ${imperialTelemetryData.maxSpeedMph} mph" +
                            "\n\tTotal distance travelled: ${imperialTelemetryData.totalDistanceTraveledFeet} feet"
                }
            } catch (e: Exception) {
                logger.error {
                    "Error processing telemetry data for NASA probe '${telemetryMessage.headers["kafka_receivedMessageKey"]}': " +
                            "'${telemetryMessage.payload}': '${e.message}'"
                }
            }
        }

    @Bean
    fun processEsaTelemetryData(): Consumer<Message<ImperialTelemetryData>> =
        Consumer { telemetryMessage ->
            try {
                val metricTelemetryData = MetricTelemetryData(telemetryMessage.payload)
                logger.info {
                    "\nReceived telemetry data for ESA probe '${telemetryMessage.headers["kafka_receivedMessageKey"]}':" +
                            "\n\tMax Speed: ${metricTelemetryData.maxSpeedKph} kph" +
                            "\n\tTotal distance travelled: ${metricTelemetryData.totalDistanceMetres} meters"
                }
            } catch (e: Exception) {
                logger.error {
                    "Error processing telemetry data for ESA probe '${telemetryMessage.headers["kafka_receivedMessageKey"]}': " +
                            "'${telemetryMessage.payload}': '${e.message}'"
                }
            }
        }
}
