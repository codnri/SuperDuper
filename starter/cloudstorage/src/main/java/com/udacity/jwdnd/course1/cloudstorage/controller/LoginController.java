package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    //delete this constructor later
    public LoginController(UserService userService, NoteService noteService, CredentialService credentialService, FileMapper fileMapper){
        User user = new User(null,"hoge",null,"test","John","Doe");
        userService.createUser(user);
        noteService.createNote(new Note(null,"mytitle","mydesc",1));
        credentialService.createCredential(new Credential(null,"http://hogehoge.com","userX",null,"password",1));

       // fileMapper.insertFile(new File(null,"fileName","text/plain","0","test".getBytes(),1));
    }
    @GetMapping()
    public String getLogin(){
        return "login";
    }


}



