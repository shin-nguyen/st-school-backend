package com.stschools.service.impl;

import com.stschools.dto.NoteDTO;
import com.stschools.entity.Note;
import com.stschools.repository.NoteRepository;
import com.stschools.service.NoteService;
import com.stschools.util.ModelMapperControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    NoteRepository noteRepository;

    @Override
    public List<NoteDTO> getAll() {
        return ModelMapperControl.mapAll(noteRepository.findAll(), NoteDTO.class);
    }

    @Override
    public NoteDTO addNote(NoteDTO noteDTO) {
        noteRepository.save(ModelMapperControl.map(noteDTO, Note.class));
        return noteDTO;
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public List<NoteDTO> getNotes(Long courseId, Long userId) {
        return ModelMapperControl.mapAll(noteRepository.findByCourseIdAndUserId(courseId, userId), NoteDTO.class);
    }
}
