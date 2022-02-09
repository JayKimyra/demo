package servlets.TONAME;

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

@WebServlet("/deletefileservlet")
public class DeleteFileServlet extends HttpServlet{
    final String PATH_NAME = "D:\\TEST\\";
    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        System.out.println("DeleteFileServlet");

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
                System.out.println("Данный пользователь не может удалить запись");
                return;
            }
            System.out.println("Данный пользователь отправил файл - он имеет доступ к файлу");
        }


        File file = new File(PATH_NAME + record.getPath() + ".pdf");
        File dir = file.getParentFile();
        if (file.exists()) {
            file.delete();
            if (dir.listFiles().length == 0){
                file.getParentFile().delete();
            }
        }else{
            System.out.println("Файла не существовало!!");
        }
        RecordHelper.delete(record);

    }
}
