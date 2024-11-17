package com.example.QuizApp.service.Impl;

import com.example.QuizApp.DTO.Convertors.QuestionConvertor;
import com.example.QuizApp.DTO.RequestDTO.QuizRequestDTO;
import com.example.QuizApp.DTO.ResponseDTO.QuizResponseDTO;
import com.example.QuizApp.models.Question;
import com.example.QuizApp.DTO.ResponseDTO.QuestionResponseDTO;
import com.example.QuizApp.models.Quiz;
import com.example.QuizApp.repository.QuestionRepository;
import com.example.QuizApp.service.QuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(QuizServiceImpl.class);
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        // Fetch random questions based on category
        List<Question> questions = questionRepository.findRandomQuestionsByCategory(numQ, category);

        if (questions == null || questions.isEmpty()) {
            return new ResponseEntity<>("No questions found for the given category", HttpStatus.BAD_REQUEST);
        }

        // Create and save the quiz with associated questions
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);  // This should now persist the quiz and associate the questions

        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
    }


    @Override
    public List<QuestionResponseDTO> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);

        if (quiz.isEmpty()) {
            log.error("Quiz not found for ID: " + id);
            throw new RuntimeException("Quiz not found");
        }

        List<Question> questionsFromDB = quiz.get().getQuestions();
        log.info("Fetched Questions from DB: " + questionsFromDB);

        List<QuestionResponseDTO> questionResponseDTOList = new ArrayList<>();
        for (Question q : questionsFromDB) {
            QuestionResponseDTO dto = QuestionConvertor.QuestionToQuestionResponseDTO(q);
            log.info("Converted Question: " + dto);
            questionResponseDTOList.add(dto);
        }
        return questionResponseDTOList;
    }



    @Override
    public QuizResponseDTO calculateResult(int id, List<QuizRequestDTO> quizRequestDTO) {
        Optional<Quiz> quiz = quizRepository.findById(id);

        if (quiz.isEmpty()) {
            throw new RuntimeException("Quiz not found for ID: " + id);
        }

        List<Question> questions = quiz.get().getQuestions();

        if (questions.size() != quizRequestDTO.size()) {
            throw new RuntimeException("Mismatch between quiz questions and submitted answers.");
        }

        int rightAnsCount = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            QuizRequestDTO request = quizRequestDTO.get(i);

            if (question.getId().equals(request.getId()) && question.getRightAnswer().equals(request.getResponse())) {
                rightAnsCount++;
            }
        }

        QuizResponseDTO quizResponseDTO = new QuizResponseDTO();
        quizResponseDTO.setRightAnsCount(rightAnsCount);

        return quizResponseDTO;
    }



    public void hello(){
        System.out.println("heelo");
    }
}
