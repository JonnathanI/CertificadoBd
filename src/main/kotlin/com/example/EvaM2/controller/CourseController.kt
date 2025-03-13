package com.example.EvaM2.controller

import com.example.EvaM2.entity.Course
import com.example.EvaM2.service.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile // Import this
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/course")
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class CourseController {
    @Autowired
    lateinit var courseService: CourseService

    @GetMapping
    fun list(): List<Course> {
        return courseService.list()
    }

    @GetMapping("/{id}")
    fun getCourseDetails(@PathVariable id: Long): ResponseEntity<Course> {
        val course = courseService.getCourseById(id)
        return if (course != null) {
            ResponseEntity(course, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun save(@RequestBody course: Course): Course {
        return courseService.save(course)
    }

    @PutMapping
    fun update(@RequestBody course: Course): ResponseEntity<Course> {
        return ResponseEntity(courseService.updateName(course), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<String> {
        courseService.delete(id)
        return ResponseEntity("Curso Eliminado", HttpStatus.OK)
    }

    @GetMapping("/details/{courseId}")
    fun getCourseDetailsWithCertificateDate(@PathVariable courseId: Long?): CourseService.CourseWithCertificateDate? {
        return courseService.getCourseDetailsWithCertificateDate(courseId)
    }
}