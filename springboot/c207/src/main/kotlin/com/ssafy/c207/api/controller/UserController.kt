package com.ssafy.c207.api.controller

import com.ssafy.c207.api.dto.User
import com.ssafy.c207.api.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/user")
class UserController(
    val userRepository: UserRepository
) {
    @GetMapping()
    fun getMembers(@RequestBody data: User): Any {
        val user = userRepository.findById(data.id)

        if (user.isEmpty) {
            return false
        } else {
            if (user.get().pw != data.pw) {
                return false
            }
        }

        return true
    }

    @PostMapping()
    fun setMember(@RequestBody user: User): ResponseEntity<*>? {
        val user = userRepository.save(user)

        return ResponseEntity.ok(user)
    }
}

