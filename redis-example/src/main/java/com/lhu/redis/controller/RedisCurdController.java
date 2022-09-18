package com.lhu.redis.controller;

import com.lhu.redis.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lhu.redis.service.RedisCurdService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class RedisCurdController {
    @Autowired
    private RedisCurdService service;

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return service.saveStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudent() {
        return service.getAllStudent();
    }

    @GetMapping("/{id}")
    public Student findStudent(@PathVariable int id) {
        return service.findStudent(id);
    }
    @DeleteMapping("/{id}")
    public String removeStudent(@PathVariable int id)   {
        return service.removeStudent(id);
    }
}
