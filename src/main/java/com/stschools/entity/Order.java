package com.stschools.entity;

import lombok.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @PrePersist
    protected void onCreate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.createdTime = formatter.format(date);
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

    public Integer getTotal() {
        return this.course.getPrice();
    }

    public String getUserName() {
        return this.user.getLastName();
    }

    public String getCourseName() {
        return this.course.getName();
    }

}
