package servlets.TONAME.download;

import com.example.demo.hibernate.entities.Record;
import com.example.demo.hibernate.entities.Role;
import com.example.demo.hibernate.entities.Street;
import com.example.demo.hibernate.entities.User;
import com.example.demo.hibernate.entityHelpers.RecordHelper;

import javax.mail.internet.MimeUtility;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Objects;

@WebServlet("/downloadfileservlet")
public class DownloadFileServlet extends HttpServlet{
    final String PATH_NAME = "D:\\TEST\\";
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        System.out.println("DownloadFileServlet");

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendError(403);
            return;
        }


        String recordId = request.getParameter("recordId");
        if (recordId == null){
            response.sendError(404);
            System.out.println("В запросе нет записи");
            return;
        }
        Record record = RecordHelper.getById(Long.valueOf(recordId));
        if (!Objects.equals(user.getRole(), Role.ADMIN)){
            if (!Objects.equals(record.getUser(), user)){
                response.sendError(403);
                System.out.println("Данный пользователь не может скачать запись");
                return;
            }
            System.out.println("Данный пользователь отправил файл - он имеет доступ к файлу");
        }


        response.setContentType("application/x-download; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println(PATH_NAME + record.getPath() + ".pdf");
        String filename = record.getName() + ".pdf";
        response.setHeader("Content-disposition", "attachment; filename=\"" + MimeUtility.encodeWord(filename, "utf-8", "Q") + "\"");


        try(FileInputStream in = new FileInputStream(PATH_NAME + record.getPath() + ".pdf");
            OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[1048];//todo: Что за размер?
            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }
}
