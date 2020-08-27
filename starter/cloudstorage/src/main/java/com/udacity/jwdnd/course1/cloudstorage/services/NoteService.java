package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getNotesByUserId(Integer userId){
        return this.noteMapper.getNotesByUserId(userId);
    }
    public Note getNote(Integer noteId){
        return this.noteMapper.getNote(noteId);
    }

    public int createNote(Note note){
        return  this.noteMapper.insertNote(note);
    }

    public int deleteNote(Integer noteId){
        return this.noteMapper.deleteNote(noteId);
    }
    public int updateNote(Note note){
        return this.noteMapper.updateNote(note);
    }

}
