package com.stschools.service;

import com.stschools.dto.RecordDTO;
import com.stschools.payload.record.RecordResponse;

import java.util.List;

public interface RecordService {

    List<RecordResponse> listAll(Long quizId);

    RecordDTO getById(Long recordId);
}
