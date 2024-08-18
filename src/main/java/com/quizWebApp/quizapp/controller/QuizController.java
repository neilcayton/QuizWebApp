package com.quizWebApp.quizapp.controller;

import com.quizWebApp.quizapp.model.Question;
import com.quizWebApp.quizapp.model.QuestionWrapper;
import com.quizWebApp.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")


public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String>  createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }


    @PostMapping("submit/{id}")
    ResponseEntity<String> submitAnswer(@PathVariable Integer id){
        return quizService.checkCorrectAnswer(id);
    }

    @GetMapping("get/{quizId}")
    ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer quizId){
        //Wrapper For only the admin

        return quizService.getQuizQuestions(quizId);
    }
}


