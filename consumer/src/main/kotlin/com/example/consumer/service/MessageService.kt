package com.example.consumer.service

import com.example.consumer.dto.MessageDto

interface MessageService {

    fun consume(dto: MessageDto)

    val messages: List<MessageDto>
}