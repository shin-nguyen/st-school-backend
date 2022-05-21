package com.stschools.entity;

import com.stschools.common.enums.AuthProvider;
import com.stschools.common.enums.Catogory;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

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
    private String lecturer;

    @Column
    private String language;

    @Column
    private Integer price;

    @Column
    private Integer subPrice;

    @Enumerated(EnumType.STRING)
    private Catogory category;

    @Column
    private String image;

    @Column
    private Integer videoTotal;

    @Column
    private Integer subTotal;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Video> videos;

//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Collection<Comment> comments;
//
//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Collection<Review> reviews;

    public Course(String name, String description, String language, Integer price, String image,String lecturer,Catogory category) {
        this.name = name;
        this.description = description;
        this.language = language;
        this.price = price;
        this.image = image;
        this.lecturer = lecturer;
        this.category = category;
    }

    public Course(Long id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }
}
