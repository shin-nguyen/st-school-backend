package com.stschools.payload.activity_progress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityProgressReponse {
    private Long id;
    private double sun;
    private double mon;
    private double tue;
    private double wed;
    private double thu;
    private double sat;
    private double fri;
}
