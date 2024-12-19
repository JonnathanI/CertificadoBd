package com.example.EvaM2.entity

import jakarta.persistence.*

@Entity
@Table(name = "students")
class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null
    var name: String? = null
    var dni: String? = null


}
