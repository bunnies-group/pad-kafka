package com.example.producer.dto

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class MessageDto(@NotBlank(message = "Author name should not be blank")
                      @Min(2, message = "Author name should be minimum 2 chars long")
                      val author: String,

                      @NotBlank
                      val text: String)
