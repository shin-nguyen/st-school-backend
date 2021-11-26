package com.stschools.api;

import com.stschools.dto.UserDTO;
import com.stschools.exception.InputFieldException;
import com.stschools.mapper.UserMapper;
import com.stschools.payload.common.GraphQLRequest;
import com.stschools.security.CurrentUser;
import com.stschools.security.UserPrincipal;
import com.stschools.service.UserService;
import com.stschools.service.graphql.GraphQLProvider;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    private final GraphQLProvider graphQLProvider;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(@CurrentUser UserPrincipal user) {
        return ResponseEntity.ok(userMapper.findUserByEmail(user.getEmail()));
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
            return ResponseEntity.ok(userMapper.updateProfile(user.getEmail(), request));
        }
    }

    @PostMapping("/add-image")
    public ResponseEntity<?> addUserImage(@CurrentUser UserPrincipal user,
                                          @RequestParam MultipartFile file
                                          ) throws IOException {
        return ResponseEntity.ok(userService.updateImage(user.getEmail(), file));
    }

}
