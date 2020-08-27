package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/note")
    public String postNoteView(@AuthenticationPrincipal User currentUser,
                               @ModelAttribute("note") Note note, Authentication authentication,
                               RedirectAttributes redirectAttributes, Model model){
        try{
            if(note.getNoteId()!=null){
                noteService.updateNote(note);
            }else {
                note.setUserid(currentUser.getUserId());
                noteService.createNote(note);
            }

            return "redirect:/result?success";
        }catch (Exception e){
            redirectAttributes.addAttribute("errorMessage", e.getCause().getMessage());
            return "redirect:/result?error";
        }

    }

    @PostMapping("/note/{noteId}/delete")
    public String deleteNoteView(@PathVariable String noteId){
        noteService.deleteNote(Integer.parseInt(noteId));
        return "redirect:/home";
    }
}
