package com.stschools.entity;


import com.stschools.util.DateTimeControl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_activity_progress")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double sun;

    @Column
    private double mon;
    @Column
    private double tue;
    @Column
    private double wed;
    @Column
    private double thu;
    @Column
    private double fri;
    @Column
    private double sat;
    @Column
    private Long userId;

    @PrePersist
    protected void onCreate() {
        this.sun = 0;
        this.mon = 0;
        this.tue = 0;
        this.wed = 0;
        this.thu = 0;
        this.sat = 0;
        this.fri = 0;
    }
    public ActivityProgress(Long userId) {
        this.userId = userId;
    }
}
