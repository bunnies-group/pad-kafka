package com.example.consumer.service

import com.example.consumer.dto.MessageDto

interface MessageService {

    fun retrieveMessages(): List<MessageDto>
}