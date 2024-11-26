package com.example.EvaM2.service


import com.example.EvaM2.entity.Trainer
import com.example.EvaM2.repository.TrainerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TrainerService {
    @Autowired
    lateinit var trainerRepository: TrainerRepository

    fun list(): List<Trainer> {
        return trainerRepository.findAll()
    }

    fun save(trainer: Trainer): Trainer {
        return trainerRepository.save(trainer)
    }

    fun update(trainer: Trainer): Trainer {
        try {
            trainerRepository.findById(trainer.id)?: throw Exception("Capacitador no Encontrado")
            return trainerRepository.save(trainer)
        }catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun updateName(trainer: Trainer): Trainer {
        try {

            var response = trainerRepository.findById(trainer.id) ?: throw Exception("Ya existe este ID")
            response.apply {
                name=trainer.name

            }
            return trainerRepository.save(response)
        }
        catch(ex:Exception){
            throw  ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun  delete(id: Long) {
        try {

            var response = trainerRepository.findById(id).orElseThrow{throw ResponseStatusException(HttpStatus.NOT_FOUND, "Capacitador no Existe con el Id:  $id")}
            trainerRepository.delete(response)
        }
        catch(ex:Exception){
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar al Capacitador", ex)
        }
    }

    fun validarDni(dni:String?): Boolean? {
        if (dni.isNullOrBlank()) {
            return false
        }

        val nuiRegex = Regex("^[0-9]{10}$")
        if (!nuiRegex.matches(dni)) {
            return false
        }


        return true
    }
}