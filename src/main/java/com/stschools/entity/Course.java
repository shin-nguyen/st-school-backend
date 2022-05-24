package com.stschools.entity;

import com.stschools.util.DateTimeControl;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "tbl_course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 500)
    private String about;

    @Column(length = 10000000)
    private String description;

    @Column
    private String lecturer;

    @Column(length = 2000)
    private String requirements;

    @Column(length = 2000)
    private String isFor;

    @Column
    private String language;

    @Column
    private String topic;

    @Column
    private Integer price;

    @Column
    private Integer subPrice;

    @Column
    private String image;

    @Column
    private Integer subTotal;

    @Transient
    private Integer commentTotal;

    @Transient
    private Integer rateTotal;

    @Transient
    private Double averageRate;

    @Transient
    private Integer videoTotal;

    @Transient
    private Double duration;

    @Column
    private String createdTime;

    @Column
    private String updateTime;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Video> videos;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Comment> comments;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Review> reviews;

    @PrePersist
    protected void onCreate() {
        this.createdTime = DateTimeControl.formatDate(new Date());
        this.updateTime = DateTimeControl.formatDate(new Date());
        this.subTotal = 0;
        this.averageRate = 5D;
        this.duration = 0D;
        this.commentTotal = 0;
        this.rateTotal = 0;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime =  DateTimeControl.formatDate(new Date());
    }

    public Course(String name, String about, String description, String lecturer, String requirements, String isFor, String language, String topic, Integer price, String image) {
        this.name = name;
        this.about = about;
        this.description = description;
        this.lecturer = lecturer;
        this.requirements = requirements;
        this.isFor = isFor;
        this.language = language;
        this.topic = topic;
        this.price = price;
        this.image = image;
    }

    public Course(Long id, String name, String about, String description, String lecturer, String requirements, String isFor, String language, String topic, Integer price, String image, Integer subTotal, String createdTime, String updateTime) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.description = description;
        this.lecturer = lecturer;
        this.requirements = requirements;
        this.isFor = isFor;
        this.language = language;
        this.topic = topic;
        this.price = price;
        this.image = image;
        this.subTotal = subTotal;
        this.createdTime = createdTime;
        this.updateTime = updateTime;
    }

    public Integer getVideoTotal() {
        return this.videos != null? this.videos.size() : 0;
    }

    public Double getAverageRate() {
        double sum = 0.0;
        if( this.reviews != null && this.reviews.size() > 0){
            for (Review review : this.reviews) {
                sum += review.getRate();
            }
            return (double)Math.round(sum/this.reviews.size() * 10) / 10;
        }
        return 5.0;
    }

    public Double getDuration() {
        double sum = 0.0;
        if( this.videos != null && this. videos.size() > 0){
            for (Video video : this.videos){
                sum += video.getDuration();
            }
            return (double)Math.round(sum/3600 * 100) / 100;
        }
        return 0.0;
    }

    public Integer getCommentTotal() {
        return this.comments != null? this.comments.size() : 0;
    }

    public Integer getRateTotal() {
        return this.reviews != null? this.reviews.size() : 0;
    }
}