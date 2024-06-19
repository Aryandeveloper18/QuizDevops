package com.example.QuizApp.controllers;

import com.example.QuizApp.DTO.RequestDTO.QuestionRequestDTO;
import com.example.QuizApp.models.Question;
import com.example.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired // use this when u need an object
    QuestionService questionService;
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){ // in ResponseEntity, for function return type, we mention type of data List<Question> in ResponseEntity
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("category") String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody QuestionRequestDTO questionRequestDTO){
        return questionService.addQuestion(questionRequestDTO);
    }
}
