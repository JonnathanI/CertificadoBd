package com.example.EvaM2.entity

import jakarta.persistence.*

@Entity
@Table(name = "course")
class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null
    var name: String? = null
    var rol: String? = null
    var link: String? = null
    var code: String? = null
    var aim: String? = null
    var contents: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "students_id")
    var students:Students? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    var trainer:Trainer? = null
}