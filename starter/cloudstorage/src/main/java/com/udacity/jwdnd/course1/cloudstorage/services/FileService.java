package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    private FileMapper fileMapper;
    public FileService(FileMapper fileMapper){
        this.fileMapper = fileMapper;
    }

    public File getFile(Integer fileId){
        return fileMapper.getFile(fileId);
    }

    public List<File> getFilesByUserId(Integer userId){
        return fileMapper.getFilesByUserId(userId);
    }

    public int createFile(MultipartFile multipartFile,Integer userId) throws IOException {
        try {
            File file = new File();
            file.setFileName(multipartFile.getOriginalFilename());
            file.setContentType(multipartFile.getContentType());
            file.setFileSize(Long.toString(multipartFile.getSize()));
            file.setFileData(multipartFile.getBytes());
            file.setUserId(userId);
            return fileMapper.insertFile(file);
        }catch (IOException e){
            throw new IOException("Exception while file saving",e);
        }
    }


    public int deleteFile(Integer fileId){
        return fileMapper.deleteFile(fileId);
    }

    public boolean isFileNameAvailable(String filename){
        return fileMapper.getFileByName(filename) == null;
    }


}
