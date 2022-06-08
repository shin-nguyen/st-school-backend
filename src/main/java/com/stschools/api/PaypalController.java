package com.stschools.api;

import com.stschools.dto.QuizDTO;
import com.stschools.exception.InputFieldException;
import com.stschools.payload.orders.OrdersRequest;
import com.stschools.security.CurrentUser;
import com.stschools.security.UserPrincipal;
import com.stschools.service.paypal.PaypalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.web.server.ResponseStatusException;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/paypal")
@RequiredArgsConstructor
public class PaypalController {

    private final PaypalService paypalService;

    @GetMapping("/create-paypal-payment")
    public String  payment() {

        final String SUCCESS_URL = "http://localhost:8080/api/v1/paypal/execute?amount=1&currency=USD";
        final String CANCEL_URL  = "http://cancel.url";

        try {
            Payment payment = paypalService.createPayment(0.99, "USD", "paypal",
                    "sale", "This is the payment description.", CANCEL_URL,
                    SUCCESS_URL);

            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(value = "/execute")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "redirect:http://return_url/?status=success&id="+payment.getId()+ "&state=" + payment.getState();
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

}