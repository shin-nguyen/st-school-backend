package com.stschools.service.impl;

import java.util.List;

import com.stschools.dto.QuestionDTO;
import com.stschools.dto.QuizDTO;
import com.stschools.entity.Question;
import com.stschools.entity.Quiz;
import com.stschools.entity.User;
import com.stschools.exception.ApiRequestException;
import com.stschools.repository.AnswerRepository;
import com.stschools.repository.QuestionRepository;
import com.stschools.repository.QuizRepository;
import com.stschools.repository.UserRepository;
import com.stschools.service.QuizService;
import com.stschools.util.ModelMapperControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    //	private static final int QUIZZES_PER_PAGE = 4;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

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

    @Override
    public QuizDTO addQuestion(QuestionDTO questionNew, Long quizId) {
        Quiz quizOld = quizRepository.findById(quizId)
                .orElseThrow(() -> new ApiRequestException("Quiz is null!", HttpStatus.BAD_REQUEST));

        Question question = ModelMapperControl.map(questionNew, Question.class);
        quizOld.getQuestions().add(question);

        return ModelMapperControl.map(quizRepository.save(quizOld), QuizDTO.class);
    }

    @Override
    @Transactional
    public QuizDTO updateQuestion(QuestionDTO questionNew, Long quizId) {
        Quiz quizOld = quizRepository.findById(quizId)
                .orElseThrow(() -> new ApiRequestException("Quiz is null!", HttpStatus.BAD_REQUEST));

        Question question = ModelMapperControl.map(questionNew, Question.class);

        quizOld.getQuestions().forEach(q ->{
            if (q.getId().equals(question.getId())){
                q.setCorrect(question.getCorrect());
                q.setDescription(question.getDescription());
                q.setImage(question.getImage());
                //Remove Ansser in Question Old
                answerRepository.deleteAll(q.getOptions());
                q.setOptions(question.getOptions());
            }
        });

        return ModelMapperControl.map(quizRepository.save(quizOld), QuizDTO.class);
    }

    @Override
    public Long deleteQuestionInQuiz(Long quizId, Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new ApiRequestException("Question is null!", HttpStatus.BAD_REQUEST));
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ApiRequestException("Quiz is null!", HttpStatus.BAD_REQUEST));
        quiz.getQuestions().remove(question);
        quizRepository.save(quiz);

        questionRepository.delete(question);
        return questionId;
    }

}
