package com.ssafy.c207.api.dto

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class Response {
    var result: Boolean = false

    var message: String = "실패"
}