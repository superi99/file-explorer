package com.fileexpore.service;

import com.fileexpore.model.FileSys;
import com.fileexpore.model.Folder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileService {

    public Folder getFiles(String directory) {
        File rootFolder = new File(directory);
        Folder root = new Folder(rootFolder);
        if (rootFolder.listFiles() != null) {
            for (final File fileEntry : Objects.requireNonNull(rootFolder.listFiles())) {
                if (fileEntry.isDirectory()) {
                    root.getChildren().add(getFiles(fileEntry.getAbsolutePath()));
                    System.out.println("add folder: " + fileEntry.getName());
                } else {
                    System.out.println("add file: " + fileEntry.getName());
                    root.getFileList().add(new FileSys(fileEntry.getName(), fileEntry.getAbsolutePath()));
                }
            }
        }

        return root;
    }

    public boolean deleteDir(String dir) {
        try {
            File file = new File(dir);
            if (!file.exists()) {
                throw new IOException("file " + dir + " doesn't exist!");
            }
            return file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean renameFile(String file, String newName) {
        try {
            String newPath = newName + "." + FilenameUtils.getExtension(file);
            Path source = Paths.get(file);
            if (Files.exists(source)) {
                Files.move(source, source.resolveSibling(newPath));

                if(Files.exists(Paths.get(newPath))){
                    return true;
                }
            } else {
                throw new IOException("path doesn't exist!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createFile(String path) {
        try {
            File myFile = new File(path);
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
                return true;
            }
            throw new IOException("Can't not create file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
