package com.example.demo;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Formatter {
    public static String getFileName(String street,String home, String flat) {
        return getFullLocation(street,home,flat);
    }

    public static String getDirectoryName(Object... path){
        StringBuilder sb = new StringBuilder();
        Arrays.stream(path).forEach(x -> sb.append(x.toString()).append("\\"));
        return sb.toString();
    }

    public static String getFullLocation(String street,String home, String flat) {
        return street + ", д. " + home + ", " + flat;
    }

    public static String ISOtoUTF(String str){
        return new String(str.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
    public static List<File> getFilesFromParts(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<File> files = new ArrayList<>();
        try {
            request.getParts().forEach(System.out::println);
            List<Part> fileParts = request.getParts().stream().filter((part) -> "files".equals(part.getName())).toList();
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
