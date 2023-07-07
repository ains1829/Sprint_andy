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
import etu1829.framework.files.*;
import etu1829.framework.function.*;
import etu1829.framework.show.*;
import etu1829.framework.annotation.*;
import etu1829.framework.test.Models_view;
import java.lang.annotation.*;
public class FrontServlet extends HttpServlet {
    HashMap<String,Mapping> Mapping_url;
    HashMap<Class , Object> content_class ;  
    // public static void initialize(Runnable initializationFunction) {
    //     AtomicBoolean hasInitialized = new AtomicBoolean(false);
    //     Runnable cachedInitialization = () -> {
    //         if (!hasInitialized.getAndSet(true)) {
    //             initializationFunction.run();
    //         }
    //     };

    //     cachedInitialization.run();
    // }
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
    protected void processRequest (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException,Exception { 
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
            Class [] allClass = new AllClasses().allClass(c) ;
            for (int i = 0; i < allClass.length; i++) {
                Model annotation = (Model) allClass[i].getAnnotation(Model.class) ;
                System.out.println(allClass[i].getName());
                if(annotation != null){
                    System.out.println(annotation.value() + "  = > " + allClass[i].getName());
                    String retour_method = annotation.value() ;
                    if(retour_method.compareTo("singleton") == 0){
                        Method [] method = allClass[i].getDeclaredMethods() ;
                        for (int j = 0; j < method.length; j++) {
                            Methods method_annoter = method[j].getAnnotation(Methods.class) ;
                            if(method_annoter != null){
                                if(method_annoter.value().compareTo(path.split("/")[1]) == 0){
                                    if(content_class.containsKey(allClass[i]) == true){
                                        ob = content_class.get(allClass[i]) ; 
                                        valeur_default(allClass[i], ob);
                                        Method method_invoke = allClass[i].getMethod("setNbr_appel");
                                        // method_invoke.setAccessible(true);
                                        method_invoke.invoke(ob);
        
                                        Method val = allClass[i].getMethod("getNbr_appel");
                                        System.out.println("nombre d'appel = " + val.invoke(ob)); 

                                        // deuxieme test 
                                        
                                        Object ob_2 = content_class.get(allClass[i]) ;
                                        method_invoke.invoke(ob_2);
                                        Method val_2 = allClass[i].getMethod("getNbr_appel");
                                        System.out.println("nombre d'appel = " + val_2.invoke(ob_2));
                                    }
                                }
                            }
                        }
                    }
                    else{
                        Method [] method = allClass[i].getDeclaredMethods() ;
                        for (int j = 0; j < method.length; j++) {
                            Methods method_annoter = method[j].getAnnotation(Methods.class) ;
                            if(method_annoter != null){
                                if(method_annoter.value().compareTo(path.split("/")[1]) == 0){
                                    ob = allClass[i].newInstance();
                                    Method method_invoke = allClass[i].getMethod("setNbr_appel");
                                    method_invoke.setAccessible(true);
                                    method_invoke.invoke(ob );
                                    Method val = allClass[i].getMethod("getNbr_appel");
                                    System.out.println("nombre d'appel = " + val.invoke(ob));
                                    
                                    Object ob_2 = allClass[i].newInstance();
                                    method_invoke.invoke(ob_2) ;
                                    Method val_2 = allClass[i].getMethod("getNbr_appel");
                                    System.out.println("nombre d'appel = " + val_2.invoke(ob_2));
                                }
                            }
                        }
                    }
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
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