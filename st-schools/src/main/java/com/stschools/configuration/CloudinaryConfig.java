package com.stschools.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.stschools.common.constants.CloudinaryConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CloudinaryConstants.CLOUD_NAME,
                "api_key", CloudinaryConstants.API_KEY,
                "api_secret", CloudinaryConstants.API_SECRET,
                "secure", true));
    }
}
