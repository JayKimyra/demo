package servlets.TONAME;

import com.example.demo.hibernate.entities.Record;
import com.example.demo.hibernate.entities.Role;
import com.example.demo.hibernate.entities.User;
import com.example.demo.hibernate.entityHelpers.RecordHelper;
import com.example.demo.hibernate.entityHelpers.StreetHelper;
import com.example.demo.hibernate.entityHelpers.UserHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@WebServlet("/getpostsservlet")
public class GetDataFromServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("GetDataFromServlet");

        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");


        Timestamp dateBegin;
        Timestamp dateEnd;
        Long streetId;
        Long userId;

        String stringDateBegin = request.getParameter("date-begin");
        String stringDateEnd = request.getParameter("date-end");
        String stringStreetId = request.getParameter("street-id");
        String stringUserId = request.getParameter("user-id");
        System.out.println(stringDateBegin);
        System.out.println(stringDateEnd);
        try{
            dateBegin = Objects.equals(stringDateBegin, "") ? null : new Timestamp(Date.valueOf(stringDateBegin).getTime());
            dateEnd = Objects.equals(stringDateEnd, "") ? null : new Timestamp(Date.valueOf(stringDateEnd).getTime() + 24*60*60*1000);
            streetId = Objects.equals(stringStreetId, "all") ? null : Long.parseLong(stringStreetId);
            userId = Objects.equals(stringUserId, "all") ? null : Long.parseLong(stringUserId);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Не удалось обработать данные");
            response.sendError(404);
            return;
        }



        User user = (User) request.getSession().getAttribute("user");
        if (!Objects.equals(user.getRole(), Role.ADMIN) && !(Objects.equals(user.getRole(), Role.MANAGER))){
            if (!Objects.equals(userId, user.getId())){
                System.out.println(Objects.equals(userId, user.getId()));
                System.out.println(userId);
                System.out.println(user.getId());
                System.out.println("Пользователь видит только свои записи!");
                response.sendError(403);
                return;
            }
        }

        List<Record> records = RecordHelper.getListByMultipleParameter(new HashMap<Object, Object>() {
            {
                put("street", streetId != null ? StreetHelper.getById(streetId) : null);//TODO СДЕЛАТЬ КРАСИВЕЕ
                put("user", userId != null ? UserHelper.getById(userId) : null);
                put("dateBegin", dateBegin);
                put("dateEnd", dateEnd);
            }
        });

        if (records == null || records.isEmpty()){
            System.out.println("Нет подходящих результатов");
            response.addHeader("error_message","Нет подходящих результатов");
            response.sendError(404);
            return;
        }

        PrintWriter writer = response.getWriter();
        records.forEach(value -> writer.write("<tr>" +
                "<th>" + value.getId() + "</th>" +
                "<td > " +  value.getDate() + " </td >" +
                "<td > " +  value.getStreet().getName() + " </td >" +
                "<td > " +  value.getHome() + " </td >" +
                "<td > " +  value.getFlat() + " </td >" +
                "<td > " + value.getUser().getFirstname() + " " + value.getUser().getLastname() +" </td >" +
                "<td>" + "<button onclick=\"download("+ value.getId() +")\">Скачать</button>" +"/Удалить/Заменить" + "</td>" +
                "</tr>"

        ));

    }
}
