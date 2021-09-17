package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tbl_like_blog")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeBlog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "time_created")
    private Date timeCreated;

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
