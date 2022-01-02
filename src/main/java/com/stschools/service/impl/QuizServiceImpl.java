package com.stschools.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stschools.dto.QuizDTO;
import com.stschools.entity.Quiz;
import com.stschools.entity.User;
import com.stschools.exception.ApiRequestException;
import com.stschools.repository.QuizRepository;
import com.stschools.repository.UserRepository;
import com.stschools.service.QuizService;
import com.stschools.util.ModelMapperControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    //	private static final int QUIZZES_PER_PAGE = 4;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

//	@Override
//	public List<QuizDTO> getAllByUser(Long userId) {
//		return ModelMapperControl.mapAll(quizRepository.findByUser(userId), QuizDTO.class);
//	}

    @Override
    public Long delete(Long quizId){
        Quiz quizOld = quizRepository.findById(quizId)
                .orElseThrow(() -> new ApiRequestException("Quiz is null!", HttpStatus.BAD_REQUEST));

        quizOld.setIsDeleted(true);
        quizRepository.save(quizOld);
        return quizId;
    }

    @Override
    public QuizDTO add(QuizDTO quizDTO, Long idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new ApiRequestException("User is null!", HttpStatus.BAD_REQUEST));
        Quiz quiz = ModelMapperControl.map(quizDTO, Quiz.class);
        quiz.setCreateBy(user);
        quiz = quizRepository.save(quiz);
        return ModelMapperControl.map(quiz, QuizDTO.class);
    }

    @Override
    public QuizDTO getQuiz(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new ApiRequestException("Quiz is null!", HttpStatus.BAD_REQUEST));
        return ModelMapperControl.map(quiz, QuizDTO.class);
    }

//	@Override
//	public List<QuizDTO> getPopularQuiz() {
//		List<Quiz> listQuiz = quizRepository.findByStatusOrderByPlaysDesc(true,PageRequest.of(0, 11));
//		return ModelMapperControl.mapAll(listQuiz, QuizDTO.class);
//	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public Page<Quiz> listByPage(int pageNum, PagingAndSortingHelper helper) {
//		// TODO Auto-generated method stub
//		return (Page<Quiz>) helper.listEntities(pageNum, QUIZZES_PER_PAGE, quizRepository);
//	}

//	@Override
//	public List<QuizDTO> searchQuiz(String key) {
//
//		if (key.equals("") || key == null) {
//			return null;
//		}
//		Pageable page = PageRequest.of(0, 2);
//		List<Quiz> listQuiz = quizRepository.findByStatusAndTitleIgnoreCaseContaining(true,key);
//		return ModelMapperControl.mapAll(listQuiz, QuizDTO.class);
//	}

//	@Override
//	public List<QuizDTO> getRecommendationQuiz(Long idUser) {
//		List<Subject> subjects = userRepository.findById(idUser).get().getInterests();
//		List<Quiz> listQuiz = quizRepository.findDistinctByStatusAndSubjectsIn(true,subjects, PageRequest.of(0, 10)).getContent();
//		return ModelMapperControl.mapAll(listQuiz, QuizDTO.class);
//	}
//	public QuizDTO update(Long id, boolean enabled) {
//		try {
//			Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new ApiRequestException("Quiz is null!", HttpStatus.BAD_REQUEST););
//			quiz.setStatus(enabled);
//
//			return ModelMapperControl.map(quizRepository.save(quiz), QuizDTO.class);
//		} catch (Exception exception) {
//			System.out.println(exception.getMessage());
//			return null;
//		}
//	}


    @Override
    public List<QuizDTO> getAll() {
        return ModelMapperControl.mapAll(quizRepository.findAll(), QuizDTO.class);
    }


}
