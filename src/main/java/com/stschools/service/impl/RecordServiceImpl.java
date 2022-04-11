package com.stschools.service.impl;

import java.util.Date;
import java.util.List;

import com.stschools.dto.QuizDTO;
import com.stschools.dto.RecordDTO;
import com.stschools.entity.Quiz;
import com.stschools.entity.Record;
import com.stschools.entity.User;
import com.stschools.exception.ApiRequestException;
import com.stschools.payload.record.RecordResponse;
import com.stschools.repository.QuizRepository;
import com.stschools.repository.RecordRepository;
import com.stschools.repository.UserRepository;
import com.stschools.service.RecordService;
import com.stschools.util.ModelMapperControl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

	private final RecordRepository recordRepository;


	private final UserRepository userRepository;


//	@Override
//	public RecordDTO calculateScore(QuizDTO quizDTO, Long idUser) {
//		double score = answerService.calculateScore(quizDTO);
//		Record record = new Record();
//		RecordDTO recordDTO = null;
//		Quiz quiz = quizRepository.findById(quizDTO.getId()).get();
//		quiz.setPlays(quiz.getPlays() + 1);
//		quizRepository.save(quiz);
//		try {
//			User user = userRepository.findById(idUser)
//					.orElseThrow(() -> new ResourceNotFoundException("User", "id", idUser));
//			record.setUser(user);
//			record.setQuiz(quiz);
//			record.setStartTime(quizDTO.getStartTime());
//			record.setScore(score);
//			record.setSubmitTime(new Date());
//			record = recordRepository.save(record);
//			recordDTO = ObjectMapperUtils.map(record, RecordDTO.class);
//		} catch (Exception e) {
//			return null;
//		}
//		return recordDTO;
//	}

	@Override
	public List<RecordResponse> listAll(Long quizId) {
		return ModelMapperControl.mapAll(recordRepository.findAllByQuizId(quizId), RecordResponse.class);
	}

	@Override
	public RecordDTO getById(Long recordId) {
		Record record = recordRepository.findById(recordId)
				.orElseThrow(() -> new ApiRequestException("Quiz is null!", HttpStatus.BAD_REQUEST));
		return ModelMapperControl.map(record, RecordDTO.class);
	}

//	@Override
//	public List<RecordDTO> ranking(Long id) {
//		return ObjectMapperUtils.mapAll(recordRepository.ranking(id), RecordDTO.class);
//	}
//
//	@Override
//	public Long countNumberofTake(Long userId, Long quizId) {
//
//		return (long) recordRepository.findByUserAndQuiz(userId, quizId).size();
//	}
}
