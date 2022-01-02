package com.stschools.mapper;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.CommentDTO;
import com.stschools.entity.Comment;
import com.stschools.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final ModelMapper modelMapper;
    private final CommentService commentService;

    private Comment convertToEntity(CommentDTO commentRequest) {
        return modelMapper.map(commentRequest, Comment.class);
    }

    CommentDTO convertToResponseDto(Comment comment) {
        return modelMapper.map(comment, CommentDTO.class);
    }

    List<CommentDTO> convertListToResponseDto(List<Comment> comments) {
        return comments.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public CommentDTO findCommentById(Long blogId) {
        return convertToResponseDto(commentService.findCommentById(blogId));
    }


    public List<CommentDTO> findAllComments(Long id) {
        return convertListToResponseDto(commentService.findAllComments(id));
    }

    public CommentDTO updateComment(Long id, CommentDTO request) throws ApiException {
        return convertToResponseDto(commentService.update(convertToEntity(request),id));
    }

    public CommentDTO addComment(Long id, CommentDTO request) throws ApiException {
        return convertToResponseDto(commentService.addComment(convertToEntity(request),id));
    }

//    public List<BlogDTO> filter(List<String> perfumers, List<String> genders, List<Integer> prices, boolean sortByPrice) {
//        return convertListToResponseDto(perfumeService.filter(perfumers, genders, prices, sortByPrice));
//    }

//    public List<PerfumeResponse> findByPerfumerOrderByPriceDesc(String perfumer) {
//        return convertListToResponseDto(perfumeService.findByPerfumerOrderByPriceDesc(perfumer));
//    }
//
//    public List<PerfumeResponse> findByPerfumeGenderOrderByPriceDesc(String perfumeGender) {
//        return convertListToResponseDto(perfumeService.findByPerfumeGenderOrderByPriceDesc(perfumeGender));
//    }
//
//    public PerfumeResponse saveBlog(PerfumeRequest perfumeRequest, MultipartFile file) {
//        return convertToResponseDto(perfumeService.savePerfume(convertToEntity(perfumeRequest), file));
//    }

//    public List<PerfumeResponse> deleteOrder(Long perfumeId) {
//        return convertListToResponseDto(perfumeService.deletePerfume(perfumeId));
//    }
}
