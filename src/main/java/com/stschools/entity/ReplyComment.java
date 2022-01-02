package com.stschools.entity;

import com.stschools.util.DateTimeControl;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_reply_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    private String createdTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Comment comment;

    @PrePersist
    protected void onCreate() {
        this.createdTime = DateTimeControl.formatDate(new Date());
    }
}
