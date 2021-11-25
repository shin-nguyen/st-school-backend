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

    public CommentBlog(String content, User user, Blog blog) {
        this.content = content;
        this.user = user;
        this.blog = blog;
    }
}
