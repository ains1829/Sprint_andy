package etu1829.framework.servlet;
import java.sql.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.servlet.*; 
import javax.servlet.http.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import etu1829.framework.*;


// import etu1829.framework.annotation.Model;

import javax.servlet.http.HttpSession;
import etu1829.framework.files.*;
import etu1829.framework.function.*;
import etu1829.framework.show.*;
import etu1829.framework.annotation.*;
import etu1829.framework.test.Models_view;
import java.lang.annotation.*;
public class FrontServlet extends HttpServlet {
    HashMap<String,Mapping> Mapping_url;
    HashMap<Class , Object> content_class ;  
    @Override
    public void init(){
        try {
            System.out.println("Initialisation terminer...");
            ServletContext context = getServletContext() ; 
            String c = context.getRealPath("/WEB-INF/classes/etu1829/framework/test");
            content_class = new HashMap<>() ;
            Class [] allClass = new AllClasses().allClass(c) ;
                for (int i = 0; i < allClass.length; i++) {
                    Model annotation = (Model) allClass[i].getAnnotation(Model.class) ;
                    System.out.println(allClass[i].getName());
                    if(annotation != null){
                        if(annotation.value().compareToIgnoreCase("singleton") == 0 ){
                            Object obj = allClass[i].newInstance();
                            content_class.put(allClass[i], obj);
                        }
                        System.out.println(annotation.value() +  "   =>  " + allClass[i].getName());
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void valeur_default(Class classe , Object obj){
        try {
            Field [] fields = classe.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                if(fields[i].getName().compareToIgnoreCase("Nbr_appel")!=0){
                    if(fields[i].getType().getName().compareTo("String") == 0){
                        obj.getClass().getDeclaredMethod("set".concat(fields[i].getName()), String.class ).invoke(obj, "") ;
                    }
                    if(fields[i].getType().getName().compareTo("int") == 0){
                        obj.getClass().getDeclaredMethod("set".concat(fields[i].getName()), int.class ).invoke(obj, 0) ;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Json_transform(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException,Exception {
        HttpSession session = req.getSession();
        res.setContentType("text/html;charset=UTF-8") ;
        // init();
        PrintWriter out = res.getWriter();
        String path = req.getServletPath();
        ServletContext context = getServletContext() ; 
        String c = context.getRealPath("/WEB-INF/classes/etu1829/framework/test");
        out.println(c);
        out.println(path);
        Object ob = null ;
        try { 
        if(new Show().is_exist(req.getServletPath().split("/")[1], c) == true){
            Vector about_s =  new Show().getAbout_method(req.getServletPath().split("/")[1],c ) ; 
            HashMap<String , Mapping> all =  new AllClasses().GetHashMap(req.getServletPath(), c) ; 
            out.print("key =  " + req.getServletPath().split("/")[1] + "</br>" );
            Mapping mymapp = all.get(req.getServletPath().split("/")[1]) ; 
            out.print("classname = " + mymapp.getClassName() + "</br>");
            out.print("method = " + mymapp.getMethod() + "</br>");
            // 
            // Object objt = new Object() ;
            try {
                out.print(about_s);
                Class alls =(Class) about_s.elementAt(0) ;  
                Object ob1 = alls.newInstance() ; 
                Method my = (Method) about_s.elementAt(1) ; 
                // if(my.getReturnType().getSimpleName().compareToIgnoreCase("Models_view") == 0){
                    Models_view Modelss = (Models_view) my.invoke(ob1) ;
                    HashMap<String , Object> ses = Modelss.getSession();
                    // String profil = "" ;
                    // if(ses.get("profil") != null){
                    //     profil =  (String)ses.get("profil") ;
                    // }
                    // Boolean boll = false ;
                    // if(ses.get("connected") != null){
                    //     boll = (Boolean)ses.get("connected") ;
                    // }
                    if(ses != null) {
                        for (Map.Entry data : ses.entrySet()) {
                            session.setAttribute(data.getKey().toString(), data.getValue());
                            out.println("data = "  + data.getValue() + "</br>");
                            out.println("inon ty = " +  session.getAttribute(data.getKey().toString()) + "</br>");
                        }
                    }
                    out.println(Modelss.getView());
                    // if((profil.compareTo("") != 0) && (boll != false)){

                        if(new Utilitaire().verification_auth(session, alls, my, "profil", "connected") == true){
                            System.out.println("acces ");
                            if(Modelss.isIsJson() == true){
                                res.setContentType("application/json"); 
                                System.out.println("transform json");
                                out.println(Modelss.Json_transform());
                            }
                            
                        }else{
                            System.out.println("non non acces");
                        }
                    // }else{
                    //     System.out.println("non non acces");
                    // }
                    // RequestDispatcher requests = req.getRequestDispatcher(Modelss.getView()) ; 
                    // requests.forward(req,res) ; 
                // }
                // Models_view redir =(Models_view) my.invoke(ob) ;
            // out.println(redir.getView()) ;   
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            
        }
        out.println(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ResT_Api(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException,Exception{
        HttpSession session = req.getSession();
        res.setContentType("text/html;charset=UTF-8") ;
        // init();
        PrintWriter out = res.getWriter();
        String path = req.getServletPath();
        ServletContext context = getServletContext() ; 
        String c = context.getRealPath("/WEB-INF/classes/etu1829/framework/test");
        out.println(c);
        out.println(path);
        Object ob = null ;
        try {
        if(new Show().is_exist(req.getServletPath().split("/")[1], c) == true){
            Vector about_s =  new Show().getAbout_method(req.getServletPath().split("/")[1],c ) ; 
            HashMap<String , Mapping> all =  new AllClasses().GetHashMap(req.getServletPath(), c) ; 
            out.print("key =  " + req.getServletPath().split("/")[1] + "</br>" );
            Mapping mymapp = all.get(req.getServletPath().split("/")[1]) ; 
            out.print("classname = " + mymapp.getClassName() + "</br>");
            out.print("method = " + mymapp.getMethod() + "</br>");
            // 
            // Object objt = new Object() ;
            try {
                out.print(about_s);
                Class alls =(Class) about_s.elementAt(0) ;  
                Object ob1 = alls.newInstance() ; 
                Method my = (Method) about_s.elementAt(1) ; 
                if(my.getReturnType().getSimpleName().compareToIgnoreCase("Models_view") == 0){
                    Models_view Modelss = (Models_view) my.invoke(ob1) ;
                    HashMap<String , Object> ses = Modelss.getSession();
                    if(ses != null) {
                        for (Map.Entry data : ses.entrySet()) {
                            session.setAttribute(data.getKey().toString(), data.getValue());
                            out.println("data = "  + data.getValue() + "</br>");
                            out.println("inon ty = " +  session.getAttribute(data.getKey().toString()) + "</br>");
                        }
                    }
                    out.println(Modelss.getView());
                        if(new Utilitaire().verification_auth(session, alls, my, "profil", "connected") == true){
                            System.out.println("acces ");
                            if(Modelss.isIsJson() == true){
                                res.setContentType("application/json"); 
                                System.out.println("transform json");
                                out.println(Modelss.Json_transform());
                            }
                            
                        }else{
                            System.out.println("non non acces");
                        }
                }else{
                    Object [] emp = (Object []) my.invoke(ob1) ;
                    if(my.getAnnotation(RestApi.class) != null){
                        if(new Utilitaire().verification_auth(session, alls, my, "profil", "connected") == true){
                            System.out.println("acces ");
                            res.setContentType("application/json"); 
                            out.println(new Utilitaire().Json(emp));
                            
                        }else{
                            System.out.println("non non acces");
                        }

                    }
                }
                // if(my.getReturnType().getSimpleName().compareToIgnoreCase("Models_view") == 0){
                    
                    // }else{
                    //     System.out.println("non non acces");
                    // }
                    // RequestDispatcher requests = req.getRequestDispatcher(Modelss.getView()) ; 
                    // requests.forward(req,res) ; 
                // }
                // Models_view redir =(Models_view) my.invoke(ob) ;
            // out.println(redir.getView()) ;   
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            
        }
        out.println(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void processRequest (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException,Exception { 
        ResT_Api( req,res);
    }
    protected void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {    
        try {
            processRequest(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
    protected void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException { 
    }
}