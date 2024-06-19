package com.example.QuizApp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String questionTitle;
    String option1;
    String option2;
    String option3;
    String option4;
    String rightAnswer;
    String difficulty_level;
    String category;

}
