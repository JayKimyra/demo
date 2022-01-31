package servlets.pages;

import com.example.demo.hibernate.entities.Role;
import com.example.demo.hibernate.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("servlets.pages.RegistrationServlet");
        response.setContentType("text/html;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            response.sendError(403);
        }else {
            if (user.getRole() == Role.ADMIN){
                request.getRequestDispatcher("WEB-INF/registration.jsp").forward(request, response);
            }
            else{
                System.out.println("Вы не имеете права быть здесь");
                response.sendError(403);
            }
        }
    }
}
