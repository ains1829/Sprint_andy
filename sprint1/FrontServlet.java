package etu1829.framework.servlet;
import java.sql.*;
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;
import java.util.*;
public class FrontServlet extends HttpServlet {
    protected void processRequest (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        PrintWriter out = res.getWriter();
        out.println(req.getRequestURL());
        out.println(req.getContextPath());
        out.println(req.getServletPath());
        out.println(req.getQueryString());
    }
    protected void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        processRequest(req, res);
    }
    protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        
    }
}