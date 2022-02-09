package servlets.TONAME;

import com.example.demo.Formatter;
import com.example.demo.PdfManager;
import com.example.demo.hibernate.entities.Record;
import com.example.demo.hibernate.entities.Role;
import com.example.demo.hibernate.entities.Street;
import com.example.demo.hibernate.entities.User;
import com.example.demo.hibernate.entityHelpers.RecordHelper;
import com.example.demo.hibernate.entityHelpers.StreetHelper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
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





        Long streetId = Long.parseLong(request.getParameter("street"));


        Street street = StreetHelper.getById(streetId);
        String home = Formatter.ISOtoUTF(request.getParameter("home"));
        String flat = Formatter.ISOtoUTF(request.getParameter("flat"));
        System.out.println(request.getParameter("datetime"));
        Timestamp datetime = Formatter.StringToTimestamp(request.getParameter("datetime"));
        System.out.println(datetime);
        //Название папки, в которую сохраним файл(сегодняшняя дата + название улицы)
        String directory = Formatter.getDirectoryName(new Date(datetime.getTime()), street);
        // Какое будет название файла
        String fileName = Formatter.getFileName(street.getName(), home, flat);
        System.out.println(directory);

        //Parts -> Files
        List<File> files = getFilesFromParts(request,response);
        if (files.isEmpty()) {
            System.out.println("Не было загружено ни одного файла");
            response.sendError(404);
            return;//TODO: Такого быть не должно!.
        }


        boolean res1 = PdfManager.createPdf(files, directory, fileName);
        files.forEach(File::delete);
        boolean res2 = false;
        if (res1) {
            try {
                res2 = RecordHelper.save(new Record(datetime, user, street, home, flat, fileName, directory + fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (res1 && res2) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("error");
            response.sendError(404);
        }
    }

    public void destroy() {
    }
    public static List<File> getFilesFromParts(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<File> files = new ArrayList<>();
        try {
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

        } catch (Exception e) {
            System.out.println(e);
        }

        return files;
    }


}