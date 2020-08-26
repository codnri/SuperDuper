package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping
public class HomeController {

    private final NoteService noteService;
    private final FileService fileService;
    private final CredentialService credentialService;
    private final UserService userService;
    public HomeController(NoteService noteService, FileService fileService, CredentialService credentialService, UserService userService){
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.userService = userService;

    }

    @GetMapping("/home")
    public String getHomeView(@AuthenticationPrincipal User currentUser,
                              @ModelAttribute("note") Note note, Model model){
        model.addAttribute("notes", this.noteService.getNotesByUserId(currentUser.getUserId()));
        model.addAttribute("credentials",this.credentialService.getCredentialByUserId(currentUser.getUserId()));
        model.addAttribute("files",this.fileService.getFilesByUserId(currentUser.getUserId()));
        return "home";
    }

    @GetMapping("/result")
    public String getResult(){
        return "result";
    }



}
