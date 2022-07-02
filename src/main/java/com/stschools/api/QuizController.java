package com.stschools.api;


import com.stschools.dto.QuestionDTO;
import com.stschools.dto.QuizDTO;
import com.stschools.entity.Quiz;
import com.stschools.exception.InputFieldException;
import com.stschools.export_file.quizzes.QuizCsvExporter;
import com.stschools.export_file.quizzes.QuizExcelExporter;
import com.stschools.export_file.quizzes.QuizPdfExporter;
import com.stschools.repository.QuizRepository;
import com.stschools.security.CurrentUser;
import com.stschools.security.UserPrincipal;
import com.stschools.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quizzes")
public class QuizController {
    private final QuizService quizService;

    private final QuizRepository quizRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllQuizzes() {
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

    @PutMapping(value ="/update-detail")
    public ResponseEntity<?> updateDetailQuiz(@RequestBody QuizDTO request,
                                          BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(quizService.updateQuiz(request));
        }
    }

    @PostMapping(value ="/{quizId}/question")
    public ResponseEntity<?> addQuestion(@RequestBody QuestionDTO request,
                                         @PathVariable(value = "quizId") Long quizId,
                                          BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(quizService.addQuestion(request, quizId));
        }
    }

    @PutMapping(value ="/{quizId}/question/edit")
    public ResponseEntity<?> updateQuestion(@RequestBody QuestionDTO request,
                                         @PathVariable(value = "quizId") Long quizId,
                                         BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return ResponseEntity.ok(quizService.updateQuestion(request, quizId));
        }
    }


    @DeleteMapping("/delete/{quizId}")
    public ResponseEntity<Long> deleteQuiz(@PathVariable(value = "quizId") Long quizId) {
        return ResponseEntity.ok(quizService.delete(quizId));
    }

    //Error
    @DeleteMapping("/{quizId}/question/{questionId}/delete")
    public ResponseEntity<Long> deleteQuestionInQuiz(
            @PathVariable(value = "quizId") Long quizId,
            @PathVariable(value = "questionId") Long questionId) {
        return ResponseEntity.ok(quizService.deleteQuestionInQuiz(quizId,questionId));
    }


    //Error
    @PostMapping("/submit")
    public ResponseEntity<?> submitQuiz(@RequestBody QuizDTO request,@CurrentUser UserPrincipal user) {
        return ResponseEntity.ok(quizService.submitQuiz(request,user.getId()));
    }

    @GetMapping(path = "export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        List<Quiz> quizzes = quizRepository.findAll();
        QuizExcelExporter exporter = new QuizExcelExporter();
        exporter.export(quizzes, response);
    }

    @GetMapping("/export/csv")
    public void exportToCSV( HttpServletResponse response) throws IOException {
        List<Quiz> quizzes = quizRepository.findAll();
        QuizCsvExporter exporter = new QuizCsvExporter();

        exporter.export(quizzes, response);
    }

    @GetMapping("/export/pdf")
    public void exportToPDF( HttpServletResponse response) throws IOException {
        List<Quiz> quizzes = quizRepository.findAll();
        QuizPdfExporter exporter = new QuizPdfExporter();
        exporter.export(quizzes, response);
    }
}