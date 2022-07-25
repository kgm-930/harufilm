package com.ssafy.c207.api.repository

import com.ssafy.c207.api.dto.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository
interface UserRepository : JpaRepository<User, String>{}