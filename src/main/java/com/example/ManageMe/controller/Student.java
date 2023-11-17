package com.example.ManageMe.controller;

import com.example.ManageMe.data.dto.request.StudentRequest;
import com.example.ManageMe.data.dto.response.StudentResponse;
import com.example.ManageMe.service.StudentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class Student {
    private final StudentService studentService;
    @PostMapping("/students")
    public ResponseEntity<?> register(@RequestBody StudentRequest studentRequest){
        StudentResponse response = studentService.addStudent(studentRequest);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/students")
    public ResponseEntity<?> viewAllStudents(){
        List<StudentResponse> students = studentService.viewAllStudents();
        return ResponseEntity.ok(students);
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<?> findStudentsById(@RequestBody StudentRequest studentRequest){
        StudentResponse response = studentService.findStudentById(studentRequest);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudentProfile(@RequestBody StudentRequest studentRequest){
        StudentResponse response = studentService.updateStudentProfile(studentRequest);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudentProfile(@RequestBody StudentRequest studentRequest){
        studentService.deleteStudentProfile(studentRequest);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
