package com.example.producer.service

import com.example.producer.dto.MessageDto
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(private val kafkaTemplate: KafkaTemplate<String, MessageDto>) : MessageService {

    private val logger = LoggerFactory.getLogger(MessageServiceImpl::class.java)

    override fun send(dto: MessageDto) {
        logger.info(String.format("#### -> Producing message -> {}", dto));
        kafkaTemplate.send(KAFKA_TOPIC, dto)
    }

    private companion object {

        const val KAFKA_TOPIC = "server.message"
    }
}