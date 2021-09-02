package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tbl_blog")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String summary;
    private String image;
    private String content;

    @Column(name = "time_created")
    private Date timeCreated;

    private String author;
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;
}
