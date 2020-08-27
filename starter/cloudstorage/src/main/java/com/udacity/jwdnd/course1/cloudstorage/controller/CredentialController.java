package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class CredentialController {
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;

    public CredentialController(CredentialService credentialService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @PostMapping("/credential")
    public String postCredentialView(@AuthenticationPrincipal User currentUser, @ModelAttribute Credential credential, RedirectAttributes redirectAttributes){

        try{
            if(credential.getCredentialId()!=null){
                //update
                credentialService.updateCredential(credential);
            }else{
                //create
                credential.setUserId(currentUser.getUserId());
                credentialService.createCredential(credential);
            }

            return "redirect:/result?success";
        }catch (Exception e){
            redirectAttributes.addAttribute("errorMessage",e.getCause().getMessage());
            return "redirect:/result?error";
        }
    }

    @PostMapping("/credential/{credentialId}/delete")
    public String deleteCredential(@PathVariable Integer credentialId){
        credentialService.deleteCredential(credentialId);
        return "redirect:/home";
    }

}
