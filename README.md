# QuizApp-Learn-Java-SpringBoot

## Endpoints

### getAllQuestions

http://localhost:8080/question/allQuestions

### getQuestionsByCategory

http://localhost:8080/question/category/python

### addQuestion

http://localhost:8080/question/add

Example:
````
{
  "questionTitle": "Which of the following is not a primitive data type in Java?",
  "option1": "int",
  "option2": "boolean",
  "option3": "double",
  "option4": "string",
  "rightAnswer": "string",
  "difficulty_level": "medium",
  "category": "java"
}
````

### createQuiz

http://localhost:8080/quiz/create?category=java&numQ=5&title=JavaQuiz5

### getQuizQuestions

http://localhost:8080/quiz/get/2

### submitQuiz&GetScore

http://localhost:8080/quiz/submit/2