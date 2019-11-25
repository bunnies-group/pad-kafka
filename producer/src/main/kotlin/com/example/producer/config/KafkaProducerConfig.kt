package com.example.producer.config

import com.example.producer.dto.MessageDto
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.LongSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.converter.StringJsonMessageConverter
import org.springframework.kafka.support.serializer.JsonSerializer


@Configuration
class KafkaProducerConfig {

    @Value("\${kafka.server}")
    private lateinit var kafkaHost: String

    @Value("\${kafka.producer.id}")
    private lateinit var kafkaProducerId: String

    @Bean
    fun producerConfig(): MutableMap<String, Any?> =
            hashMapOf(
                    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaHost,
                    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to LongSerializer::class.java,
                    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java,
                    ProducerConfig.CLIENT_ID_CONFIG to kafkaProducerId
            )

    @Bean
    fun kafkaProducerFactory() = DefaultKafkaProducerFactory<Long, MessageDto>(producerConfig())

    @Bean
    fun kafkaTemplate() =
            KafkaTemplate<Long, MessageDto>(kafkaProducerFactory()).apply {
                setMessageConverter(StringJsonMessageConverter())
            }
}