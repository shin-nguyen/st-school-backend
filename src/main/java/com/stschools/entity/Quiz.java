package com.stschools.entity;

import com.stschools.util.DateTimeControl;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_quiz")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String code;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User createBy;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval=true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Question> questions = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column
    private String duration;
    @Column
    private Boolean status;
    @Column
    private Long restart;
    @Column
    private Boolean isDeleted;

    @Column
    private String createdTime;

    @Column
    private String updateTime;

    @PrePersist
    protected void onCreate() {
        this.createdTime = DateTimeControl.formatDate(new Date());
        this.updateTime = DateTimeControl.formatDate(new Date());
        this.isDeleted = false;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime =  DateTimeControl.formatDate(new Date());
    }

    public Quiz(String name, String code, User createBy, String duration, Boolean status, Long restart) {
        this.name = name;
        this.code = code;
        this.createBy = createBy;
        this.duration = duration;
        this.status = status;
        this.restart = restart;
    }
}
