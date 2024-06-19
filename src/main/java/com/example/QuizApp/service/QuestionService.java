package com.example.QuizApp.service;

import com.example.QuizApp.DTO.RequestDTO.QuestionRequestDTO;
import com.example.QuizApp.models.Question;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {
    public ResponseEntity<List<Question>> getAllQuestions();

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category);

    public ResponseEntity<String> addQuestion(QuestionRequestDTO questionRequestDTO);
}
