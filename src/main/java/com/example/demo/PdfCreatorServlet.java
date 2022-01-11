package com.example.demo;

import org.joda.time.LocalDate;

import java.io.*;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@MultipartConfig
public class PdfCreatorServlet extends HttpServlet {

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String streetId = Formatter.ISOtoUTF(request.getParameter("location-street-id"));
        String home = Formatter.ISOtoUTF(request.getParameter("location-home"));
        String flat = Formatter.ISOtoUTF(request.getParameter("location-flat"));
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