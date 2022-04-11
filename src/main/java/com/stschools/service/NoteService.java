package com.stschools.service;

import com.stschools.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    List<NoteDTO> getAll();
    NoteDTO addNote(NoteDTO noteDTO);
    void deleteNote(Long id);
    List<NoteDTO> getNotes(Long courseId, Long userId);
}
