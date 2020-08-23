package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    //    noteid INT PRIMARY KEY auto_increment,
//    notetitle VARCHAR(20),
//    notedescription VARCHAR (1000),
//    userid INT,
//    foreign key (userid) references USERS(userid)
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    //    private Integer userId;
    private Integer userId;

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public Integer getUserid() {
        return userId;
    }

    public void setUserid(Integer userId) {
        this.userId = userId;
    }

    public Note(Integer noteId, String noteTitle, String noteDescription, Integer userId) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userId = userId;
    }
}
