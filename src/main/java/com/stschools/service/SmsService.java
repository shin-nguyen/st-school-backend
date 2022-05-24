package com.stschools.service;

import com.stschools.payload.schedule.SmsRequest;

public interface SmsService {
    void sendSms(SmsRequest smsRequest);
}
