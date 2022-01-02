package com.stschools.api;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.payload.common.GraphQLRequest;
import com.stschools.service.UserService;
import com.stschools.service.graphql.GraphQLProvider;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserService userService;
    private final GraphQLProvider graphQLProvider;

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

    @PostMapping("/graphql/dashboard/order")
    public ResponseEntity<ExecutionResult> getOrdersDashboard(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard() {
        return ResponseEntity.ok(userService.getDashboards());
    }

    @GetMapping("/customer/all")
    public ResponseEntity<List<?>> getAllCustomers() {
        return ResponseEntity.ok(userService.findAllCustomers());
    }

    @PostMapping("/graphql/user")
    public ResponseEntity<ExecutionResult> getUserByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @GetMapping("/graphql/user/all")
    public ResponseEntity<ExecutionResult> getAllUsersByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @GetMapping("/dashboard/{year}")
    public ResponseEntity<?> dashboardBlog(@PathVariable("year") Long year) {
        return ResponseEntity.ok(userService.dashboardGraph(year));
    }

}
