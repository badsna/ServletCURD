package com.example.cookieloginapp.CookieApp;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/updateForm")
public class UpdateFormServlet  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<h1>Update Employee</h1>");
        int id= Integer.parseInt(request.getParameter("id"));
        try {
            Employee employee=EmployeeDao.getEmployeeById(id);
            out.println("<form action='update' method='post'>\n");
            out.print("<table> ");
            out.print("<tr><td><input type='hidden' name='id' value='"+employee.getId() +"' ></td></tr>\n");
            out.print("<tr>\n" +
                    "            <td>Name:</td><td><input type=\"text\" name=\"name\" value='"+employee.getName() +"'></td>\n" +
                    "        </tr> ");

            out.print(" <tr>\n" +
                    "            <td>Password:</td><td><input type=\"text\" name=\"password\" value='"+employee.getPassword()+"' ></td>\n" +
                    "        </tr>");
            out.print("<tr>\n" +
                    "            <td>Email:</td><td><input type=\"text\" name=\"email\" value='" +employee.getEmail() + "' ></td>\n" +
                    "        </tr> ");
            out.print(" <tr>\n" +
                    "            <td>Country:</td><td>\n" +
                    "                <select name=\"country\" style=\"width:150px\">\n" +
                    "                    <option>India</option>\n" +
                    "                    <option>USA</option>\n" +
                    "                    <option>Nepal</option>\n" +
                    "                    <option>UK</option>\n" +
                    "                </select>\n" +
                    "        </td>\n" +
                    "        </tr>");
            out.print("        <tr><td><input type=\"submit\" value=\"Edit and Save Employee\"></td></tr>\n ");
            out.print("</table>\n" +
                    "</form> ");

            out.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
