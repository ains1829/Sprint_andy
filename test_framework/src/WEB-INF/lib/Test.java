package etu1829.framework.test;
import etu1829.framework.annotation.*;
@Model("singleton")
public class Test {
        
        @ForFields(valeur = "champ")
        String monChamp;
        String Nom ; 
        int Age ; 
        int Nbr_appel ;
        public int getNbr_appel() {
            return Nbr_appel ;
        }
        public void setNbr_appel() {
            this.Nbr_appel += 1;
        }
        public int getAge() {
            return Age;
        }
        public void setAge(String age) {
            Age = Integer.valueOf(age);
        }
        public String getNom() {
            return Nom;
        }
        public void setNom(String nom) {
            Nom = nom;
        }
        public Test() {
        }
        public String getMonChamp() {
            return monChamp;
        }
        public void setMonChamp(String monChamp) {
            this.monChamp = monChamp;
        }

    
}

