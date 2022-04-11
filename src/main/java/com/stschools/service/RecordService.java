package com.stschools.service;

import com.stschools.dto.RecordDTO;
import com.stschools.payload.record.RecordResponse;

import java.util.List;

public interface RecordService {
	
//	RecordDTO calculateScore(QuizDTO quizDTO, Long idUser );

	List<RecordResponse> listAll(Long quizId);

    RecordDTO getById(Long recordId);

//	List<RecordDTO> ranking(Long id);
//
//	Long countNumberofTake(Long userId, Long quizId);
}
