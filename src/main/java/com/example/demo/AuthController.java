package com.example.demo;

import com.example.demo.hibernate.Record;
import com.example.demo.hibernate.RecordHelper;
import com.example.demo.hibernate.User;
import com.example.demo.hibernate.UserHelper;
import lombok.SneakyThrows;
import org.hibernate.annotations.Filter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet("/auth")
public class AuthController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.out.println(login);
        System.out.println(password);
        User user = UserHelper.getListByParameter("login",login).get(0);
        boolean isCorrectUser = false;

        try{
            isCorrectUser = Password.check(password,user.getPassword());
            System.out.println(isCorrectUser);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if (isCorrectUser) {
            request.getSession().setAttribute("user",user);
            response.getWriter().write("Success!!");
        }
        else{
            response.sendError(404);
        }
    }
}
