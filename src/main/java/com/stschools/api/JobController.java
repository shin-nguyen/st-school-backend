package com.stschools.api;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.stschools.entity.Message;
import com.stschools.entity.SchedulerJobInfo;
import com.stschools.job.EmailJob;
import com.stschools.job.SmsJob;
import com.stschools.payload.schedule.ScheduleRequest;
import com.stschools.payload.schedule.ScheduleResponse;
import com.stschools.payload.schedule.SmsRequest;
import com.stschools.service.SchedulerJobService;
import com.stschools.service.SmsService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class JobController {

    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private SchedulerJobService scheduleJobService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private Scheduler scheduler;

    @RequestMapping(value = "/saveOrUpdate", method = { RequestMethod.POST })
    public Object saveOrUpdate(@RequestBody SchedulerJobInfo job) {
        log.info("params, job = {}", job);
        Message message = Message.failure();
        try {
            scheduleJobService.saveOrupdate(job);
            message = Message.success();
        } catch (Exception e) {
            message.setMsg(e.getMessage());
            log.error("updateCron ex:", e);
        }
        return message;
    }

    @RequestMapping("/metaData")
    public Object metaData() throws SchedulerException {
        SchedulerMetaData metaData = scheduleJobService.getMetaData();
        return metaData;
    }

    @RequestMapping("/getAllJobs")
    public Object getAllJobs() throws SchedulerException {
        List<SchedulerJobInfo> jobList = scheduleJobService.getAllJobList();
        return jobList;
    }

    @RequestMapping(value = "/runJob", method = { RequestMethod.GET, RequestMethod.POST })
    public Object runJob(SchedulerJobInfo job) {
        log.info("params, job = {}", job);
        Message message = Message.failure();
        try {
            scheduleJobService.startJobNow(job);
            message = Message.success();
        } catch (Exception e) {
            message.setMsg(e.getMessage());
            log.error("runJob ex:", e);
        }
        return message;
    }

    @RequestMapping(value = "/pauseJob", method = { RequestMethod.GET, RequestMethod.POST })
    public Object pauseJob(SchedulerJobInfo job) {
        log.info("params, job = {}", job);
        Message message = Message.failure();
        try {
            scheduleJobService.pauseJob(job);
            message = Message.success();
        } catch (Exception e) {
            message.setMsg(e.getMessage());
            log.error("pauseJob ex:", e);
        }
        return message;
    }

    @RequestMapping(value = "/resumeJob", method = { RequestMethod.GET, RequestMethod.POST })
    public Object resumeJob(SchedulerJobInfo job) {
        log.info("params, job = {}", job);
        Message message = Message.failure();
        try {
            scheduleJobService.resumeJob(job);
            message = Message.success();
        } catch (Exception e) {
            message.setMsg(e.getMessage());
            log.error("resumeJob ex:", e);
        }
        return message;
    }

    @RequestMapping(value = "/deleteJob", method = { RequestMethod.GET, RequestMethod.POST })
    public Object deleteJob(SchedulerJobInfo job) {
        log.info("params, job = {}", job);
        Message message = Message.failure();
        try {
            scheduleJobService.deleteJob(job);
            message = Message.success();
        } catch (Exception e) {
            message.setMsg(e.getMessage());
            log.error("deleteJob ex:", e);
        }
        return message;
    }

    @PostMapping("/schedule")
    public ResponseEntity<ScheduleResponse> schedule(@Valid @RequestBody ScheduleRequest scheduleRequest) {
        try {
            ZonedDateTime dateTime = ZonedDateTime.of(scheduleRequest.getDateTime(), scheduleRequest.getTimeZone());
            if(dateTime.isBefore(ZonedDateTime.now())) {
                ScheduleResponse scheduleResponse = new ScheduleResponse(false,
                        "dateTime must be after current time");
                return ResponseEntity.badRequest().body(scheduleResponse);
            }

            JobDetail jobDetail = buildJobDetail(scheduleRequest);
            Trigger trigger = buildJobTrigger(jobDetail, dateTime);
            scheduler.scheduleJob(jobDetail, trigger);

            ScheduleResponse scheduleResponse = new ScheduleResponse(true,
                    jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Scheduled Successfully!");
            return ResponseEntity.ok(scheduleResponse);
        } catch (SchedulerException ex) {
            logger.error("Error scheduling", ex);

            ScheduleResponse scheduleResponse = new ScheduleResponse(false,
                    "Error scheduling. Please try later!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(scheduleResponse);
        }
    }

    @PostMapping("/sms")
    public void sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        smsService.sendSms(smsRequest);
    }

    private JobDetail buildJobDetail(ScheduleRequest scheduleRequest) throws SchedulerException {
        JobDataMap jobDataMap = new JobDataMap();

        if(scheduleRequest.getEmail() != null && scheduleRequest.getSubject() != null && scheduleRequest.getBody() != null){
            jobDataMap.put("email", scheduleRequest.getEmail());
            jobDataMap.put("subject", scheduleRequest.getSubject());
            jobDataMap.put("body", scheduleRequest.getBody());

            return JobBuilder.newJob(EmailJob.class)
                    .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                    .withDescription("Send Email Job")
                    .usingJobData(jobDataMap)
                    .storeDurably()
                    .build();
        } else if(scheduleRequest.getPhoneNumber() != null && scheduleRequest.getMessage() != null){
            jobDataMap.put("phoneNumber", scheduleRequest.getPhoneNumber());
            jobDataMap.put("message", scheduleRequest.getMessage());

            return JobBuilder.newJob(SmsJob.class)
                    .withIdentity(UUID.randomUUID().toString(), "sms-jobs")
                    .withDescription("Send Sms Job")
                    .usingJobData(jobDataMap)
                    .storeDurably()
                    .build();
        } else {
            throw new SchedulerException();
        }
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "schedule-triggers")
                .withDescription("Schedule Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}