package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_comment_blog")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentBlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date createdTime;
    @Column
    private Date updateTime;

    @PrePersist
    protected void onCreate() {
        this.createdTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime = new Date();
    }

    @Column(length = 1000,nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Blog blog;
}
