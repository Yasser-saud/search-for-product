package com.example.JDBC;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "Search", value = "/Search")
public class Search extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String id = (String)request.getParameter("id");
        Connection con = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            DbConnection db = new DbConnection();
            con = db.getConnection();
            statement = con.createStatement();
            // query to find a product that matches the given ID
            rs = statement.executeQuery("select productName from products where products.id = "+id);
            // if there's a match print it out
            if(rs.next()){
                out.println("<h1>"+ rs.getString("productName")+"</h1>");
            }
            // else show error
            else {
                out.println("<h1>not found</h1>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // close connection
        finally {
            try {
                if(rs != null){
                    rs.close();
                }
                if(statement !=null){
                    statement.close();
                }
                if(con != null){
                    con.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
