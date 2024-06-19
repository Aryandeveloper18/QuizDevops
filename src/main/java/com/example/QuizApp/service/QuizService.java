package com.example.QuizApp.service;

import com.example.QuizApp.DTO.RequestDTO.QuizRequestDTO;
import com.example.QuizApp.DTO.ResponseDTO.QuizResponseDTO;
import com.example.QuizApp.DTO.ResponseDTO.QuestionResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {

    public ResponseEntity<String> createQuiz(String category, int numQ, String title);

    public List<QuestionResponseDTO> getQuizQuestions(int id);

    public QuizResponseDTO calculateResult(int id, List<QuizRequestDTO> quizRequestDTO);
}
