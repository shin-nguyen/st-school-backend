package com.stschools.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class LinksGereneral {
    @Value("${urlapp}")
    private String linksUrlApp;

    @Value("${hostname}")
    private String linksBackFront;
}
