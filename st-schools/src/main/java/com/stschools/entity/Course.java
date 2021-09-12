package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "tbl_course")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(length = 200)
    private String description;

    private Integer price;
    private String image;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Video> videos;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Order> orders;
}
