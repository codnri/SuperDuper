package com.udacity.jwdnd.course1.cloudstorage.controller;
import java.io.*;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Controller
@RequestMapping
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/download/{fileId}")
    public void  downloadFile(@PathVariable int fileId,
                              HttpServletResponse response, Model model)  {
        File file = fileService.getFile(fileId);
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getFileName() + "\"");
        try {
            OutputStream out = response.getOutputStream();
            response.setContentType(file.getContentType());
            IOUtils.copy(new ByteArrayInputStream(file.getFileData()), out);
            out.flush();
            out.close();
        }catch (IOException e){
            model.addAttribute("errorMessage",e.getCause().getMessage());
        }
    }

    @PostMapping("/file")
    public String postFile(@AuthenticationPrincipal User user , @RequestParam("fileUpload") MultipartFile multipartFile, RedirectAttributes redirectAttributes){
        try{
            String filename = multipartFile.getOriginalFilename();
            if(StringUtils.isEmpty(filename)){
                redirectAttributes.addAttribute("errorMessage","File is not selected");
                return "redirect:/result";
            }
            if(!fileService.isFileNameAvailable(filename)){
                redirectAttributes.addAttribute("errorMessage","File Name "+filename +" is duplicated.");
                return "redirect:/result";
            }
            fileService.createFile(multipartFile,user.getUserId());
        }catch (Exception e){
            redirectAttributes.addAttribute("errorMessage",e.getCause().getMessage());
            return "redirect:/result?error";
        }

        return "redirect:/result?success";
    }

    @PostMapping("/file/{fileId}/delete")
    public String deleteFile(@PathVariable Integer fileId){
        fileService.deleteFile(fileId);
        return "/home";
    }

}
