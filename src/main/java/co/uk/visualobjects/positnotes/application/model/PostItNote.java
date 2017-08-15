package co.uk.visualobjects.positnotes.application.model;

import java.util.Date;

public class PostItNote {

    public Date getNoteCreatedDate() {
        return noteCreateDate;
    }

    public void setNoteCreateDate(Date noteCreateDate) {
        this.noteCreateDate = noteCreateDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    private Date noteCreateDate;
    private Date updateDate;
    private String noteTitle;
    private long noteId;
    private String noteText;
}
