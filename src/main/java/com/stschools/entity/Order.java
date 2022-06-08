package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "tbl_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String createdTime;

    @Column
    private String updateTime;

    @Column
    private Double progress;

    @Column
    private Boolean isComplete;

    @PrePersist
    protected void onCreate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.createdTime = formatter.format(date);
        this.isComplete = false;
        progress  = 0.0;
    }

    @PreUpdate
    protected void onUpdate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.createdTime = formatter.format(date);
    }

    @Transient
    private Integer total;

    @Transient
    private String userName;

    @Transient
    private String courseName;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "course_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "tbl_progress",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private Set<Video> videos;

    public String getUserName() {
        return this.user.getLastName();
    }

    public String getCourseName() {
        return this.course.getName();
    }

    public Integer getTotal() {
        return this.course.getPrice();
    }
}
