package servlets.pages;

import com.example.demo.hibernate.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/get")
public class GetServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("servlets.pages.GetServlet");
        response.setContentType("text/html;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            response.sendError(403);
        }
        else{
            request.getRequestDispatcher("WEB-INF/get.jsp").include(request, response);
        }
    }
}
