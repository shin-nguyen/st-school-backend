package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    private String language;

    @Column
    private Integer price;

    @Column
    private String image;

    @Transient
    private Integer sectionTotal;

    @Transient
    private Integer lectureTotal;

//    @OneToMany(mappedBy = "course", cascade = CascadeType.REFRESH)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Collection<Section> sections;
//
//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private List<Order> orders = new ArrayList<>();
//
//    @OneToMany(mappedBy = "course", cascade = CascadeType.REFRESH)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Collection<CommentCourse> commentCourses;
//
//    @OneToMany(mappedBy = "course", cascade = CascadeType.REFRESH)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Collection<LikeCourse> likeCourses;

    public Course(String name, String description, String totalLength, String language, Integer price, String image) {
        this.name = name;
        this.description = description;
        this.totalLength = totalLength;
        this.language = language;
        this.price = price;
        this.image = image;
    }

    public Course(Long id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;

    public Course(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
