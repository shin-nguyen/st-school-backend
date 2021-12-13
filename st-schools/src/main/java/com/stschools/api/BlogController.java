package com.stschools.api;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.exceptions.ApiException;
import com.cloudinary.utils.ObjectUtils;
import com.stschools.dto.BlogDTO;
import com.stschools.dto.UserDTO;
import com.stschools.entity.Blog;
import com.stschools.entity.User;
import com.stschools.exception.ApiRequestException;
import com.stschools.exception.InputFieldException;
import com.stschools.export_file.blogs.BlogCsvExporter;
import com.stschools.export_file.blogs.BlogExcelExporter;
import com.stschools.export_file.blogs.BlogPdfExporter;
import com.stschools.export_file.users.UserCsvExporter;
import com.stschools.export_file.users.UserExcelExporter;
import com.stschools.export_file.users.UserPdfExporter;
import com.stschools.mapper.BlogMapper;
import com.stschools.payload.blog.BlogRequest;
import com.stschools.payload.common.GraphQLRequest;
import com.stschools.security.CurrentUser;
import com.stschools.security.UserPrincipal;
import com.stschools.service.BlogService;
import com.stschools.service.graphql.GraphQLProvider;
import graphql.ExecutionResult;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blogs")
@Builder
public class BlogController {

    private final BlogMapper blogMapper;
    private final BlogService blogService;
    private final GraphQLProvider graphQLProvider;

    @GetMapping
    public ResponseEntity<List<?>> getAllBlogs() {
        return ResponseEntity.ok(blogMapper.findAllBlogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBlog(@PathVariable("id") Long blogId) {
        return ResponseEntity.ok(blogMapper.findBlogById(blogId));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> updateUserInfo(@CurrentUser UserPrincipal user,
                                            @Valid @RequestBody BlogDTO request,
                                            BindingResult bindingResult) throws ApiException {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(blogMapper.updateBlog(user.getId(), request));
        }
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity<Long> deleteBlog(@PathVariable(value = "blogId") Long blogId) throws ApiException {
        return ResponseEntity.ok(blogService.deleteBlog(blogId));
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<?> updateBlogStatus(@PathVariable("id") Long blogId,@PathVariable("status") String status) {
        return ResponseEntity.ok(blogService.updateBlogStatus(blogId,status));
    }

    @PostMapping("/graphql/blogs")
    public ResponseEntity<ExecutionResult> getAllBlogsByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @PostMapping("/graphql/blogs/me")
    public ResponseEntity<ExecutionResult> getAllBlogsMeByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }


    @PostMapping("/graphql/blog")
    public ResponseEntity<ExecutionResult> getBlogByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @PostMapping(value ="/add")
    public ResponseEntity<BlogDTO> registerPost(@ModelAttribute BlogRequest blog,
                                                    @CurrentUser UserPrincipal user,
                                                BindingResult bindingResult) throws IOException, ApiException {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(blogMapper.addBlog(blog, user.getId()));
        }
    }


    @GetMapping(path = "export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        List<Blog> blogs = blogService.findAllBlogs();
        BlogExcelExporter exporter = new BlogExcelExporter();
        exporter.export(blogs, response);
    }

    @GetMapping("/export/csv")
    public void exportToCSV( HttpServletResponse response) throws IOException {
        List<Blog> blogs = blogService.findAllBlogs();
        BlogCsvExporter exporter = new BlogCsvExporter();

        exporter.export(blogs, response);
    }

    @GetMapping("/export/pdf")
    public void exportToPDF( HttpServletResponse response) throws IOException {
        List<Blog> blogs = blogService.findAllBlogs();
        BlogPdfExporter exporter = new BlogPdfExporter();
        exporter.export(blogs, response);
    }
}
