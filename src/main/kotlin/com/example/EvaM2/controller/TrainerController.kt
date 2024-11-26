package com.example.EvaM2.controller


import com.example.EvaM2.entity.Trainer
import com.example.EvaM2.service.TrainerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/trainer")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class TrainerController {
    @Autowired
    lateinit var trainerService: TrainerService

    @GetMapping
    fun list():List<Trainer>{
        return trainerService.list()
    }

    @PostMapping
    fun save(@RequestBody trainer: Trainer):Trainer{
       return trainerService.save(trainer)
    }

    @PutMapping
    fun update(@RequestBody trainer: Trainer):ResponseEntity<Trainer>{
        return ResponseEntity(trainerService.updateName(trainer), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long):ResponseEntity<String>{
        trainerService.delete(id)
        return ResponseEntity("Capacitador Eliminado",HttpStatus.OK)
    }

}