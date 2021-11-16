package com.stschools.mapper;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.BlogDTO;
import com.stschools.dto.CommentBlogDTO;
import com.stschools.entity.Blog;
import com.stschools.entity.CommentBlog;
import com.stschools.service.BlogService;
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

    private CommentBlog convertToEntity(CommentBlogDTO commentRequest) {
        return modelMapper.map(commentRequest, CommentBlog.class);
    }

    CommentBlogDTO convertToResponseDto(CommentBlog commentBlog) {
        return modelMapper.map(commentBlog, CommentBlogDTO.class);
    }

    List<CommentBlogDTO> convertListToResponseDto(List<CommentBlog> comments) {
        return comments.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public CommentBlogDTO findCommentById(Long blogId) {
        return convertToResponseDto(commentService.findCommentById(blogId));
    }


    public List<CommentBlogDTO> findAllComments(Long id) {
        return convertListToResponseDto(commentService.findAllComments(id));
    }

    public CommentBlogDTO updateComment(Long id, CommentBlogDTO request) throws ApiException {
        return convertToResponseDto(commentService.update(convertToEntity(request),id));
    }

    public CommentBlogDTO addComment(Long id, CommentBlogDTO request) throws ApiException {
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
