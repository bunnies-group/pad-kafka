package com.example.consumer.service

import com.example.consumer.dto.MessageDto
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.requests.FetchMetadata.log
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(private val objectMapper: ObjectMapper) : MessageService {

    override val messages: List<MessageDto> get() = data

    val data = mutableListOf<MessageDto>()

    @KafkaListener(id = "Message", topics = [KAFKA_TOPIC], containerFactory = "singleFactory")
    override fun consume(dto: MessageDto) {
        log.info("=> consumed {}", writeValueAsString(dto))
        data.add(dto)
    }

    private fun writeValueAsString(dto: Any?): String {
        return try {
            objectMapper.writeValueAsString(dto)
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
            throw RuntimeException("Writing value to JSON failed: $dto")
        }
    }

    private companion object {

        private const val KAFKA_TOPIC = "server.message"
    }
}