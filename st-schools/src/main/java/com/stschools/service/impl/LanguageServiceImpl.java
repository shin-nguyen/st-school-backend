package com.stschools.service.impl;

import com.stschools.dto.LanguageDTO;
import com.stschools.entity.Language;
import com.stschools.repository.LanguageRepository;
import com.stschools.service.LanguageService;
import com.stschools.util.ModelMapperControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired
    LanguageRepository languageRepository;

    @Override
    public List<LanguageDTO> getAll() {
        List<Language> languageList = languageRepository.findAll();
        return ModelMapperControl.mapAll(languageList,LanguageDTO.class);
    }
}
