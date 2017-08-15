package co.uk.visualobjects.positnotes.application.dao;

import co.uk.visualobjects.positnotes.application.model.PostItNote;
import co.uk.visualobjects.positnotes.application.service.request.PostItNoteListRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PositNoteDao implements IPostitNoteDao {

    private final static Logger LOG = LoggerFactory.getLogger(PositNoteDao.class);

    @Override
    public PostItNote getPostItNoteById(long noteId) {
        return null;
    }

    @Override
    public List<PostItNote> findPostItNotes(PostItNoteListRequest postItNotesListRequest) {
        return null;
    }

    @Override
    public PostItNote createPostItNote(PostItNote postItNote) {
        return null;
    }

    @Override
    public PostItNote updatePostItNote(PostItNote postItNote) {
        return null;
    }

    @Override
    public boolean deletePostItNote(PostItNote postItNote) {
        return false;
    }
}
