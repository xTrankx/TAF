package com.playtika.automation.school.test.framework.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.playtika.automation.school.test.framework.demo.configuration.AuthenticatedFeignClientConfiguration;
import com.playtika.automation.school.test.framework.demo.pojo.Note;

@FeignClient(
        name = "authenticated-client",
        url = "${host.baseurl}",
        path = "/v1/notes",
        configuration = AuthenticatedFeignClientConfiguration.class
)
public interface AuthenticatedFeignClient {

    @GetMapping
    List<Note> getNotes();

    @PostMapping
    Note createNote(Note note);

    @GetMapping(path = "/{noteId}")
    Note getNoteById(@PathVariable("noteId") Integer noteId);

    @PutMapping(path = "/{noteId}")
    Note updateExistingNote(@PathVariable Integer noteId, Note note);

    @DeleteMapping(path = "/{noteId}")
    void deleteNoteById(@PathVariable Integer noteId);

}
