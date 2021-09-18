package com.stschools.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_video")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;
    @Column(length = 1500,nullable = false)
    private String content;

    private Integer position;

    @ManyToOne
    @JoinColumn(name = "course_item_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CourseItem courseItem;
}
