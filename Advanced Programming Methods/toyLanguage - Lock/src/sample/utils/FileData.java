package sample.utils;

import java.io.BufferedReader;

public class FileData {
    private String fileName;
    private BufferedReader fileDescriptor;
    public FileData(String fileName, BufferedReader fd){
        this.fileName=fileName;
        this.fileDescriptor=fd;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public BufferedReader getFileDescriptor() {
        return fileDescriptor;
    }

    public void setFileDescriptor(BufferedReader fileDescriptor) {
        this.fileDescriptor = fileDescriptor;
    }

    @Override
    public String toString(){
        return fileName;
    }
}
