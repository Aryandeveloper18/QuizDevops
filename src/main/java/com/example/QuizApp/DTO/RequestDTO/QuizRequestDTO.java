package com.example.QuizApp.DTO.RequestDTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
public class QuizRequestDTO {

    private Integer id;

    private String response;
}
