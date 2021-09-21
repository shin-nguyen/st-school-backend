package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tbl_course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 200, nullable = false)
    private String description;

    @Column
    private String totalLength;

    @Column
    private Integer price;

    @Column
    private String image;

    @Transient
    private Integer sectionTotal;

    @Transient
    private Integer lectureTotal;

    @ManyToOne
    @JoinColumn(name = "language_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Language language;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REFRESH)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CourseSection> courseSections;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REFRESH)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Order> orders;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REFRESH)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CommentCourse> commentCourses;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REFRESH)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<LikeCourse> likeCourses;

    public Course(String name, String description, String totalLength, Integer price, String image, Language language) {
        this.name = name;
        this.description = description;
        this.totalLength = totalLength;
        this.price = price;
        this.image = image;
        this.language = language;
    }
}
