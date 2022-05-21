package com.stschools.entity;

import com.stschools.util.DateTimeControl;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String createdTime;

    @Column
    private String updateTime;

    @Column(length = 1000, nullable = false)
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

    @ManyToOne
    @JoinColumn(name = "course_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ReplyComment> replies;

    @PrePersist
    protected void onCreate() {
        this.createdTime = DateTimeControl.formatDate(new Date());
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime = DateTimeControl.formatDate(new Date());
    }

    public Comment(String content, User user, Blog blog) {
        this.content = content;
        this.user = user;
        this.blog = blog;
    }
}
