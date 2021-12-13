package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_blog")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    private String title;
    @Column(nullable = false, length = 2000)
    private String summary;

    @Column(nullable = false, length = 10000000)
    private String content;

    @Column
    private String image;

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


    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "tbl_blog_topic",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Collection<Topic> topics;

    public Blog(Long id, String title, String summary, String content, String image, Boolean status) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.image = image;
        this.status = status;
    }

    public Blog(String title, String summary, String content, Boolean status, String image, User user, List<Topic> topics) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.status = status;
        this.image = image;
        this.user = user;
        this.topics = topics;
    }
}
