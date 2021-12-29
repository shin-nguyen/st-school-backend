package com.stschools.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CloudinaryControl {
    private static Cloudinary cloudinary;

    public static Map uploadFile(MultipartFile file) throws IOException {
        if(Objects.equals(file.getContentType(), "image")){
            return cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("resource_type", "image", "public_id", "st-school/images/" + file.getOriginalFilename()));
        }
        if(Objects.equals(file.getContentType(), "video")){
            return cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("resource_type", "video", "public_id", "st-school/videos/" + file.getOriginalFilename()));
        }
        return cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto", "public_id", "st-school/another/" + file.getOriginalFilename()));
    }
}
