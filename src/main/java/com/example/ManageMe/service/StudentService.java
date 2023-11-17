package com.example.ManageMe.service;

import com.example.ManageMe.data.dto.request.StudentRequest;
import com.example.ManageMe.data.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse addStudent(StudentRequest studentRequest);
    List<StudentResponse> viewAllStudents();
    StudentResponse findStudentById(StudentRequest studentRequest);
    StudentResponse updateStudentProfile(StudentRequest studentRequest);
    void deleteStudentProfile(StudentRequest studentRequest);
}
