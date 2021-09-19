package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "tbl_blog")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;
    @Column(nullable = false, length = 200)
    private String description;

    @Column(nullable = false, length = 1500)
    private String content;

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


    private Boolean status;

    @Transient
    private Integer likeTotal() {
        return likeBlogs == null ? 0 : likeBlogs.size();
    }

    @Transient
    private Integer commentTotal() {
        return commentBlogs == null ? 0 : commentBlogs.size();
    }

    @OneToMany(mappedBy = "blog", cascade = CascadeType.REFRESH)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CommentBlog> commentBlogs;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.REFRESH)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<LikeBlog> likeBlogs;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "tbl_blog_topic",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Collection<Topic> topics;
}
