package com.example.EvaM2.entity

import jakarta.persistence.*

@Entity
@Table(name = "certificate")
class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null

    var hours: String? = null
    var date: String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    var course:Course? = null

}
