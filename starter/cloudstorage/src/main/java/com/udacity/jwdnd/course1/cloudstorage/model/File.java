package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {
//    fileId INT PRIMARY KEY auto_increment,
//    filename VARCHAR,
//    contenttype VARCHAR,
//    filesize VARCHAR,
//    userid INT,
//    filedata BLOB,
//    foreign key (userid) references USERS(userid)
    private Integer fileId;
    private  String fileName;
    private String contentType;
    private  String fileSize;
    private  byte[] fileData;
    private  Integer userId;

// This constructor caused "org.h2.jdbc.JdbcSQLDataException: Data conversion error converting" error
//    public File(Integer fileId, String fileName, String contentType, String fileSize, byte[] fileData, Integer userId) {
//        this.fileId = fileId;
//        this.fileName = fileName;
//        this.contentType = contentType;
//        this.fileSize = fileSize;
//        this.fileData = fileData;
//        this.userId = userId;
//    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
