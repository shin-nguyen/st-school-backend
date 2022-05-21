package com.stschools.repository;

import com.stschools.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByCourseIdAndUserId(Long courseId, Long userId);
    Note findNoteById(Long id);
}
