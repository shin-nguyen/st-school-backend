package com.stschools.service.impl;

import com.stschools.dto.ReplyCommentDTO;
import com.stschools.entity.ReplyComment;
import com.stschools.repository.ReplyCommentRepository;
import com.stschools.service.ReplyCommentService;
import com.stschools.util.ModelMapperControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyCommentServiceImpl implements ReplyCommentService {
    @Autowired
    ReplyCommentRepository replyCommentRepository;

    @Override
    public List<ReplyCommentDTO> getReplyOfComment(Long id) {
        return ModelMapperControl.mapAll(replyCommentRepository.findByCommentId(id), ReplyCommentDTO.class);
    }

    @Override
    public ReplyCommentDTO addReply(ReplyCommentDTO replyCommentDTO) {
        replyCommentRepository.save(ModelMapperControl.map(replyCommentDTO, ReplyComment.class));
        return replyCommentDTO;
    }
}
