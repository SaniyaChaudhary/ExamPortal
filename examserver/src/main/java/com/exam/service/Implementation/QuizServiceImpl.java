package com.exam.service.Implementation;

import com.exam.model.exam.Quiz;
import com.exam.repo.QuizRepo;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepo quizRepo;
    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepo.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepo.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizzies() {
        return new HashSet<>(this.quizRepo.findAll());
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return this.quizRepo.findById(quizId).get();
    }

    @Override
    public void deleteQuiz(Long quizId) {
        Quiz quiz=new Quiz();
        quiz.setQid(quizId);
        this.quizRepo.delete(quiz);


    }
}
