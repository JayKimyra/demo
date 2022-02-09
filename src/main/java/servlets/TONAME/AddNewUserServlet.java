package servlets.TONAME;

import com.example.demo.hibernate.entities.Role;
import com.example.demo.hibernate.entities.User;
import com.example.demo.hibernate.entityHelpers.UserHelper;

import java.io.*;
import java.util.Locale;
import java.util.Objects;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/addnewuser")
@MultipartConfig
public class AddNewUserServlet extends HttpServlet {

    public void init() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("AddNewUserServlet");
        User user = (User) request.getSession().getAttribute("user");
        if (!Objects.equals(user.getRole(), Role.ADMIN)) return;


        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String login = request.getParameter("login");
        String password = request.getParameter("last-name");
        String role = request.getParameter("role");
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(login);
        System.out.println(password);
        System.out.println(role);
        if (!UserHelper.getListByParameter("login", login).isEmpty()) {
            response.sendError(404);//TODO Ошибка существующего пользователя
            return;
        }

        try {
            User userToAdd = new User(login, firstName, lastName, password,Role.valueOf(role.toUpperCase(Locale.ROOT)));
            UserHelper.save(userToAdd);
        } catch (Exception e) {
            System.out.println("НЕВЕДОМАЯ ОШИБКА " + this.getClass());//TODO Хз что это - убрать
        }


    }

    public void destroy() {
    }
}