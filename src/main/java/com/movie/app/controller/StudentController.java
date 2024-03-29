package com.movie.app.controller;


import com.movie.app.model.Student;
import com.movie.app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        URI uri = URI.create(
                ServletUriComponentsBuilder.fromCurrentRequestUri().path("/" + student.getId()).toUriString());
        return ResponseEntity.created(uri).body(student);
    }

    @GetMapping("/students/fullname")
    public ResponseEntity<List<Student>> findStudent(@RequestParam String name) {
        return ResponseEntity.ok(studentRepository.findByFullNameContaining(name));
    }
}

