package com.exam.repo;

import com.exam.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Long> {
}
