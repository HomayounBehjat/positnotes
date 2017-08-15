package co.uk.visualobjects.positnotes.application.controller;

import co.uk.visualobjects.positnotes.application.dao.IPostitNoteDao;
import co.uk.visualobjects.positnotes.application.model.PostItNote;
import co.uk.visualobjects.positnotes.application.service.request.PostItNoteListRequest;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PostitNoteController {

    @Autowired
    private IPostitNoteDao postitNoteDao;

    private final static Logger LOG = LoggerFactory.getLogger(PostitNoteController.class);

    /**
     * Retrieves a single potitnote
     * @param noteId the id of the note to return
     * @return The PostitNote for the id
     */
    @GetMapping("/postitnotes/{noteId}")
    public PostItNote getPostItNoteById(@PathVariable(value="noteId") final long noteId) {
       return postitNoteDao.getPostItNoteById(noteId);
    }

    /**
     * Retrieves All the postit notes on the database
     * @param postItNoteListRequest
     * @param bindingResult
     * @return
     */
    @GetMapping("/postitnotes")
    public List<PostItNote> getPostItNotes(@Valid PostItNoteListRequest postItNoteListRequest, BindingResult bindingResult) {
        Preconditions.checkNotNull(postItNoteListRequest, "postItNoteListRequest is null. Processing cannot continue");
        if (bindingResult.hasErrors()) {
            // Model not valid
            LOG.error("Create vendor failed validation");
            throw new RuntimeException(bindingResult.getAllErrors().toString());
        }

        return postitNoteDao.findPostItNotes(postItNoteListRequest);
    }

    @PostMapping("/positnotes/postitnote")
    @Transactional
    public PostItNote createPostItNote(@RequestBody final PostItNote postItNote) {

        Preconditions.checkNotNull(postItNote, "Null postItNote");

        if(postItNote == null) {
            LOG.warn("createPostItNote: Could not find PostItNote to persist ");
            throw new ResourceNotFoundException();
        }

        return postitNoteDao.createPostItNote(postItNote);

    }

    @PutMapping("/postitnotes/postitnote/{noteId}")
    @Transactional
    public PostItNote updatePostItNote(@PathVariable(value="noteId") final long noteId,
                                   @RequestBody final PostItNote postItNote) {

        Preconditions.checkNotNull(postItNote, "Null postItNote");

        if(postItNote == null) {
            LOG.warn("updatePostItNote: Could not find PostItNote details for note ID: {} ");
            throw new ResourceNotFoundException();
        }

        return postitNoteDao.updatePostItNote(postItNote);
    }

    @DeleteMapping("/postitnotes/postitnote/{noteId}")
    @Transactional
    public void deletePostItNote(@PathVariable(value="noteId") final Long noteId) {

        Preconditions.checkNotNull(noteId, "Null postItNote");

        PostItNote postItNote = new PostItNote();
        postItNote.setNoteId(noteId);

        postitNoteDao.deletePostItNote(postItNote);
    }
}
