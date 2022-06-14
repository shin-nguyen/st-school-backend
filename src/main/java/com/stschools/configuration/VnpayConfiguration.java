package com.stschools.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("vnpay")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VnpayConfiguration {
    private String version;
    private String command;
    private String tmnCode;
    private String bankCode;
    private String curCode;
    private String locale;
    private String supCheck;
}
