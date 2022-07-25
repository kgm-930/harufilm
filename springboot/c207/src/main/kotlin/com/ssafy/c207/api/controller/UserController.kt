package com.ssafy.c207.api.controller

import com.ssafy.c207.api.dto.User
import com.ssafy.c207.api.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/user")
class UserController(
    val userRepository: UserRepository
) {
    @GetMapping()
    fun getMembers(): ResponseEntity<*> {
        val users = userRepository.findAll()

        return ResponseEntity.ok(users)
    }

    @PostMapping()
    fun setMember(@RequestBody user:User): ResponseEntity<*>? {
        val user = userRepository.save(user)

        return ResponseEntity.ok(user)
    }
}

