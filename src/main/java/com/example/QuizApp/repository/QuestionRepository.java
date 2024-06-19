package com.example.QuizApp.repository;

import com.example.QuizApp.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> { // mention class name and type of primary key

    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question Where question.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(int numQ, String category);

}
