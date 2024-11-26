package com.example.EvaM2.repository

import com.example.EvaM2.entity.Trainer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrainerRepository:JpaRepository<Trainer, Long> {
    fun findById(id: Long?): Trainer?
}