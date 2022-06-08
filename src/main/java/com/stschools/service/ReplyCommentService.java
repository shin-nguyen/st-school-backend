package com.stschools.service;

import com.stschools.dto.ReplyCommentDTO;

import java.util.List;

public interface ReplyCommentService {
    List<ReplyCommentDTO> getReplyOfComment(Long id);

    ReplyCommentDTO addReply(ReplyCommentDTO replyCommentDTO);
}
