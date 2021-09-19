package com.stschools.api;

import com.stschools.dto.BlogDto;
import com.stschools.dto.TopicDto;
import com.stschools.dto.UserDto;
import com.stschools.service.IBlogService;
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

    private final IBlogService IBlogService;

    @GetMapping("/blogs")
    public ResponseEntity<List<BlogDto>> getBlogs(){
        try{
            final List<BlogDto> blogs = IBlogService.getBlogs();
            if(blogs == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok().body(blogs);

        }
        catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found customers", ex);
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BlogDto> create(@RequestParam String title,
                                       @RequestParam String summary,
                                       @RequestParam String content,
                                       @RequestParam Boolean  status,
                                       @RequestParam TopicDto topic,
                                       @RequestParam UserDto user ) {

        BlogDto blogDto = BlogDto.builder()
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't save blog", ex);
        }
    }


}
