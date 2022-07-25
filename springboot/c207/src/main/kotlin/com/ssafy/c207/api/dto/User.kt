package com.ssafy.c207.api.dto

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
data class User(
    @Id
    @Column(name = "id")
    var id: String = "",

    @Column(name = "pw")
    var pw: String = "",
)