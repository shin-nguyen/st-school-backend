package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tbl_course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 200, nullable = false)
    private String description;

    @Column
    private String language;

    @Column
    private Integer price;

    @Column
    private String image;

    @Transient
    private Integer videoTotal;

    public Course(String name, String description, String language, Integer price, String image) {
        this.name = name;
        this.description = description;
        this.language = language;
        this.price = price;
        this.image = image;
    }

    public Course(Long id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
