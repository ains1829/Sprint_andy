package etu1829.framework;
import java.sql.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import javax.servlet.*; 
import javax.servlet.http.*;
import java.util.*;
public class Utilitaire {
    
    public Utilitaire() {
    }
    public String servletPath(HttpServletRequest req, HttpServletResponse res){
        return req.getServletPath() ;
    }   
    public Object Save(Class classe , HttpServletRequest req){
        Object object = null ;
        try {
            object = classe.newInstance() ; 
            Field [] fields = classe.getDeclaredFields() ;
            for (int j = 0; j < fields.length; j++) {
                String parameter =  fields[j].getName();
                if(req.getParameter(parameter) != null){
                    String valeur = (String) req.getParameter(parameter) ; 
                    System.out.println(valeur);
                    System.out.println(parameter);
                    (object.getClass().getDeclaredMethod("set".concat(parameter) , String.class)).invoke(object, valeur ) ; 
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object ; 
    }
}
3