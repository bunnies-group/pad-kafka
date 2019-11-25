package com.example.consumer.controller

import com.example.consumer.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/message")
class MessageController @Autowired constructor(private val messageService: MessageService) {

    @GetMapping
    fun get() = messageService.messages
}