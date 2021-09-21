package com.stschools.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_course_lecture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseLecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, length = 150)
    private String video;

    private Integer position;

    @ManyToOne
    @JoinColumn(name = "course_section_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CourseSection courseSection;
}
