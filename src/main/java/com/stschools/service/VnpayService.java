package com.stschools.service;

import com.stschools.dto.VideoDTO;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface VnpayService {
    String getCode(String vnpIpAddr, String vnpOrderInfo, String vnpOrderType ,Long courseId,Long userId,String link) throws UnsupportedEncodingException;
    String submitCode(Double vnpAmount,String vnpBankCode,String vnpBankTranNo,String vnpCardType, String vnpOrderInfo,String vnpPayDate, String vnpResponseCode,String vnpTmnCode,String vnpTransactionNo,String vnpTxnRef,String vnpSecureHashType,String vnpSecureHash,Long courseId,String email,String link);
}