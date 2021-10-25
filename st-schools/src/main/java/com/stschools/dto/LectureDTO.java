package com.stschools.dto;

import com.stschools.entity.Section;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureDTO {
    private Long id;
    private String title;
    private String video;
    private Integer position;
//    private SectionDTO section;
}
