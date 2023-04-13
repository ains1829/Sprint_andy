package etu1829.framework.servlet;
import java.sql.*;
import java.io.*;
import java.lang.reflect.Method;
import javax.servlet.*; 
import javax.servlet.http.*;
import java.util.*;
import etu1829.framework.*;
import etu1829.framework.files.*;
import etu1829.framework.function.*;
import etu1829.framework.show.*;
// import show.Show;
public class FrontServlet extends HttpServlet {
    HashMap<String,Mapping> Mapping_url;
    protected void processRequest (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException,Exception { 
        res.setContentType("text/html;charset=UTF-8") ; 
        PrintWriter out = res.getWriter();
        out.println(req.getRequestURL());
        out.println(req.getContextPath());
        out.println(req.getServletPath());
        String path = req.getServletPath();
        ServletContext context = getServletContext() ; 
        String c = context.getRealPath("/WEB-INF/classes/etu1829/framework/test");
        out.println(new Show().is_exist(path.split("/")[1] , c)) ;
        if(new Show().is_exist(req.getServletPath().split("/")[1], c) == true){
            HashMap<String , Mapping> all =  new AllClasses().GetHashMap(req.getServletPath(), c) ; 
            out.print("key =  " + req.getServletPath().split("/")[1] + "</br>" );
            Mapping mymapp = all.get(req.getServletPath().split("/")[1]) ; 
            out.print("classname = " + mymapp.getClassName() + "</br>");
            out.print("method = " + mymapp.getMethod() + "</br>");
        }
        out.println(c);
    }
    protected void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        try {
            processRequest(req, res);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
        
    }
}