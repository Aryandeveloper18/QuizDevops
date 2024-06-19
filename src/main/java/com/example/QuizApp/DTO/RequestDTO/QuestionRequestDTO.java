package com.example.QuizApp.DTO.RequestDTO;

import com.example.QuizApp.models.Question;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionRequestDTO {
    String questionTitle;
    String option1;
    String option2;
    String option3;
    String option4;
    String rightAnswer;
    String difficulty_level;
    String category;
}
