package com.stschools.entity;

import javax.persistence.*;

import lombok.*;

@ToString
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scheduler_job_info")
public class SchedulerJobInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @Column
    private String jobName;

    @Column
    private String jobGroup;

    @Column
    private String jobStatus;

    @Column
    private String jobClass;

    @Column
    private String cronExpression;

    @Column
    private String interfaceName;

    @Column
    private Long repeatTime;

    @Column
    private Boolean cronJob;
}
