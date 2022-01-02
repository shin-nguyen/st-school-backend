package com.stschools.api;


import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.QuestionDTO;
import com.stschools.dto.QuizDTO;
import com.stschools.exception.InputFieldException;
import com.stschools.payload.blog.BlogRequest;
import com.stschools.security.CurrentUser;
import com.stschools.security.UserPrincipal;
import com.stschools.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quizzes")
public class QuizController {
    private final QuizService quizService;

    @GetMapping("/all")
    public ResponseEntity<List<?>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllQuizzes(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(quizService.getQuiz(id));
    }

    @PostMapping(value ="/add")
    public ResponseEntity<?> registerQuiz(@RequestBody QuizDTO request,
                                                @CurrentUser UserPrincipal user,
                                                BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(quizService.add(request, user.getId()));
        }
    }



    @DeleteMapping("/delete/{quizId}")
    public ResponseEntity<Long> deleteQuiz(@PathVariable(value = "quizId") Long quizId) {
        return ResponseEntity.ok(quizService.delete(quizId));
    }
//    @GetMapping(path = "export/excel")
//    public void exportToExcel(HttpServletResponse response) throws IOException {
//        List<User> listUsers = userService.findAllUsers();
//        UserExcelExporter exporter = new UserExcelExporter();
//        exporter.export(listUsers, response);
//    }
//
//    @GetMapping("/export/csv")
//    public void exportToCSV( HttpServletResponse response) throws IOException {
//        List<User> listUsers = userService.findAllUsers();
//        UserCsvExporter exporter = new UserCsvExporter();
//
//        exporter.export(listUsers, response);
//    }
//
//    @GetMapping("/export/pdf")
//    public void exportToPDF( HttpServletResponse response) throws IOException {
//        List<User> listUsers = userService.findAllUsers();
//        UserPdfExporter exporter = new UserPdfExporter();
//        exporter.export(listUsers, response);
//    }
}