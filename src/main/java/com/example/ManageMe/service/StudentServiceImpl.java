package com.example.ManageMe.service;

import com.example.ManageMe.data.dto.request.StudentRequest;
import com.example.ManageMe.data.dto.response.StudentResponse;
import com.example.ManageMe.data.model.Student;
import com.example.ManageMe.data.repository.StudentRepository;
import com.example.ManageMe.exception.StudentException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;
    @Override
    public StudentResponse addStudent(StudentRequest studentRequest) {
        StudentResponse response = new StudentResponse();
        Optional<Student>foundStudent = studentRepository.findByEmail(studentRequest.getEmail());
        if (foundStudent.isPresent()){
            throw new StudentException("Email already registered");
        }
        Student student = Student.builder()
                .firstName(studentRequest.getFirstName())
                .lastName(studentRequest.getLastName())
                .email(studentRequest.getEmail())
                .age(studentRequest.getAge())
                .build();
        Student registeredStudent = studentRepository.save(student);
        BeanUtils.copyProperties(registeredStudent, response);
        return response;
    }

    @Override
    public List<StudentResponse> viewAllStudents() {
        return studentRepository.findAll().stream()
                .map(student -> StudentResponse.builder()
                        .firstName(student.getFirstName())
                        .lastName(student.getLastName())
                        .email(student.getEmail())
                        .age(student.getAge())
                        .id(student.getId())
                        .build()).toList();
    }

    @Override
    public StudentResponse findStudentById(StudentRequest studentRequest) {
        StudentResponse response = new StudentResponse();
        Optional<Student> foundStudent = getStudent(studentRequest);
        if (foundStudent.isEmpty()){
            throw new StudentException("Student with ID number "+studentRequest.getId()+" not found");
        }
        BeanUtils.copyProperties(foundStudent.get(), response);
        return response;
    }

    private Optional<Student> getStudent(StudentRequest studentRequest) {
        return studentRepository.findById(studentRequest.getId());
    }

    @Override
    public StudentResponse updateStudentProfile(StudentRequest studentRequest) {
        StudentResponse response = new StudentResponse();
        Optional<Student> foundStudent = getStudent(studentRequest);
        if (foundStudent.isEmpty()){
            throw new StudentException("Student with ID number "+studentRequest.getId()+" not found");
        }
        Student student = foundStudent.get();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setEmail(studentRequest.getEmail());
        student.setAge(studentRequest.getAge());
        studentRepository.save(student);
        BeanUtils.copyProperties(student, response);
        return response;
    }

    @Override
    public void deleteStudentProfile(StudentRequest studentRequest) {
        Optional<Student> foundStudent = getStudent(studentRequest);
        if (foundStudent.isEmpty()) {
            throw new StudentException("Student with ID number "+studentRequest.getId()+" not found");
        }
        studentRepository.deleteById(studentRequest.getId());
    }
}
