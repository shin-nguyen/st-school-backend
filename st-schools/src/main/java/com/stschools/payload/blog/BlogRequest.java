package com.stschools.payload.blog;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class BlogRequest implements Serializable {
//    @NotBlank
    private String title;
//    @NotBlank
    private String content;
    private MultipartFile file;
}
