package com.stschools.dto;

import lombok.Data;

@Data
public class AnswerDTO {

    private Long id;

    @Override
    public String toString() {
        return "{\"id\":" + id + ", \"text\":\"" + text + "\"}";
    }
    private String text;
}
