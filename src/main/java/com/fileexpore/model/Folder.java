package com.fileexpore.model;

import java.util.ArrayList;
import java.util.List;

public class Folder {

    private String folderName;
    private String path;
    private List<Folder> children;
    private List<FileSys> fileSysList;

    public Folder(java.io.File dir) {
        children = new ArrayList<>();
        fileSysList = new ArrayList<>();
        folderName = dir.getName();
        path = dir.getAbsolutePath();
    }

    public Folder(String folderName, String path, List<Folder> children, List<FileSys> fileSysList) {
        this.folderName = folderName;
        this.path = path;
        this.children = children;
        this.fileSysList = fileSysList;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Folder> getChildren() {
        return children;
    }

    public void setChildren(List<Folder> children) {
        this.children = children;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public List<FileSys> getFileList() {
        return fileSysList;
    }

    public void setFileList(List<FileSys> fileSysList) {
        this.fileSysList = fileSysList;
    }
}
