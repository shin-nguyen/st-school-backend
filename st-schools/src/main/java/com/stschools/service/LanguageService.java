package com.stschools.service;

import com.stschools.dto.LanguageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LanguageService {
    List<LanguageDTO> getAll ();
}
