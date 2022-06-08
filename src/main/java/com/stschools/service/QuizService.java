package com.stschools.service;

import java.util.List;

import com.stschools.dto.QuestionDTO;
import com.stschools.dto.QuizDTO;
import com.stschools.dto.RecordDTO;
import com.stschools.entity.Record;

public interface QuizService {

    Long delete(Long id);

    QuizDTO add(QuizDTO quizDTO, Long idUser);

    QuizDTO getQuiz(Long id);

    QuizDTO updateQuiz(QuizDTO quizDTO);

    List<QuizDTO> getAll();

    QuizDTO addQuestion(QuestionDTO question, Long quizId);

    QuizDTO updateQuestion(QuestionDTO request, Long quizId);

    Long deleteQuestionInQuiz(Long quizId, Long questionId);

    RecordDTO submitQuiz(QuizDTO request, Long userId);
}
