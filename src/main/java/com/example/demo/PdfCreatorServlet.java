package com.example.demo;

import org.joda.time.LocalDate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@MultipartConfig
public class PdfCreatorServlet extends HttpServlet {

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String streetId = new String(request.getParameter("location-street-id").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String home = new String(request.getParameter("location-home").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String flat = new String(request.getParameter("location-flat").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String location = Formatter.getFullLocation(streetId,home,flat);
        String directory = Formatter.getDirectoryName(new LocalDate(), streetId);
        String fileName = Formatter.getFileName(location);
        List<File> files = Filter.getFilesFromParts(request);
        System.out.println(directory);
        if (files.isEmpty()) {
            System.out.println("Не было загружено ни одного файла");
            return;
        }//TODO: Вывести что-то в response.
        PdfManager.createPdf(files, directory, fileName);
        files.forEach(File::delete);
    }

    public void destroy() {
    }
}