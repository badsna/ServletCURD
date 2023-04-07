package com.example.cookieloginapp.CookieApp;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        int id= Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String country=request.getParameter("country");

        Employee employee=new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setPassword(password);
        employee.setEmail(email);
        employee.setCountry(country);
        try {
            int status=EmployeeDao.update(employee);
            if(status>0){
                response.sendRedirect("profile");
            }
            else {
                out.print("sorry!unable to update record");
            }
            out.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



    }
}
