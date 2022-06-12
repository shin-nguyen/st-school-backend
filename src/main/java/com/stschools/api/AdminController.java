package com.stschools.api;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.security.CurrentUser;
import com.stschools.security.UserPrincipal;
import com.stschools.service.OrderService;
import com.stschools.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long userId) throws ApiException {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<?>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/dashboard/user")
    public ResponseEntity<List<?>> getCustomersDashboard() {
        return ResponseEntity.ok(userService.getAllCustomerByDashboards());
    }

    @PostMapping("/dashboard/order")
    public ResponseEntity<?> getOrdersDashboard() {
        return ResponseEntity.ok(orderService.findAllByCreateDateTop5());
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard() {
        return ResponseEntity.ok(userService.getDashboards());
    }

    @GetMapping("/customer/all")
    public ResponseEntity<List<?>> getAllCustomers() {
        return ResponseEntity.ok(userService.findAllCustomers());
    }

    @PostMapping("/user")
    public ResponseEntity<?> getUserByUser(@CurrentUser UserPrincipal user) {
        return ResponseEntity.ok(userService.getUserById(user.getId()));
    }


    @GetMapping("/dashboard/{year}")
    public ResponseEntity<?> dashboardBlog(@PathVariable("year") Long year) {
        return ResponseEntity.ok(userService.dashboardGraph(year));
    }

}
