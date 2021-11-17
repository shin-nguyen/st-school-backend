package com.stschools.api;

import com.stschools.entity.User;
import com.stschools.export_file.users.UserCsvExporter;
import com.stschools.export_file.users.UserExcelExporter;
import com.stschools.export_file.users.UserPdfExporter;
import com.stschools.mapper.UserMapper;
import com.stschools.payload.common.GraphQLRequest;
import com.stschools.service.UserService;
import com.stschools.service.graphql.GraphQLProvider;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserMapper userMapper;
    private final UserService userService;
    private final GraphQLProvider graphQLProvider;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userMapper.findUserById(userId));
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<?>> getAllUsers() {
        return ResponseEntity.ok(userMapper.findAllUsers());
    }

    @GetMapping("/customer/all")
    public ResponseEntity<List<?>> getAllCustomers() {
        return ResponseEntity.ok(userMapper.findAllCustomers());
    }

    @PostMapping("/graphql/user")
    public ResponseEntity<ExecutionResult> getUserByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @PostMapping("/graphql/user/all")
    public ResponseEntity<ExecutionResult> getAllUsersByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @GetMapping(path = "export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        List<User> listUsers = userService.findAllUsers();
        UserExcelExporter exporter = new UserExcelExporter();
        exporter.export(listUsers, response);
    }

    @GetMapping("export/csv")
    public void exportToCSV( HttpServletResponse response) throws IOException {
        List<User> listUsers = userService.findAllUsers();
        UserCsvExporter exporter = new UserCsvExporter();

        exporter.export(listUsers, response);
    }

    @GetMapping("export/pdf")
    public void exportToPDF( HttpServletResponse response) throws IOException {
        List<User> listUsers = userService.findAllUsers();
        UserPdfExporter exporter = new UserPdfExporter();
        exporter.export(listUsers, response);
    }
}
