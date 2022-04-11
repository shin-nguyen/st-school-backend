package com.stschools.dto;
import com.stschools.payload.quiz.QuizReponse;
import lombok.*;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class RecordDTO {
    private Long id;
    private QuizReponse quiz;
    private Double score;
    private String jsonQuiz;
}
