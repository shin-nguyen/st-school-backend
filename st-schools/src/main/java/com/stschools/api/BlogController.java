package com.stschools.api;

import com.stschools.dto.BlogDTO;
import com.stschools.dto.TopicDTO;
import com.stschools.dto.UserDTO;
import com.stschools.exception.ApiRequestException;
import com.stschools.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService IBlogService;

    @GetMapping("/blogs")
    public ResponseEntity<?> getBlogs(){
        try{
            final List<BlogDTO> blogs = IBlogService.getBlogs();
            if(blogs == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(blogs);

        }
        catch (Exception ex){
            throw new ApiRequestException("Not found customers", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BlogDTO> create(@RequestParam String title,
                                          @RequestParam String summary,
                                          @RequestParam String content,
                                          @RequestParam Boolean  status,
                                          @RequestParam TopicDTO topic,
                                          @RequestParam UserDTO user ) {

        BlogDTO blogDto = BlogDTO.builder()
                .title(title)
                .summary(summary)
                .content(content)
                .timeCreated(new Date())
                .status(status)
                .topic(topic)
                .user(user)
                .build();

        try{
            return ResponseEntity.ok().body(IBlogService.save(blogDto));
        } catch (Exception ex){
            throw new ApiRequestException("Can't save blog", HttpStatus.BAD_REQUEST);
        }
    }


}
