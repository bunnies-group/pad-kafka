package com.example.consumer.config

import com.example.consumer.dto.MessageDto
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.LongDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter
import org.springframework.kafka.support.converter.StringJsonMessageConverter


@Configuration
class KafkaConsumerConfig {

    @Value("\${kafka.server}")
    private lateinit var kafkaHost: String

    @Value("\${kafka.group.id}")
    private lateinit var kafkaGroupId: String

    @Bean
    fun batchFactory() =
            ConcurrentKafkaListenerContainerFactory<Long, MessageDto>().apply {
                consumerFactory = kafkaListenerContainerFactory()
                isBatchListener = true
                setMessageConverter(BatchMessagingMessageConverter(converter()))
            }

    @Bean
    fun singleFactory() =
            ConcurrentKafkaListenerContainerFactory<Long, MessageDto>().apply {
                consumerFactory = kafkaListenerContainerFactory()
                isBatchListener = false
                setMessageConverter(converter())
            }

    @Bean
    fun kafkaListenerContainerFactory(): ConsumerFactory<Long, MessageDto> = DefaultKafkaConsumerFactory(consumerConfig())

    @Bean
    fun consumerConfig(): MutableMap<String, Any?> = hashMapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaHost,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to LongDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.GROUP_ID_CONFIG to kafkaGroupId,
            ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to true
    )

    @Bean
    fun converter() = StringJsonMessageConverter()
}