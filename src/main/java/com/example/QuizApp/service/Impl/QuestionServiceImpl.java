package com.example.QuizApp.service.Impl;

import com.example.QuizApp.DTO.Convertors.QuestionConvertor;
import com.example.QuizApp.DTO.RequestDTO.QuestionRequestDTO;
import com.example.QuizApp.models.Question;
import com.example.QuizApp.repository.QuestionRepository;
import com.example.QuizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    @Override
    public ResponseEntity<List<Question>> getAllQuestions() { // returning the ResponseEntity should be done in service
        try{
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK); // ResponseEntity is used to get the HttpStatus along with the data
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> addQuestion(QuestionRequestDTO questionRequestDTO) {
        Question question = QuestionConvertor.QuestionRequestDTOtoQuestion(questionRequestDTO);
        try{
            questionRepository.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
