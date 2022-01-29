package servlets.TONAME;

import com.example.demo.hibernate.entities.Record;
import com.example.demo.hibernate.entities.Street;
import com.example.demo.hibernate.entityHelpers.RecordHelper;
import com.example.demo.hibernate.entityHelpers.StreetHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
@WebServlet("/downloadFileServlet")
public class DownloadFileServlet extends HttpServlet{
    final String PATH_NAME = "D:\\TEST\\";
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        System.out.println("DownloadFileServlet");
        response.setContentType("application/pdf");
        //response.setContentType("APPLICATION/OCTET-STREAM");
        String recordId = "2";//request.getParameter("recordId");
        if (recordId == null){
            response.sendError(404);
            return;
        }
        Record record = RecordHelper.getById(Long.valueOf(recordId));
        System.out.println(PATH_NAME + record.getPath() + ".pdf");
        response.setHeader("Content-disposition", "attachment; filename=" + record.getName());
        try(FileInputStream in = new FileInputStream("D:\\TEST\\2022-01-29\\4\\Задания.pdf");//PATH_NAME + record.getPath() + ".pdf"TODO!!!!!!!!
            OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[1048];
            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }
}
