package com.stschools.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogUserLoveDTO {
    private Long id;
    @Override
    public String toString() {
        return "{\"id\":" + id + "}";
    }

}
