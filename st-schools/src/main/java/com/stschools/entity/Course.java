package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

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
    private Date complete;

    private Integer price;
    private String image;

    @Transient
    private Integer itemTotal() {
        return courseItems == null ? 0 : courseItems.size();
    }

    @Transient
    Integer videoTotal() {
        return null;
    }

    @ManyToOne
    @JoinColumn(name = "language_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Language language;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REFRESH)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CourseItem> courseItems;

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
}
