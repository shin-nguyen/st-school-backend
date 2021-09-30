package com.stschools.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_lecture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, length = 150)
    private String video;

    private Integer position;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Section section;
}
