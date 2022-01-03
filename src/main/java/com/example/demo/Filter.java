//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class Filter {
    public Filter() {
    }

    public static List<File> getFilesFromParts(HttpServletRequest request) {
        ArrayList files = new ArrayList();
        try {
            List<Part> fileParts = request.getParts().stream().filter((part) -> {
                return "files".equals(part.getName());
            }).toList();
            System.out.println("Found " + fileParts.size() + " files:");
            for (Part filePart : fileParts){
                String imgName = Formatter.ISOtoUTF(Paths.get(filePart.getSubmittedFileName()).getFileName().toString());
                InputStream fileContent = filePart.getInputStream();
                File targetFile = new File("D:\\TEST\\" + filePart.getSubmittedFileName());
                System.out.println("\t" + imgName);
                Files.copy(fileContent, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                files.add(targetFile);
            }

        } catch (IOException | ServletException | NullPointerException e) {
            System.out.println(e);
        }

        return files;
    }
}
