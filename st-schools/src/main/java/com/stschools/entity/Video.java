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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "course_item_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CourseItem courseItem;
}
