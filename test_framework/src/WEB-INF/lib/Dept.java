package etu1829.framework.test;
import etu1829.framework.files.*;

import java.io.File;

import etu1829.framework.annotation.*;
@Model("normal")
public class Dept{
    String Nom ;
    String Prenom ;
    String Dates ;
    int Age ; 
    public int getAge() {
        return Age;
    }
    public void setAge(String age) {
        this.Age = Integer.valueOf(age);
    }
    public String getNom() {
        return Nom;
    }
    public void setNom(String nom) {
        Nom = nom;
    }
    @Methods("prenom")
    public String getPrenom() {
        return Prenom;
    }
    public void setPrenom(String prenom) {
        Prenom = prenom;
    }
    public String getDates() {
        return Dates;
    }
    public void setDates(String dates) {
        Dates = dates;
    }
    public Dept(){
    }
    int Nbr_appel ;
    public int getNbr_appel() {
        return Nbr_appel;
    }
    public void setNbr_appel() {
        this.Nbr_appel += 1;
    }
}