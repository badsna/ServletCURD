package com.example.cookieloginapp.CookieApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

public static Connection getConnection() throws ClassNotFoundException, SQLException {
    Connection con=null;
    Class.forName("org.postgresql.Driver");
     con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employee","postgres","By@$hna%");
    return con;
}
    public static List<Employee> getAllEmployee() throws ClassNotFoundException, SQLException {
        List<Employee> list=new ArrayList<Employee>();
        Connection con=EmployeeDao.getConnection();

        PreparedStatement stmt=con.prepareStatement("SELECT * from Employee");
        ResultSet rs= stmt.executeQuery();
        while (rs.next()){
            Employee employee=new Employee();
           employee.setId(rs.getInt(1));
            employee.setName(rs.getString(2));
            employee.setPassword(rs.getString(3));
            employee.setEmail(rs.getString(4));
            employee.setCountry(rs.getString(5));
            list.add(employee);
        }
        con.close();
        return list;
    }

    public static int save(Employee employee){
        int status=0;
        try{
            Connection con= EmployeeDao.getConnection();
            PreparedStatement ps=con.prepareStatement("insert into employee values(?,?,?,?,?)");

            ps.setInt(1,employee.getId());
            ps.setString(2,employee.getName());
            ps.setString(3, employee.getPassword());
            ps.setString(4, employee.getEmail());
            ps.setString(5, employee.getCountry());

            status=ps.executeUpdate();
            con.close();

        }catch (Exception e){
            System.out.println(e);
        }
        return status;
    }

    public static int delete(int id) throws SQLException, ClassNotFoundException {
    int status=0;
    Connection con=EmployeeDao.getConnection();
    PreparedStatement ps=con.prepareStatement("delete from employee where id= ?");
    ps.setInt(1,id);
    status=ps.executeUpdate();
    con.close();
    return status;
    }

    public static Employee getEmployeeById(int id) throws SQLException, ClassNotFoundException {
    Employee employee=new Employee();
    Connection con=EmployeeDao.getConnection();
    PreparedStatement ps= con.prepareStatement("select * from employee where id =?");
    ps.setInt(1,id);

    ResultSet rs=ps.executeQuery();
    if(rs.next()){
        employee.setId(rs.getInt(1));
        employee.setName(rs.getString(2));
        employee.setPassword(rs.getString(3));
        employee.setEmail(rs.getString(4));
        employee.setCountry(rs.getString(5));
    }
    con.close();
    return employee;
    }

    public static int update(Employee employee) throws SQLException, ClassNotFoundException {
    int status=0;
    Connection con=EmployeeDao.getConnection();
    PreparedStatement ps=con.prepareStatement("update employee set name=?,password=?,email=?,country=? where id=?");

    ps.setString(1,employee.getName());
    ps.setString(2,employee.getPassword());
    ps.setString(3,employee.getEmail());
    ps.setString(4, employee.getCountry());
    ps.setInt(5,employee.getId());

    status= ps.executeUpdate();
    con.close();
    return status;
    }

}
