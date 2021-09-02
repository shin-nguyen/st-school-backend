package com.stschools.api;

import com.stschools.common.enums.FileType;
import com.stschools.dto.BlogDto;
import com.stschools.service.BlogService;
import com.stschools.util.FileControl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/blogs")
    public ResponseEntity<List<BlogDto>> getBlogs(){
        try{
            final List<BlogDto> blogs = blogService.getBlogs();
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
                                       @RequestParam String author,
                                       @RequestParam Boolean  status,
//                                       @RequestParam Topic topic,
//                                       @RequestParam User user,
                                       @RequestParam MultipartFile image) throws IOException {
        FileControl.saveFile(FileType.IMAGE, image);

        BlogDto blogDto = BlogDto.builder()
                .title(title)
                .summary(summary)
                .content(content)
                .author(author)
                .timeCreated(new Date())
                .status(status)
//                .topic(topic)
//                .user(user)
                .image(image.getOriginalFilename())
                .build();

        try{
            return ResponseEntity.ok().body(blogService.save(blogDto));
        } catch (Exception ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't save blog", ex);
        }
    }


}
