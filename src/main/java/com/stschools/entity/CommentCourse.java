package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_comment_course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String createdTime;

    @Column
    private String updateTime;

    @PrePersist
    protected void onCreate() {
        this.createdTime = new Date().toString();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime = new Date().toString();
    }

    @Column(length = 300,nullable = false)
    private String content;

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
