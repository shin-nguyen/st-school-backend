package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tbl_language")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200,nullable = false)
    private String name;

    @OneToMany(mappedBy = "language")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Course> courses;
}
