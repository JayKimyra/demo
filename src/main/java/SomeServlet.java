import com.example.demo.hibernate.Record;
import com.example.demo.hibernate.RecordHelper;
import com.example.demo.hibernate.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/index.jsp")
public class SomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            request.getRequestDispatcher("WEB-INF/login.jsp").include(request, response);
        }
        else{
            if (Objects.equals(user.getRole(), "admin")){
                request.getRequestDispatcher("WEB-INF/send.jsp").include(request, response);
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        /*PrintWriter writer = response.getWriter();
        records.forEach(value -> writer.write("<tr>" +
                "<th scope=\"row\">" + name + "</th>" +
                    "<td > " +  value.getDate() + " </td >" +
                        "<td > " +  value.getStreetId() + " </td >" +
                        "<td > " +  value.getPath() + " </td >" +
                        "<td > " +  value.getPath() + " </td >" +
                        "<td > " +  value.getUserId() + " </td >" +
                        "<td > Скачать / Удалить / Заменить </td >" + "</tr>"

        ));
        response.setContentType("text/plain");*/
        System.out.println("someservlet");
    }
}
