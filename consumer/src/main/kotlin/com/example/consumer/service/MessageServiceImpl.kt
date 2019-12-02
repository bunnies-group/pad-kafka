package com.example.consumer.service

import com.example.consumer.dto.MessageDto
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl : MessageService {

    private val logger = LoggerFactory.getLogger(MessageServiceImpl::class.java)

    private val messages = mutableListOf<MessageDto>()

    @KafkaListener(topics = [KAFKA_TOPIC], groupId = "group_id")
    fun consume(dto: MessageDto) {
        logger.info(String.format("#### -> Consumed message -> {}", dto));
        messages.add(dto)
    }

    override fun retrieveMessages() = messages

    private companion object {

        private const val KAFKA_TOPIC = "server.message"
    }
}