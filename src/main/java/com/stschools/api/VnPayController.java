package com.stschools.api;

import com.stschools.security.CurrentUser;
import com.stschools.security.UserPrincipal;
import com.stschools.service.VnpayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/pay-vn")
@RequiredArgsConstructor
public class VnPayController {

    private final VnpayService vnpayService;

    @GetMapping(value = "/submit")
    public String post(@RequestParam(name = "vnp_Amount", required = false, defaultValue = "null") Double vnpAmount,
                       @RequestParam(name = "vnp_BankCode", required = false, defaultValue = "null") String vnpBankCode,
                       @RequestParam(name = "vnp_BankTranNo", required = false, defaultValue = "null") String vnpBankTranNo,
                       @RequestParam(name = "vnp_CardType", required = false, defaultValue = "null") String vnpCardType,
                       @RequestParam(name = "vnp_OrderInfo", required = false, defaultValue = "null") String vnpOrderInfo,
                       @RequestParam(name = "vnp_PayDate", required = false, defaultValue = "null") String vnpPayDate,
                       @RequestParam(name = "vnp_ResponseCode", required = false, defaultValue = "null") String vnpResponseCode,
                       @RequestParam(name = "vnp_TmnCode", required = false, defaultValue = "null") String vnpTmnCode,
                       @RequestParam(name = "vnp_TransactionNo", required = false, defaultValue = "null") String vnpTransactionNo,
                       @RequestParam(name = "vnp_TxnRef", required = false, defaultValue = "null") String vnpTxnRef,
                       @RequestParam(name = "vnp_SecureHashType", required = false, defaultValue = "null") String vnpSecureHashType,
                       @RequestParam(name = "vnp_SecureHash", required = false, defaultValue = "null") String vnpSecureHash,
                       @RequestParam(name = "course_Id") Long courseId,
                       @RequestParam(name = "email") String email,
                       @RequestParam(name = "callback") String callback
    ) {
        return vnpayService.submitCode(vnpAmount, vnpBankCode, vnpBankTranNo, vnpCardType, vnpOrderInfo, vnpPayDate,
                vnpResponseCode, vnpTmnCode, vnpTransactionNo, vnpTxnRef, vnpSecureHashType, vnpSecureHash, courseId, email, callback);
    }


    @GetMapping(value = "/get-code")
    public String createStringQuery(
            @RequestParam(name = "vnp_IpAddr", required = false, defaultValue = "null") String vnpIpAddr,
            @RequestParam(name = "vnp_OrderInfo", required = false, defaultValue = "null") String vnpOrderInfo,
            @RequestParam(name = "vnp_OrderType", required = false, defaultValue = "null") String vnpOrderType,
            @RequestParam(name = "course_Id") Long courseId,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "callback") String callback
    ) throws UnsupportedEncodingException {
        return vnpayService.getCode(vnpIpAddr, vnpOrderInfo, vnpOrderType, courseId, email, callback);
    }
}
