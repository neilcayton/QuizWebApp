package com.quizWebApp.quizapp.service;

import com.quizWebApp.quizapp.dao.QuestionDao;
import com.quizWebApp.quizapp.dao.QuizDao;
import com.quizWebApp.quizapp.model.Question;
import com.quizWebApp.quizapp.model.QuestionWrapper;
import com.quizWebApp.quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

//        questions.stream()
//                .forEach(question -> System.out.println("Question Title: " + question.getQuestionTitle()));

        String questionIds = questions.stream()
                .map(question -> "Question ID: " + question.getId())
                .collect(Collectors.joining(", "));

        System.out.println("Number of questions found: " + questions.size());


        return new ResponseEntity<>("Success: " + questionIds +" " +questions.size(), HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer quizId) {
        Optional<Quiz> quiz = quizDao.findById(quizId); //data might come or not so using optional
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<String> checkCorrectAnswer(Integer id) {
    
    }
}
