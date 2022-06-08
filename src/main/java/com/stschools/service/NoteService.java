package com.stschools.service;

import com.cloudinary.api.exceptions.ApiException;
import com.stschools.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    List<NoteDTO> getAll();

    NoteDTO addNote(NoteDTO noteDTO);

    NoteDTO updateNote(NoteDTO noteDTO) throws ApiException;

    void deleteNote(Long id);

    List<NoteDTO> getNotes(Long courseId, Long userId);
}
