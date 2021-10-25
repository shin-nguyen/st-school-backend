package com.stschools.mapper;


import com.stschools.dto.BlogDTO;
import com.stschools.entity.Blog;
import com.stschools.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BlogMapper {

    private final ModelMapper modelMapper;
    private final BlogService blogService;

    private Blog convertToEntity(BlogDTO blogRequest) {
        return modelMapper.map(blogRequest, Blog.class);
    }

    BlogDTO convertToResponseDto(Blog blog) {
        return modelMapper.map(blog, BlogDTO.class);
    }

    List<BlogDTO> convertListToResponseDto(List<Blog> blogs) {
        return blogs.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public BlogDTO findBlogById(Long blogId) {
        return convertToResponseDto(blogService.findBlogById(blogId));
    }

    public List<BlogDTO> findBlogsByIds(List<Long> blogsId) {
        return convertListToResponseDto(blogService.findBlogsByIds(blogsId));
    }

    public List<BlogDTO> findAllBlogs() {
        return convertListToResponseDto(blogService.findAllBlogs());
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
