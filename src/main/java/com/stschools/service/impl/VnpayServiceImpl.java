package com.stschools.service.impl;

import com.stschools.configuration.VnpayConfiguration;
import com.stschools.configuration.VnpaySha256Config;
import com.stschools.entity.Course;
import com.stschools.entity.Order;
import com.stschools.entity.User;
import com.stschools.exception.ApiRequestException;
import com.stschools.repository.CourseRepository;
import com.stschools.repository.OrderRepository;
import com.stschools.repository.UserRepository;
import com.stschools.service.MailService;
import com.stschools.service.VnpayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VnpayServiceImpl implements VnpayService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final MailService mailSender;
    private final VnpayConfiguration vnpay;
    private final VnpaySha256Config vnpaySha256Config;

    @Value("${urlapp}")
    private String urlapp;

    @Override
    public String getCode(String vnpIpAddr, String vnpOrderInfo, String vnpOrderType, Long courseId, String email, String link) throws UnsupportedEncodingException {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ApiRequestException("Course is null!", HttpStatus.BAD_REQUEST));

        User user = userRepository.findByEmail(email);


        Integer vnpAmount = course.getPrice();
        vnpAmount *= 2300000;

        String vnp_ReturnUrl = "https://" + urlapp + "/api/v1/pay-vn/submit?" + "course_Id=" + courseId
                + "&email=" + user.getEmail() + "&callback=" + link;

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
        String vnpTxnRef = courseId + "-" + dateFormat.format(new Date());

        // Tạo mã map links
        Map<String, String> vnpParams = new HashMap<>();
        vnpParams.put("vnp_Version", vnpay.getVersion());
        vnpParams.put("vnp_Command", vnpay.getCommand());
        vnpParams.put("vnp_TmnCode", vnpay.getTmnCode());
        vnpParams.put("vnp_Amount", vnpAmount.toString());
        vnpParams.put("vnp_CurrCode", vnpay.getCurCode());
        vnpParams.put("vnp_BankCode", vnpay.getBankCode());
        vnpParams.put("vnp_TxnRef", vnpTxnRef);
        vnpParams.put("vnp_OrderInfo", vnpOrderInfo);
        vnpParams.put("vnp_OrderType", vnpOrderType);
        vnpParams.put("vnp_Locale", vnpay.getLocale());
        vnpParams.put("vnp_ReturnUrl", vnp_ReturnUrl);
        vnpParams.put("vnp_IpAddr", vnpIpAddr);
        vnpParams.put("vnp_CreateDate", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

        String queryUrl = vnpaySha256Config.generateQueryUrl(vnpParams, vnpay.getSupCheck());
        return "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?" + queryUrl;
    }

    @Override
    @Transactional
    public String submitCode(Double vnpAmount, String vnpBankCode, String vnpBankTranNo, String vnpCardType, String vnpOrderInfo, String vnpPayDate, String vnpResponseCode, String vnpTmnCode, String vnpTransactionNo, String vnpTxnRef, String vnpSecureHashType, String vnpSecureHash, Long courseId, String email, String link) {
        String button = "<a href=\r\n" + link + ">\r\n"
                + "<button style=\"vertical-align:middle;position: relative;display: inline-block;\r\n"
                + "  border-radius: 4px;\r\n" + "  background-color: #f4511e;\r\n" + "  border: none;\r\n"
                + "  color: #FFFFFF;\r\n" + "  text-align: center;\r\n" + "  font-size: 22px;\r\n"
                + "  padding: 20px;\r\n" + "  width:40%;\r\n" + "  transition: all 0.5s;\r\n" + "  cursor: pointer;\r\n"
                + "  margin-left: 30%;\">" + "OK" + "</button>" + "</a>";

        String responses = "<div><h1 style=\"text-align: center;\">THANH TOÁN THÀNH CÔNG</h1></div>" + button;
        try {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new ApiRequestException("Course is null!", HttpStatus.BAD_REQUEST));
            course.setSubTotal(course.getSubTotal() + 1);
            courseRepository.saveAndFlush(course);


            User user = userRepository.findByEmail(email);

            Order order = Order.builder()
                    .course(course)
                    .vnpayId(vnpTransactionNo)
                    .user(user)
                    .build();

            Order orderOfDb = orderRepository.findAll().stream()
                    .filter(o -> (o.getCourse().getId() == courseId)
                            && (o.getUser().getId() == user.getId())).findFirst().orElse(
                                    null
                    );

            if (orderOfDb != null) {
                throw new ApiRequestException("Order exits", HttpStatus.BAD_REQUEST);
            }

            try{
                Order newOrder = orderRepository.save(order);

                String subject = "Order #" + order.getId();
                String template = "order-template";
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("order", newOrder);
                mailSender.sendMessageHtml(order.getUser().getEmail(), subject, template, attributes);
            }
            catch (Exception ex){

            }

            return responses;
        } catch (Exception ex) {
            responses = "<div><h1 style=\" text-align: center;	\">THANH TOÁN THẤT BẠI</h1></div>" + button;
            return responses;
        }

    }
}
