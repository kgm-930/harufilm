package com.ssafy.c207.api.controller

import com.ssafy.c207.api.dto.Response
import com.ssafy.c207.api.dto.User
import com.ssafy.c207.api.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping()
class UserController(
    val userRepository: UserRepository
) {
    @PostMapping("/login")
    fun getMembers(@RequestBody data: User): Any {
        val response = Response()

        val user = userRepository.findById(data.id)

        if (user.isEmpty) {
            return response
        } else {
            if (user.get().pw != data.pw) {
                return response
            }
        }

        response.result = true
        response.message = "성공"

        return response
    }

    @PostMapping("/signup")
    fun setMember(@RequestBody data: User): Any {
        val response = Response()

        val check = userRepository.findById(data.id)

        if (check.isEmpty) {
            userRepository.save(data)
            response.result = true
            response.message = "성공"
        }

        return response
    }
}