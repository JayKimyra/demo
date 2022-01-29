package servlets.TONAME;

import com.example.demo.Password;
import com.example.demo.hibernate.entities.User;
import com.example.demo.hibernate.entityHelpers.UserHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth")
public class AuthController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.out.println("AuthController " + login + " " + password);
        User user = UserHelper.getListByParameter("login", login).get(0);
        boolean isCorrectUser = false;

        try {
            isCorrectUser = Password.check(password, user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isCorrectUser) {
            request.getSession().setAttribute("user", user);
            response.getWriter().write("Success!!");
        } else {
            response.sendError(404);
        }
    }
}
