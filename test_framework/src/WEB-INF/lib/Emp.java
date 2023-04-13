package etu1829.framework.test;
import etu1829.framework.annotation.*;
@Model
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
}