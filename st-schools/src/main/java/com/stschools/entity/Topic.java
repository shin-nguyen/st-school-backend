package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "tbl_topic")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Subject subject;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Blog> blogs;
}
