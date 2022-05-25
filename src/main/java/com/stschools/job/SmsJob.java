package com.stschools.job;

import com.stschools.payload.schedule.SmsRequest;
import com.stschools.service.MailService;
import com.stschools.service.SmsService;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class SmsJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(SmsJob.class);

    @Autowired
    private SmsService smsService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        logger.info("Executing Job with key {}", jobExecutionContext.getJobDetail().getKey());
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String phoneNumber = jobDataMap.getString("phoneNumber");
        String message = jobDataMap.getString("message");

        smsService.sendSms(new SmsRequest(phoneNumber, message));
        logger.info("Sending Email to {}", phoneNumber);
    }
}