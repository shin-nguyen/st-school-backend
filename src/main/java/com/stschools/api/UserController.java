package com.stschools.api;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.BlogDTO;
import com.stschools.dto.UserDTO;
import com.stschools.entity.User;
import com.stschools.exception.InputFieldException;
import com.stschools.export_file.users.UserCsvExporter;
import com.stschools.export_file.users.UserExcelExporter;
import com.stschools.export_file.users.UserPdfExporter;
import com.stschools.payload.blog.BlogRequest;
import com.stschools.payload.common.GraphQLRequest;
import com.stschools.payload.user.UserFlutterReponse;
import com.stschools.payload.user.UserRequest;
import com.stschools.repository.UserRepository;
import com.stschools.security.CurrentUser;
import com.stschools.security.UserPrincipal;
import com.stschools.service.UserService;
import com.stschools.service.graphql.GraphQLProvider;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final GraphQLProvider graphQLProvider;

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(@CurrentUser UserPrincipal user) {
        return ResponseEntity.ok(userService.findUserByEmail(user.getEmail()));
    }

    @PostMapping("/graphql/info")
    public ResponseEntity<ExecutionResult> getUserInfoByQuery(@RequestBody GraphQLRequest request){
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }
    @PutMapping("/edit")
    public ResponseEntity<?> updateUserInfo(@CurrentUser UserPrincipal user,
                                                       @Valid @RequestBody UserDTO request,
                                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(userService.updateProfile(user.getEmail(), request));
        }
    }

    @PostMapping(value ="/edit-image-and-info")
    public ResponseEntity<UserFlutterReponse> registerPost(@ModelAttribute UserRequest userRequest,
                                                           @CurrentUser UserPrincipal user,
                                                           BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(userService.updateImageAndInfo(userRequest, user.getId()));
        }
    }


    @PostMapping("/add-image")
    public ResponseEntity<?> addUserImage(@CurrentUser UserPrincipal user,
                                          @RequestParam MultipartFile file
                                          ) throws IOException {
        return ResponseEntity.ok(userService.updateImage(user.getEmail(), file));
    }

    @GetMapping(path = "export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        List<User> listUsers = userRepository.findAll();
        UserExcelExporter exporter = new UserExcelExporter();
        exporter.export(listUsers, response);
    }

    @GetMapping("/export/csv")
    public void exportToCSV( HttpServletResponse response) throws IOException {
        List<User> listUsers = userRepository.findAll();
        UserCsvExporter exporter = new UserCsvExporter();

        exporter.export(listUsers, response);
    }

    @GetMapping("/export/pdf")
    public void exportToPDF( HttpServletResponse response) throws IOException {
        List<User> listUsers = userRepository.findAll();
        UserPdfExporter exporter = new UserPdfExporter();
        exporter.export(listUsers, response);
    }
}
