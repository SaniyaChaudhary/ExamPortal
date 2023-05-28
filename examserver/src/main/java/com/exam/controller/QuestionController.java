package com.exam.controller;


import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody Question question){
        Question ques=  this.questionService.addQuestion(question);
        return ResponseEntity.ok(ques);

    }


    @PutMapping("/")
    public Question update(@RequestBody Question question){
        return  this.questionService.updateQuestion(question);
    }

    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionsofQuiz(@PathVariable("qid") Long qid  ){

       Quiz quiz=new Quiz();
       quiz.setQid(qid);
       Set<Question> questionsofQuiz =this.questionService.getQuestionsOfQuiz(quiz);
       return  ResponseEntity.ok(questionsofQuiz);

    }

    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId") Long quesId)
    {
        return this.questionService.getQuestion(quesId);
    }

    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") Long quesId){
        this.questionService.deleteQuestion(quesId);
    }
}
