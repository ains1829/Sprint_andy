package etu1829.framework.test ; 
import java.sql.*;
import java.io.*;
import java.lang.reflect.Method;
import javax.servlet.*; 
import javax.servlet.http.*;
import java.util.*;
import com.google.gson.Gson;
public class Models_view{
    String view ;
    HashMap<String, Object> donnee;
    HashMap<String , Object> session;
    boolean IsJson;
    public HashMap<String, Object> getSession() {
        return session;
    }
    // public void setSession(HashMap<String, Object> session) {
    //     this.session = session;
    // }
    public void setSession() {
        this.session = new HashMap<String, Object>();
        // System.out.println("lsdhfawefhaieufWELI WGGLF UIG DDss");
    }

    public HashMap<String, Object> getDonnee() {
        return donnee;
    }

    public void setDonnee() {
        this.donnee = new HashMap<String, Object>();
    }

    public Models_view(String view) {
        this.view = view;
    }

    public Models_view() {
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
    public void add_item(String key , Object values , HashMap<String , Object> objet){
        objet.put(key, values) ; 
    } 
    public void add_items(String key , String [] values){
        this.donnee.put(key, values) ; 
    }
    public void addSession(String key, Object value) {
        // this.session = new HashMap<String , Object>();
        this.session.put(key, value);
    }
    public String Json_transform(){
        Gson gson = new Gson();
        String transform = gson.toJson(this.donnee);
        return transform;
    }
    public boolean isIsJson() {
        return IsJson;
    }
    public void setIsJson(boolean isJson) {
        IsJson = isJson;
    }
}