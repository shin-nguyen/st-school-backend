package com.stschools.entity;

import com.stschools.util.DateTimeControl;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_note")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String createdTime;

    @Column
    private String updateTime;

    @Column
    private String atTime;

    @Column(length = 1000,nullable = false)
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

    @ManyToOne
    @JoinColumn(name = "video_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Video video;

    @PrePersist
    protected void onCreate() {
        this.createdTime = DateTimeControl.formatDate(new Date());
        this.updateTime = DateTimeControl.formatDate(new Date());
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime =  DateTimeControl.formatDate(new Date());
    }
}
