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

    @OneToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Question> questions = new ArrayList<>();

    //Record
//    usersParticipated:
//    {
//        userId: { type: mongoose.Schema.Types.ObjectID, ref: "User" },
//        marks:{type:Number},
//        responses:[],
//        timeEnded:{type:Number},
//        timeStarted:{type:Number}
//    },

//    private List<User> enrolled = new ArrayList<>();

    //    scheduledFor: { type: String },
//    scheduledForString: { type: String },
    @Column
    private String duration;
    @Column
    private Boolean status;
    @Column
    private Long quizRestart;
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

    public Quiz(String name, String code, User createBy, String duration, Boolean status, Long quizRestart) {
        this.name = name;
        this.code = code;
        this.createBy = createBy;
        this.duration = duration;
        this.status = status;
        this.quizRestart = quizRestart;
    }
//    reminderSent: {
//        type: Boolean,
//        default: false,
//    }
}
