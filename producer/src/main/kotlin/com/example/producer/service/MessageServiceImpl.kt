package com.example.producer.service

import com.example.producer.dto.MessageDto
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.requests.FetchMetadata.log
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(private val kafkaMessageTemplate: KafkaTemplate<Long, MessageDto>,
                         private val objectMapper: ObjectMapper) : MessageService {

    override fun send(dto: MessageDto) {
        log.info("<= sending {}", writeValueAsString(dto))
        kafkaMessageTemplate.send(KAFKA_TOPIC, dto)
    }

    private fun writeValueAsString(dto: MessageDto): String = try {
        objectMapper.writeValueAsString(dto)
    } catch (e: JsonProcessingException) {
        e.printStackTrace()
        throw RuntimeException("Writing value to JSON failed: $dto")
    }

    private companion object {

        const val KAFKA_TOPIC = "server.message"
    }
}