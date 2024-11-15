package com.example.QuizApp.service.Impl;

import com.example.QuizApp.DTO.Convertors.QuestionConvertor;
import com.example.QuizApp.DTO.RequestDTO.QuizRequestDTO;
import com.example.QuizApp.DTO.ResponseDTO.QuizResponseDTO;
import com.example.QuizApp.models.Question;
import com.example.QuizApp.DTO.ResponseDTO.QuestionResponseDTO;
import com.example.QuizApp.models.Quiz;
import com.example.QuizApp.repository.QuestionRepository;
import com.example.QuizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.QuizApp.repository.QuizRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionRepository.findRandomQuestionsByCategory(numQ, category);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @Override
    public List<QuestionResponseDTO> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionResponseDTO> questionResponseDTOList = new ArrayList<>();
        for(Question q : questionsFromDB){
            questionResponseDTOList.add(QuestionConvertor.QuestionToQuestionResponseDTO(q));
        }
        return questionResponseDTOList;
    }

    @Override
    public QuizResponseDTO calculateResult(int id, List<QuizRequestDTO> quizRequestDTO) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questions = quiz.get().getQuestions();

        int rightAnsCount = 0;
        int listItr = 0;
        System.out.println("entering for loop");
        for(QuizRequestDTO request : quizRequestDTO){
            if(request.getResponse().equals(questions.get(listItr).getRightAnswer())){
                rightAnsCount++;
                System.out.println(rightAnsCount);
            }
            listItr++;
        }
        QuizResponseDTO quizResponseDTO = new QuizResponseDTO();
        quizResponseDTO.setRightAnsCount(rightAnsCount);
        return quizResponseDTO;
    }


    public void hello(){
        System.out.println("heelo");
    }
}
