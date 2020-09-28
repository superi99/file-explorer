package com.fileexpore.controller;

import com.fileexpore.model.Folder;
import com.fileexpore.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;


    @RequestMapping("/root")
    public Folder getFolder(@RequestParam String directory) {
        return fileService.getFiles(directory);

    }

    // tạo file mới
    @RequestMapping("/create")
    public String create(@RequestParam String folderPath, @RequestParam String name) {
        if (fileService.createFile(folderPath + "\\" + name)) {
            return "created!";
        }

        return "can create file name: " + name;
    }

    //đổi tên file
    @RequestMapping("/rename")
    public String rename(@RequestParam String filePath, @RequestParam String newName) {
        if (fileService.renameFile(filePath, newName)) {
            return "renamed";
        }
        return "can't rename file: " + filePath;
    }

    // xóa file
    @RequestMapping("/delete")
    public String deleteDir(@RequestParam String dir) {
        if (fileService.deleteDir(dir)) {
            return "deleted!";
        }
        return "can't delete file: " + dir;
    }

}
