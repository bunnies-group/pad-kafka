package com.example.producer.controller

import com.example.producer.dto.MessageDto
import com.example.producer.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/messages")
class MessagesController(private val messageService: MessageService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun send(@RequestBody dto: MessageDto) = messageService.send(dto)
}