package com.exam.controller;


import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody Quiz quiz){
        Quiz quiz1=  this.quizService.addQuiz(quiz);
        return ResponseEntity.ok(quiz1);

    }

    @GetMapping("/{quizId}")
    public Quiz get(@PathVariable ("quizId") Long quizId){
        return this.quizService.getQuiz(quizId);

    }

    @GetMapping("/")
    public ResponseEntity<?> quizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzies());
    }

    @PutMapping("/")
    public Quiz update(@RequestBody Quiz quiz){
        return  this.quizService.updateQuiz(quiz);
    }

    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable("quizId") Long quizId){
        this.quizService.deleteQuiz(quizId);
    }

}
