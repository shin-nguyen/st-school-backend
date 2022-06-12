package com.stschools.api;

import com.stschools.dto.BlogDTO;
import com.stschools.entity.Blog;
import com.stschools.exception.InputFieldException;
import com.stschools.export_file.blogs.BlogCsvExporter;
import com.stschools.export_file.blogs.BlogExcelExporter;
import com.stschools.export_file.blogs.BlogPdfExporter;
import com.stschools.payload.blog.BlogRequest;
import com.stschools.repository.BlogRepository;
import com.stschools.security.CurrentUser;
import com.stschools.security.UserPrincipal;
import com.stschools.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blogs")
public class BlogController {
    private final BlogRepository blogRepository;
    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<List<?>> getAllBlogs() {
        return ResponseEntity.ok(blogService.findAllBlogs());
    }

    @GetMapping("/user-love")
    public ResponseEntity<List<?>> getAllBlogsByLove(@CurrentUser UserPrincipal user){
        return ResponseEntity.ok(blogService.getAllBlogsByLove(user.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBlog(@PathVariable("id") Long blogId) {
        return ResponseEntity.ok(blogService.findBlogById(blogId));
    }


    @PutMapping("/edit")
    public ResponseEntity<?> updateUserInfo(@CurrentUser UserPrincipal user,
                                            @Valid @RequestBody BlogDTO request,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(blogService.update(request, user.getId()));
        }
    }

    @PutMapping("/love/{id}")
    public ResponseEntity<?> updateLoveBlog(@PathVariable("id") Long blogId,
                                            @CurrentUser UserPrincipal user){
        return ResponseEntity.ok(blogService.updateLove(blogId, user.getId()));
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity<Long> deleteBlog(@PathVariable(value = "blogId") Long blogId){
        return ResponseEntity.ok(blogService.deleteBlog(blogId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateBlogStatus(@PathVariable("id") Long blogId) {
        return ResponseEntity.ok(blogService.updateBlogStatus(blogId));
    }

    @PostMapping("/blogs")
    public ResponseEntity<?> getAllBlogsByQuery() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @PostMapping("/blogs/me")
    public ResponseEntity<?> getAllBlogsMe(@CurrentUser UserPrincipal user) {
        return ResponseEntity.ok(blogService.getAllBlogsByMe(user.getId()));
    }


    @PostMapping("/blog")
    public ResponseEntity<?> getBlogByQuery(@PathVariable("id") Long blogId) {
        return ResponseEntity.ok(blogService.getBlog(blogId));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<BlogDTO> registerPost(@ModelAttribute BlogRequest blog,
                                                @CurrentUser UserPrincipal user,
                                                BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(blogService.addBlog(blog, user.getId()));
        }
    }

    @PostMapping("/add/file")
    public ResponseEntity<List<?>> importToExcel(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(blogService.addBlog(file));
    }


    @GetMapping(path = "export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        List<Blog> blogs = blogRepository.findAllByOrderByIdAsc();
        BlogExcelExporter exporter = new BlogExcelExporter();
        exporter.export(blogs, response);
    }

    @GetMapping("/export/csv")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        List<Blog> blogs = blogRepository.findAllByOrderByIdAsc();
        BlogCsvExporter exporter = new BlogCsvExporter();

        exporter.export(blogs, response);
    }

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        List<Blog> blogs = blogRepository.findAllByOrderByIdAsc();
        BlogPdfExporter exporter = new BlogPdfExporter();
        exporter.export(blogs, response);
    }
}
