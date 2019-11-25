package com.example.producer.service

import com.example.producer.dto.MessageDto

interface MessageService {

    fun send(dto: MessageDto)
}