package com.example.cookieloginapp.CookieApp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/profile")
public class Profile extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<a href='html/addNewEmployee.html'>Add New Employee</a>");
        //response.sendRedirect("");



        try {
            List<Employee>  list = EmployeeDao.getAllEmployee();
            out.print("<table border=1 width='100%'>");
            out.print("<tr>\n" +
                    "        <th>ID</th>\n" +
                    "        <th>Name</th>\n" +
                    "        <th>Password</th>\n" +
                    "        <th>Email</th>\n" +
                    "        <th>Country</th>\n" +
                    "        <th>Action</th>\n" +
                    "    </tr>");
            for (Employee employee : list) {
//
                out.print(
                        "<tr><td>" + employee.getId() + "</td><td>"
                                + employee.getName() + "</td><td>" +
                                employee.getPassword() + "</td><td>"
                                + employee.getEmail() + "</td><td>"
                                + employee.getCountry() + "</td>"
                                + "<td> <a href='updateForm?id=" +employee.getId() + "'>update</a></td>"
                                + "<td> <a href='delete?id=" +employee.getId() + "'>delete</a></td></tr>"



                );

            }
            out.print("</table>");
            out.close();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        }
    }

