package com.example.QuizApp.DTO.Convertors;

import com.example.QuizApp.DTO.RequestDTO.QuestionRequestDTO;
import com.example.QuizApp.DTO.ResponseDTO.QuestionResponseDTO;
import com.example.QuizApp.models.Question;

public class QuestionConvertor {

    public static Question QuestionRequestDTOtoQuestion(QuestionRequestDTO questionRequestDTO) {
        return Question.builder()
                .questionTitle(questionRequestDTO.getQuestionTitle())
                .option1(questionRequestDTO.getOption1())
                .option2(questionRequestDTO.getOption2())
                .option3(questionRequestDTO.getOption3())
                .option4(questionRequestDTO.getOption4())
                .rightAnswer(questionRequestDTO.getRightAnswer())
                .difficulty_level(questionRequestDTO.getDifficulty_level())
                .category(questionRequestDTO.getCategory())
                .build();
    }

    public static QuestionResponseDTO QuestionToQuestionResponseDTO(Question question){
        return QuestionResponseDTO.builder()
                .questionTitle(question.getQuestionTitle())
                .id(question.getId())
                .option1(question.getOption1())
                .option2(question.getOption2())
                .option3(question.getOption3())
                .option4(question.getOption4())
                .build();
    }

}
