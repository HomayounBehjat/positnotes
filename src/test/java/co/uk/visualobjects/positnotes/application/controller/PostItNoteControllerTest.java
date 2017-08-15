package co.uk.visualobjects.positnotes.application.controller;

import co.uk.visualobjects.positnotes.application.dao.IPostitNoteDao;
import co.uk.visualobjects.positnotes.application.model.PostItNote;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.ZoneOffset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PostitNoteController.class)
public class PostItNoteControllerTest {

    private static final long NOTE_ID = 0L;
    private static final String NOTE_TEXT = "Homayoun was here To See you";
    private static final String NOTE_TITLE = "Appointments";
    private static final String NOTE_CREATE_DATE = "2016-12-25T10:11:12.000Z";


    @Autowired
    MockMvc mockMvc;

    @MockBean
    IPostitNoteDao postitNoteDao;

    @Test
    public void testGetPostItNotes_when_post_it_notes_exist() throws Exception {

        PostItNote postItNote = getTestPostItNote();
        Mockito.when(postitNoteDao.getPostItNoteById(Mockito.anyLong())).thenReturn(postItNote);

        this.mockMvc.perform(get("/postitnote/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].noteId").value(NOTE_ID))
                .andExpect(jsonPath("$[0].noteText").value(NOTE_TEXT))
                .andExpect(jsonPath("$[0].noteTitle").value(NOTE_TITLE))
                .andExpect(jsonPath("$[0].noteCreateDate").value(NOTE_CREATE_DATE));
    }

    @Test
    public void testGetPostItNote_when_PostItNote_does_not_exist() throws Exception {

        Mockito.when(postitNoteDao.getPostItNoteById(Mockito.anyLong())).thenReturn(null);

        this.mockMvc.perform(get("/postitnotes/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testCreatePostItNote() throws Exception {
        PostItNote postitNote = getTestPostItNote();

        this.mockMvc
                .perform(post("/postitnotes/postitnote")
                .content(new Gson().toJson(postitNote))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.noteId").value(NOTE_ID))
                .andExpect(jsonPath("$.noteText").value(NOTE_TEXT))
                .andExpect(jsonPath("$.noteTitle").value(NOTE_TITLE))
                .andExpect(jsonPath("$.noteCreateDate").value(NOTE_CREATE_DATE));


    }

    @Test
    public void testUpdatePostItNote() throws Exception {
        PostItNote postItNote = getTestPostItNote();
        postItNote.setNoteId(1);

        this.mockMvc
                .perform(put("/postitnotes/postitnote/1")
                .content(new Gson().toJson(postItNote))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.noteId").value(1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdatePostItNote_when_PostItNote_does_not_exists() throws Exception {
        PostItNote postItNote = getTestPostItNote();

        this.mockMvc
                .perform(put("/integration/brand/contact/1")
                        .content(new Gson().toJson(postItNote))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testDeletePostItNote() throws Exception {

        this.mockMvc
        .perform(delete("/integration/brand/contact/1"))
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeletePostItNote_when_postitnote_does_not_exist() throws Exception {
        PostItNote postitNote = getTestPostItNote();

        Mockito.when(postitNoteDao.getPostItNoteById(Mockito.anyLong())).thenReturn(null);

        this.mockMvc
        .perform(delete("/postitnotes/postitnote/98"))
        .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    private PostItNote getTestPostItNote() {
        PostItNote postItNote = new PostItNote();
        postItNote.setNoteId(0);
        postItNote.setNoteText("Homayoun was here To See you");
        postItNote.setNoteTitle("Appointments");
        postItNote.setNoteCreateDate(java.sql.Date.from(LocalDate.parse("2016-12-25").atTime(10,11,12).toInstant(ZoneOffset.UTC)));

        return postItNote;
    }
}
