package com.example.demo;

import com.example.demo.hibernate.Record;
import com.example.demo.hibernate.RecordHelper;
import com.example.demo.hibernate.StreetHelper;
import org.joda.time.LocalDate;

import java.io.*;
import java.util.Date;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@MultipartConfig
@WebServlet("/pdf-request")
public class PdfCreatorServlet extends HttpServlet {

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Получаем Улицу, дом, квартиру
        Long streetId = Long.parseLong(request.getParameter("location-street-id"));
        String street = "street_name";//todo:Получить по streetId название улица из базы данных
        String home = Formatter.ISOtoUTF(request.getParameter("location-home"));
        String flat = Formatter.ISOtoUTF(request.getParameter("location-flat"));
        //Название папки, в которую сохраним файл(сегодняшняя дата + название улицы)
        String directory = Formatter.getDirectoryName(new LocalDate(), streetId);
        // Какое будет название файла
        String fileName = Formatter.getFileName(street,home,flat);

        //Parts -> Files
        List<File> files = Formatter.getFilesFromParts(request);
        System.out.println(directory);
        if (files.isEmpty()) {
            System.out.println("Не было загружено ни одного файла");
            return;//TODO: Такого быть не должно!.
        }


        boolean res1 = PdfManager.createPdf(files, directory, fileName);
        boolean res2 = false;
        if (res1) res2 = RecordHelper.save(new Record(new Date(),1L,streetId,fileName,directory+fileName));
        files.forEach(File::delete);


        if (res1 && res2){
            response.getWriter().write("success");
        }
        else {
            try {
                response.getWriter().write("error");
                response.sendError(404);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void destroy() {
    }
}