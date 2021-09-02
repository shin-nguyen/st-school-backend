package com.stschools.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "tbl_video")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;
}
