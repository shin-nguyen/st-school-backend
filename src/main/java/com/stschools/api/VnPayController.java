package com.stschools.api;

import com.stschools.configuration.LinksGereneral;
import com.stschools.dto.CourseDTO;
import com.stschools.dto.OrderDTO;
import com.stschools.dto.UserDTO;
import com.stschools.payment.vnpay.VnPayConfig;
import com.stschools.service.CourseService;
import com.stschools.service.MailService;
import com.stschools.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/pay-vn")
@RequiredArgsConstructor
public class VnPayController {

    private final MailService mailSender;
    private final CourseService courseService;
    private final UserService userService;
    private final LinksGereneral linksg;
    private final VnPayConfig vnPayConfig;


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
                       @RequestParam(name = "course_Id", required = false, defaultValue = "null") Long courseId,
                       @RequestParam(name = "email", required = false, defaultValue = "null") String email
    ) {
        String button = "<a href=\r\n" + "linkURL" + ">\r\n"
                + "<button style=\"vertical-align:middle;position: relative;display: inline-block;\r\n"
                + "  border-radius: 4px;\r\n" + "  background-color: #f4511e;\r\n" + "  border: none;\r\n"
                + "  color: #FFFFFF;\r\n" + "  text-align: center;\r\n" + "  font-size: 22px;\r\n"
                + "  padding: 20px;\r\n" + "  width:40%;\r\n" + "  transition: all 0.5s;\r\n" + "  cursor: pointer;\r\n"
                + "  margin-left: 30%;\">" + "OK" + "</button>" + "</a>";
        String responses = "<div><h1 style=\"text-align: center;\">THANH TOÁN THÀNH CÔNG</h1></div>" + button;

        try {
            CourseDTO course = courseService.findByID(courseId);
            UserDTO userDTO = userService.findUserByEmail(email);
            OrderDTO order = OrderDTO.builder()
                    .course(course)
                    .vnpayId(vnpTransactionNo)
                    .user(userDTO)
                    .build();


            responses = "<div><h1 style=\" text-align: center;	\">THANH TOÁN THẤT BẠI</h1></div>" + button;
        } catch (Exception ex) {
            responses = "<div><h1 style=\" text-align: center;	\">THANH TOÁN THÀNH CÔNG</h1></div>" + button;
        }
        return responses;
    }


    @PostMapping(value = "/get-code")
    public String createStringQuery(
            @RequestParam(name = "link", required = false, defaultValue = "null") String link,
            @RequestParam(name = "vnp_IpAddr", required = false, defaultValue = "null") String vnpIpAddr,
            @RequestParam(name = "vnp_OrderInfo", required = false, defaultValue = "null") String vnpOrderInfo,
            @RequestParam(name = "vnp_OrderType", required = false, defaultValue = "null") String vnpOrderType,
            @RequestParam(name = "course_Id", required = false, defaultValue = "null") Long courseId,
            @RequestParam(name = "email", required = false, defaultValue = "null") String email
    ) throws UnsupportedEncodingException {
//		VeRequest wrapperTemp1 = new VeRequest();
//		wrapperTemp1.setSlot(wrapper.getSlot());
//		VnPayController.linkURL = link;
        String vnpVersion = "2";
        String vnpCommand = "pay";
        String vnpTmnCode = "F14VUCZZ";
        String vnpBankCode = "NCB";
        String vnpCreateDate;
        String vnpCurrCode = "VND";
        String vnpLocale = "vn";
        String vnpTxnRef;
        String vnpSupChecksumt = "QRXINORZNIQWXKWHYLEKENHXHAGNJTMT";


//		wrapperTemp1.setGiaVe(wrapper.getGiaVe());
//		wrapperTemp1.setDate(wrapper.getDate());
//		wrapperTemp1.setIdTuyenXe(wrapper.getIdTuyenXe());
//		wrapperTemp1.setGioKetThuc(wrapper.getGioKetThuc());
//		wrapperTemp1.setGioChay(wrapper.getGioChay());
//		wrapperTemp1.setSlot(wrapper.getSlot());
//		wrapperTemp1.setDiemXuong(wrapper.getDiemXuong());

		Integer vnpAmount = 100000;
		Date dt = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(dt);
		vnpCreateDate = dateString;

        String vnp_ReturnUrl = "";

//		VeRequest wrapperTemp = new VeRequest();
//		wrapperTemp.setGiaVe(wrapper.getGiaVe2());
//		wrapperTemp.setDate(wrapper.getDate2());
//		wrapperTemp.setIdTuyenXe(wrapper.getIdTuyenXe2());
//		wrapperTemp.setGioKetThuc(wrapper.getGioKetThuc2());
//		wrapperTemp.setGioChay(wrapper.getGioChay2());
//		wrapperTemp.setSlot(wrapper.getSlot2());
//		wrapperTemp.setDiemXuong(wrapper.getDiemXuong2());
        vnpAmount *= 100;

        vnp_ReturnUrl = linksg.getLinksUrlApp() + "/api/v1/pay-vn/submit?" + "course_Id=" + courseId
                + "&email=" + email;

        vnpTxnRef = vnpCreateDate;
        // Tạo mã map links

        Map<String, String> vnpParams = new HashMap<>();
        vnpParams.put("vnp_Version", vnpVersion);
        vnpParams.put("vnp_Command", vnpCommand);
        vnpParams.put("vnp_TmnCode", vnpTmnCode);
        vnpParams.put("vnp_Amount", vnpAmount.toString());
        vnpParams.put("vnp_CurrCode", vnpCurrCode);
        vnpParams.put("vnp_BankCode", vnpBankCode);
        vnpParams.put("vnp_TxnRef", vnpTxnRef);
        vnpParams.put("vnp_OrderInfo", vnpOrderInfo);
        vnpParams.put("vnp_OrderType", vnpOrderType);
        vnpParams.put("vnp_Locale", vnpLocale);
        vnpParams.put("vnp_ReturnUrl", vnp_ReturnUrl);
        vnpParams.put("vnp_IpAddr", vnpIpAddr);
        vnpParams.put("vnp_CreateDate", vnpCreateDate);
        String queryUrl = vnPayConfig.generateQueryUrl(vnpParams, vnpSupChecksumt);
        return "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?" + queryUrl;
    }

//	@RequestMapping(value = "/refund", method = RequestMethod.POST)
//	public ResponseEntity<?> refundVnpay(
//
//	) {
//		BaseResponse response = new BaseResponse();
//
//		String vnp_Command = "pay";
//		String vnp_TmnCode = "BZKHFB2U";
//		String vnp_TransactionNo = "13494893";
//		Double vnp_Amount = 200000.0;
//		Date dt = new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//		String dateString = formatter.format(dt);
//		String vnp_TransDate = dateString;
//
//		Map<String, String> vnp_Params = new HashMap<>();
//		vnp_Params.put("vnp_Command", vnp_Command);
//		vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
//		vnp_Params.put("vnp_TransactionNo", vnp_TransactionNo);
//		vnp_Params.put("vnp_TransDate", vnp_TransDate);
//		vnp_Params.put("vnp_Amount", vnp_Amount + "000");
//
//		String queryUrl = "";
//
//		response.setData(queryUrl);
//		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
//	}
}
