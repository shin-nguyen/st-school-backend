package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tbl_comment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "time_created")
    private Date timeCreated;

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
