package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

        public class DownloadFileServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        //response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-disposition", "attachment; filename=data.pdf");

        try(FileInputStream in = new FileInputStream("C:\\Users\\Admin\\Desktop\\Изучаем_Java_EE_7.pdf");
            OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[1048];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }
}
