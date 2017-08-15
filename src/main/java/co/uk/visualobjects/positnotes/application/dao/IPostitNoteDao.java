package co.uk.visualobjects.positnotes.application.dao;

import co.uk.visualobjects.positnotes.application.model.PostItNote;
import co.uk.visualobjects.positnotes.application.service.request.PostItNoteListRequest;

import java.util.List;

/**
 * Generic interface for DAO's associated with retrieving PostitNotes
 */
public interface IPostitNoteDao {

    /**
     * Find the PostItNote given its Id
     * @param noteId the id of the note to find
     * @return the PostItNote given it's Id or null if not found.
     */
    PostItNote getPostItNoteById(long noteId);

    /**
     * Find all the PostItNotes on the database
     * @param postItNotesListRequest the request to retrive a list of PostItNotes
     * @return Vendor contacts
     */
    List<PostItNote> findPostItNotes(PostItNoteListRequest postItNotesListRequest);

    /**
     * Create a PostItNote
     * @param postItNote A PostItNote
     * @return The PostItNote that was created together with its with generated id
     */
    PostItNote createPostItNote(PostItNote postItNote);

    /**
     * Update an existing PostItNote
     * @param postItNote Existing postItNote with its id
     * @return Updated PostItNote
     */
    PostItNote updatePostItNote(PostItNote postItNote);


    /**
     *  Delete the given PostItNote From the Database
     * @param postItNote
     * @return boolean true if PostItNote was deleted false otherwise
     */
    boolean deletePostItNote(PostItNote postItNote);
}

