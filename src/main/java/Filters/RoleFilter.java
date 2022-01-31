package Filters;

import com.example.demo.hibernate.entities.Role;
import com.example.demo.hibernate.entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "roleFilter", servletNames = {
        "servlets.TONAME.SendRecord",
        "servlets.TONAME.GetDataFromServlet",
        "servlets.TONAME.download.DownloadFileServlet",
        "servlets.TONAME.download.DownloadAllFilesServlet",
        "servlets.TONAME.download.DownloadFilesServlet",
        "servlets.TONAME.AddNewUserServlet"
})
public class RoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");
        System.out.println("RoleFilter!");
        System.out.println("User: " + user);
        if (user != null && (user.getRole().equals(Role.ADMIN) || user.getRole().equals(Role.WORKER))){//todo: поменять на другого челика
            filterChain.doFilter(servletRequest,servletResponse);
        } else{
            ((HttpServletResponse) servletResponse).sendError(403);
        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
