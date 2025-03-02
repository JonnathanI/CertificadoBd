package com.example.EvaM2.entity

import jakarta.persistence.*

@Entity
@Table(name = "trainer")
class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null
    var name: String? = null
    var area: String? = null
}