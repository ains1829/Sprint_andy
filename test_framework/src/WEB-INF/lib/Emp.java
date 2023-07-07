package etu1829.framework.test;
import etu1829.framework.files.*;
import java.io.File;
import java.lang.reflect.Method;
import etu1829.framework.annotation.*;
// @Model("normal")
@Model(value = "singleton")
public class Emp{
    @ForFields
    String Test ;
    public String getTest() {
        return Test;
    }
    public void setTest(String test) {
        this.Test = test;
    }
    String Aa;
    public String getAa() {
        return Aa;
    }
    public void setAa(String aa) {
        this.Aa = aa;
    }
    @Methods("andy")
    public void add_some(){
        System.out.println("salut bogosy");
    }
    @Methods("view")
    public Models_view m_view_emp(){
        Models_view m_v = new Models_view("test.jsp") ;
        return m_v ; 
    }
    @Methods("login")
    public Models_view login() throws Exception {
        Models_view mv = new Models_view("login.jsp");
        mv.setSession();
        try {
            mv.addSession("profil", "profil");
            mv.addSession("connected", true);
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    return mv;
    }
    @Auth("profil")
    @Methods("list_emp")
    public Models_view  deptList() throws Exception {
        Models_view mv = new Models_view("Emp.jsp");
        try {
            mv.setIsJson(true);
            mv.setDonnee();
            String[] list = {"ANdy" , "MIrao" , "Miarituana"};
            mv.add_items("list", list);
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
        return mv;
       
    }
    @Auth("profil")
    @Methods("list_emp_no_json")
    public Models_view  deptList_no_json() throws Exception {
        Models_view mv = new Models_view("Emp.jsp");
        try {
            mv.setIsJson(false);
            mv.setDonnee();
            String[] list = {"ANdy" , "MIrao" , "Miarituana"};
            mv.add_items("list", list);
        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
        return mv;
       
    }
    @Auth("profil")
    @RestApi("rest")
    @Methods("all_emps")
    public Emp [] all_emp(){
        Emp [] donne = new Emp[2];
        donne[0] = new Emp("salut");
        donne[1] = new Emp("bbabababab");
        return donne;
    }
    FileUpload File ;
    public FileUpload getFile() {
        return File;
    }
    public void setFile(FileUpload file) {
        this.File = file;
    }
    public String getNamePath(){
        return File.getPath() ;
    } 
    int Nbr_appel ;
    public int getNbr_appel() {
        return Nbr_appel ;
    }
    public void setNbr_appel() {
        this.Nbr_appel += 1;
    }
    public Emp(String aa){
        this.Aa = aa;
    }
    public Emp(){}
    // public String Json(Object [] emp){
    //     Gson gson = new Gson();
    //     String jsons = gson.toJson(emp);
    //     return jsons;
    // }   
}   