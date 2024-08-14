package com.quizWebApp.quizapp.service;


import com.quizWebApp.quizapp.Question;
import com.quizWebApp.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllquestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK) ;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionDao.findAll(), HttpStatus.BAD_REQUEST) ;
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.BAD_REQUEST);
    }

    public  ResponseEntity<List<Question>> getQuestionsByDifficulty(String difficultylevel) {
        try {
            return new ResponseEntity<>(questionDao.findByDifficultylevel(difficultylevel), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionDao.findByDifficultylevel(difficultylevel), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<String> deleteQuestion(int id) {
        questionDao.deleteById(id);
        return new ResponseEntity<>("deleted", HttpStatus.NO_CONTENT);

    }
}
