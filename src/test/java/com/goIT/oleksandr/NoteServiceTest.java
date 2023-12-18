package com.goIT.oleksandr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.goIT.oleksandr.entities.Note;
import com.goIT.oleksandr.repository.NoteRepository;
import com.goIT.oleksandr.services.NoteService;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @BeforeEach
    public void setUp() {
        noteService = new NoteService(noteRepository);
    }

    @Test
    public void testListAll() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1L, "Title 1", "Content 1"));
        notes.add(new Note(2L, "Title 2", "Content 2"));

        when(noteRepository.findAll()).thenReturn(notes);

        assertEquals(notes, noteService.listAll());
    }

    @Test
    public void testAdd() {
        Note note = new Note(1L, "Title 1", "Content 1");

        when(noteRepository.save(note)).thenReturn(note);

        assertEquals(note, noteService.add(note));
    }

    @Test
    public void testDeletedById() {
        Long id = 1L;

        when(noteRepository.existsById(id)).thenReturn(true);

        noteService.deletedById(id);
    }

    @Test
    public void testUpdate() {
        Note note = new Note(1L, "Title 1", "Content 1");

        when(noteRepository.existsById(note.getId())).thenReturn(true);
        when(noteRepository.save(note)).thenReturn(note);

        noteService.update(note);
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        Note note = new Note(id, "Title 1", "Content 1");

        when(noteRepository.findById(id)).thenReturn(java.util.Optional.of(note));

        assertEquals(note, noteService.getById(id));
    }

    @Test
    public void testGetByTitle() {
        String searchedTitle = "Title 1";
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1L, searchedTitle, "Content 1"));

        when(noteRepository.findNotesByTitle(searchedTitle)).thenReturn(notes);

        assertEquals(notes, noteService.getByTitle(searchedTitle));
    }
}
