package com.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = {"/LoginServlet"},
        initParams = {
             @WebInitParam(name = "user", value = "Abhishek"),
             @WebInitParam(name = "password", value = "qwerty")
        }
)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String password  = req.getParameter("password");

//        String userID = getServletConfig().getInitParameter("user");
//        String pass = getServletConfig().getInitParameter("password");
        Pattern userPattern = Pattern.compile("^[A-Z][a-zA-Z]{2,}$");
        Pattern passPattern = Pattern.compile("^(?=.*\\d)(?=.*[A-Z])(?=.*[$&+,:;=?@#|<>.^*()%!-]).{1}(?=.*[\\S]).{8,}$");
        if(userPattern.matcher(user).matches() && passPattern.matcher(password).matches()){
            req.setAttribute("user", user);
            req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
        }else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter pw = resp.getWriter();
            pw.println("<font color=red> Either the username or password is wrong. </font>");
            rd.include(req, resp);
        }
    }
}
