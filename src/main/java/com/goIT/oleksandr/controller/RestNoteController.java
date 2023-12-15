package com.goIT.oleksandr.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goIT.oleksandr.entities.Note;
import com.goIT.oleksandr.services.NoteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/note/")
@RequiredArgsConstructor
public final class RestNoteController {
	private final NoteService noteService;
	
	@GetMapping("/list")
	public List<Note> getAllNotes(){
		return noteService.listAll();
	}
	
	@GetMapping("/id/{id}")
	public Note getNoteById(final @PathVariable Long id) {
		return noteService.getById(id);
	}
	
	@PostMapping("/create")
	public void createNote(final @RequestBody Note note) {
		noteService.add(note);
	}
	@PostMapping("/delete/{id}")
	public void deleteNoteById(final @PathVariable Long id) {
		noteService.deletedById(id);
	}
	
	@GetMapping("/edit/{id}")
	public Note editNoteById(final @PathVariable Long id) {
		return noteService.getById(id);
	}
	@PostMapping("/edit/{id}")
    public void postEditNoteById(final @PathVariable Long id, final @RequestBody Note updatedNote) {
        Note existingNote = noteService.getById(id);
        existingNote.setTitle(updatedNote.getTitle());
        existingNote.setContent(updatedNote.getContent());

        noteService.add(existingNote);
    }

}
