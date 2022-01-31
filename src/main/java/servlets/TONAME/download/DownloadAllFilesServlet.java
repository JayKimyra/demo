package servlets.TONAME.download;

import com.example.demo.Formatter;
import com.example.demo.hibernate.entities.Role;
import com.example.demo.hibernate.entities.User;

import javax.mail.internet.MimeUtility;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Objects;

@WebServlet("/downloadallfiles")
public class DownloadAllFilesServlet extends HttpServlet{
    final String PATH_NAME = "D:\\TEST\\";
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        System.out.println("DownloadAllFilesServlet");

        User user = (User) request.getSession().getAttribute("user");
        if (!Objects.equals(user.getRole(), Role.ADMIN) && !Objects.equals(user.getRole(), Role.MANAGER)) {
            response.sendError(403);
            return;
        }
        try{
            Formatter.ZipAllFiles("D:\\TEST\\","D:\\test.zip");
        }catch (Exception e){
            e.printStackTrace();
        }



        response.setContentType("application/x-download; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment; filename=\"" + MimeUtility.encodeWord("Все записи.zip", "utf-8", "Q") + "\"");

        try(FileInputStream in = new FileInputStream("D:\\test.zip");
            OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[1048];//todo: Что за размер?
            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
        if (new File("D:\\test.zip").delete()) {
            System.out.println("Файл удалён");
        } else {
            System.out.println("Файл не удалён(ошибка!!)");
        }
        ;

    }
}
