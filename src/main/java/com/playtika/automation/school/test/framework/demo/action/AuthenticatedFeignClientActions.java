package com.playtika.automation.school.test.framework.demo.action;

import java.util.List;

import lombok.AllArgsConstructor;

import com.playtika.automation.school.test.framework.demo.client.AuthenticatedFeignClient;
import com.playtika.automation.school.test.framework.demo.pojo.Note;

@AllArgsConstructor
public class AuthenticatedFeignClientActions {

    private final AuthenticatedFeignClient authenticatedFeignClient;

    public List<Note> getNotes() {
        return authenticatedFeignClient.getNotes();
    }

    public Note createNote() {
        return authenticatedFeignClient.createNote(new Note());
    }

    public Note getNote(Note note) {
        return authenticatedFeignClient.getNoteById(note.getId());
    }

    public Note updateExistingNote(Note note) {
        return authenticatedFeignClient.updateExistingNote(note.getId(), noteContentUpdate(note));
    }

    public void deleteNote(Note note) {
        authenticatedFeignClient.deleteNoteById(note.getId());
    }

    private Note noteContentUpdate(Note note) {
        Note updatedContentNote = new Note();
        updatedContentNote.setId(note.getId());
        updatedContentNote.setCreatedAt(note.getCreatedAt());
        updatedContentNote.setModifiedAt(note.getModifiedAt());
        updatedContentNote.setVersion(note.getVersion());
        return updatedContentNote;
    }
}
