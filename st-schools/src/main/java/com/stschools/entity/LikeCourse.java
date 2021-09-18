package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_like_course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    private Date createdTime;

    @PrePersist
    protected void onCreate() {
        this.createdTime = new Date();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;
}
