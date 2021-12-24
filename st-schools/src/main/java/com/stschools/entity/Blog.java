package com.stschools.entity;

import com.stschools.util.DateTimeControl;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_blog")
@Where(clause = "is_deleted = false")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    private String title;

    @Column(nullable = true, length = 2000)
    private String summary;

    @Column(nullable = false, length = 10000000)
    private String content;

    @Column
    private Long view;

    @Column
    private String image;

    @Column
    private String createdTime;

    @Column
    private String updateTime;

    @PrePersist
    protected void onCreate() {
        this.createdTime = DateTimeControl.formatDate(new Date());
        this.updateTime = DateTimeControl.formatDate(new Date());
        this.view = 0L;
        this.isDeleted = false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime =  DateTimeControl.formatDate(new Date());
    }

    private Boolean status;

    private Boolean isDeleted;

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
