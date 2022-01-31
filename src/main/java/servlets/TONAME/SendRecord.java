package servlets.TONAME;

import com.example.demo.Formatter;
import com.example.demo.PdfManager;
import com.example.demo.hibernate.entities.Record;
import com.example.demo.hibernate.entities.Role;
import com.example.demo.hibernate.entities.Street;
import com.example.demo.hibernate.entities.User;
import com.example.demo.hibernate.entityHelpers.RecordHelper;
import com.example.demo.hibernate.entityHelpers.StreetHelper;
import org.joda.time.LocalDate;

import java.io.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/sendservlet")
@MultipartConfig
public class SendRecord extends HttpServlet {

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Получаем Улицу, дом, квартиру
        System.out.println("SendRecord");
        User user = (User) request.getSession().getAttribute("user");
        if (Objects.equals(user.getRole(), Role.NO_ROLE)) return;


        //Parts -> Files
        List<File> files = Formatter.getFilesFromParts(request,response);


        Long streetId = Long.parseLong(request.getParameter("street"));


        Street street = StreetHelper.getById(streetId);
        String home = Formatter.ISOtoUTF(request.getParameter("home"));
        String flat = Formatter.ISOtoUTF(request.getParameter("flat"));
        //Название папки, в которую сохраним файл(сегодняшняя дата + название улицы)
        String directory = Formatter.getDirectoryName(new LocalDate(), street);
        // Какое будет название файла
        String fileName = Formatter.getFileName(street.getName(), home, flat);
        System.out.println(directory);
        if (files.isEmpty()) {
            System.out.println("Не было загружено ни одного файла");
            response.sendError(404);
            return;//TODO: Такого быть не должно!.
        }


        boolean res1 = PdfManager.createPdf(files, directory, fileName);
        boolean res2 = false;
        if (res1) {
            try {
                res2 = RecordHelper.save(new Record(new Timestamp(System.currentTimeMillis()), user, street, home, flat, fileName, directory + fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        files.forEach(File::delete);


        if (res1 && res2) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("error");
            response.sendError(404);
        }
    }

    public void destroy() {
    }
}