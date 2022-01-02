package com.stschools.service;

import java.util.List;

import com.stschools.dto.QuestionDTO;
import com.stschools.dto.QuizDTO;
import com.stschools.entity.Quiz;
import org.springframework.data.domain.Page;

public interface QuizService {

//	List<QuizDTO> getAllByUser(Long userId);

//	List<QuizDTO> getPopularQuiz();

//	List<QuizDTO> getRecommendationQuiz(Long idUser);

//	List<QuizDTO> searchQuiz(String key);

	Long delete(Long id);

	QuizDTO add(QuizDTO quizDTO, Long idUser);

	QuizDTO getQuiz(Long id);

//	Page<Quiz> listByPage(int pageNum, PagingAndSortingHelper helper);

//	QuizDTO update(QuizDTO quizDTO, boolean enabled);

	List<QuizDTO> getAll();


}
