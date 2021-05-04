package com.playtika.automation.school.test.framework.demo;

import java.util.List;

import feign.FeignException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.playtika.automation.school.test.framework.demo.action.AuthenticatedFeignClientActions;
import com.playtika.automation.school.test.framework.demo.action.ServiceFeignClientActions;
import com.playtika.automation.school.test.framework.demo.configuration.AuthenticatedFeignClientActionsConfiguration;
import com.playtika.automation.school.test.framework.demo.configuration.ServiceFeignClientActionsConfiguration;
import com.playtika.automation.school.test.framework.demo.configuration.UserConfiguration;
import com.playtika.automation.school.test.framework.demo.pojo.Note;
import com.playtika.automation.school.test.framework.demo.pojo.RegistrationResponse;
import com.playtika.automation.school.test.framework.demo.pojo.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootApplication
@EnableFeignClients
@SpringBootTest(classes = {
        UserConfiguration.class,
        ServiceFeignClientActionsConfiguration.class,
        AuthenticatedFeignClientActionsConfiguration.class
})
@RunWith(JUnitPlatform.class)
class DemoApplicationTests {

    public static Integer noteCounter = 0;
    //1. Generate new user
    @Autowired
    private User user;

    @Autowired
    private ServiceFeignClientActions serviceFeignClientActions;

    @Autowired
    private AuthenticatedFeignClientActions authenticatedFeignClientActions;

    @Test
    void testingServiceTask() {
        //2. Register user
        RegistrationResponse response = serviceFeignClientActions.registerUserAccount(user);
        user.setId(response.getId());
        user.setRegisteredAt(response.getRegisteredAt());
        showUser();
        checkUserRegistration(response);

        //4. Create a note with any content
        Note note1 = authenticatedFeignClientActions.createNote();
        noteCounter++;

        //5. Get list of notes and assert it has size equals to one
        List<Note> noteList = authenticatedFeignClientActions.getNotes();
        assertThat(noteList).hasSize(1);
        Integer listSize = noteList.size();
        assertEquals(noteCounter, listSize,"Notes list length is not correct, you didn't create note");
        System.out.println("Notes list after creating first note");
        showNotes(noteList);

        //6. Create second note
        Note note2 = authenticatedFeignClientActions.createNote();
        noteCounter++;

        //7. Get list of notes and assert it size has grown
        List<Note> noteListAfterCreatingSecondNote = authenticatedFeignClientActions.getNotes();
        assertThat(noteListAfterCreatingSecondNote).hasSizeGreaterThan(noteList.size());
        System.out.println("Notes list after creating second note");
        showNotes(noteListAfterCreatingSecondNote);

        //8. Get first note by id and assert it's the same as you've created
        Note firstNoteToCompare = authenticatedFeignClientActions.getNote(note1);
        checkNotesTheSame(note1, firstNoteToCompare);

        //9. Update first note with any new content
        Note updatedNote1 = authenticatedFeignClientActions.updateExistingNote(note1);

        //10. Get list of notes. Use stream to filter list by id of note and get updated one.
        Note foundedNote = searchNoteInListById(updatedNote1);

        //11. Check that updated note has the same id as first note
        checkModifiedNote(note1, foundedNote);

        //12. Delete first note
        authenticatedFeignClientActions.deleteNote(note1);
        noteCounter--;

        //13. Get list of notes and assert it has size equal to one and it doesn't contain deleted note
        checkNoteListDidNotContainDeletedNote(note1);

        //14. Try to get deleted note and assert that method throws error which contains message:
        // "Note with id [{your note id}] wasn't found"
        checkGetDeletedNote(note1);

        //15. Delete second note
        authenticatedFeignClientActions.deleteNote(note2);
        noteCounter--;
        checkNoteListDidNotContainDeletedNote(note2);
        checkGetDeletedNote(note2);

    }

    private void showUser() {
        System.out.println("User email: " + user.getEmail());
        System.out.println("User password: " + user.getPassword());
        System.out.println("User id: " + user.getId());
        System.out.println("User registeredAt: " + user.getRegisteredAt());
        System.out.println();
    }

    private void checkUserRegistration(RegistrationResponse response) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.getEmail()).isEqualTo(user.getEmail());
        softAssertions.assertThat(response.getId()).isPositive();
        softAssertions.assertThat(response.getRegisteredAt()).isNotBlank();
        softAssertions.assertAll();
    }

    private void showNotes(List<Note> noteList) {
        for (Note note : noteList
        ) {
            System.out.printf("id: %d\n", note.getId());
            System.out.printf("content: %s\n", note.getContent());
            System.out.printf("createdAt: %s\n", note.getCreatedAt());
            System.out.printf("modifiedAt: %s\n", note.getModifiedAt());
            System.out.printf("version: %d\n", note.getVersion());
            System.out.println();
        }
    }

    private void checkNotesTheSame(Note note1, Note note2) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(note1.getId()).isEqualTo(note2.getId());
        softAssertions.assertThat(note1.getContent()).isEqualTo(note2.getContent());
        softAssertions.assertThat(note1.getCreatedAt()).isEqualTo(note2.getCreatedAt());
        softAssertions.assertThat(note1.getModifiedAt()).isEqualTo(note2.getModifiedAt());
        softAssertions.assertThat(note1.getVersion()).isEqualTo(note2.getVersion());
        softAssertions.assertAll();

    }

    private void checkModifiedNote(Note originalNote, Note modifiedNote) {
        SoftAssertions softAssertions = new SoftAssertions();
        //Check that update note has the same id as first note
        softAssertions.assertThat(modifiedNote.getId()).isEqualTo(originalNote.getId());
        //Check that creation date is equal to first note creation date
        softAssertions.assertThat(modifiedNote.getCreatedAt()).isEqualTo(originalNote.getCreatedAt());
        //Check that note version increased
        softAssertions.assertThat(modifiedNote.getVersion()).isGreaterThan(originalNote.getVersion());
        //Check that content updated
        softAssertions.assertThat(modifiedNote.getContent()).isNotEqualTo(originalNote.getContent());
        //Check that modification date updated
        softAssertions.assertThat(modifiedNote.getModifiedAt()).isNotEqualTo(originalNote.getModifiedAt());
        softAssertions.assertAll();
    }

    private Note searchNoteInListById(Note targetNote) {
        List<Note> noteList = authenticatedFeignClientActions.getNotes();
        return noteList.stream()
                       .filter(note -> note.getId() != null && note.getId().equals(targetNote.getId()))
                       .findFirst()
                       .orElseThrow(() -> new RuntimeException("Note not found"));
    }

    private void checkNoteListDidNotContainDeletedNote(Note deletedNote) {
        List<Note> noteList = authenticatedFeignClientActions.getNotes();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(noteList).hasSize(noteCounter);
        softAssertions.assertThat(noteList).noneMatch(note -> note.getId().equals(deletedNote.getId()));
        softAssertions.assertThat(noteList.stream().filter(note -> note.getId().equals(deletedNote.getId()))).isEmpty();
        softAssertions.assertAll();
    }

    private void checkGetDeletedNote(Note deletedNote) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThatThrownBy(() -> authenticatedFeignClientActions.getNote(deletedNote))
                      .isInstanceOf(FeignException.FeignClientException.class)
                      .hasMessageContaining(String.format("Note with id [%d] wasn't found", deletedNote.getId()));
        softAssertions.assertAll();
    }
}
