package com.example.cookieloginapp.CookieApp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addNewEmployee")
public class AddNewEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

        int status=EmployeeDao.save(employee);
        if(status>0){
            out.print("<p>Added New Employee</p>");
            request.getRequestDispatcher("html/addNewEmployee.html").include(request,response);
        }
        else {
            out.print("sorry!unable to save record");
        }
        out.close();


    }
}
