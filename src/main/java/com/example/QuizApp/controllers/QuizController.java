package com.example.QuizApp.controllers;

import com.example.QuizApp.DTO.RequestDTO.QuizRequestDTO;
import com.example.QuizApp.DTO.ResponseDTO.QuizResponseDTO;
import com.example.QuizApp.DTO.ResponseDTO.QuestionResponseDTO;
import com.example.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionResponseDTO>> getQuizQuestions(@PathVariable int id){
        List<QuestionResponseDTO> questionResponseDTOList = quizService.getQuizQuestions(id);
        return new ResponseEntity<>(questionResponseDTOList, HttpStatus.OK);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<QuizResponseDTO> submitQuiz(@PathVariable int id, @RequestBody List<QuizRequestDTO> quizRequestDTO){
        QuizResponseDTO quizResponseDTO = quizService.calculateResult(id, quizRequestDTO);
        return new ResponseEntity<>(quizResponseDTO, HttpStatus.OK);
    }
}
