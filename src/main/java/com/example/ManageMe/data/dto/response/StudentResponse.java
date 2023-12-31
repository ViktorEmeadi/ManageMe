package com.example.ManageMe.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private Long id;
}
