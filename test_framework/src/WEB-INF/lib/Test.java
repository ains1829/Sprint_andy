package etu1829.framework.test;
import etu1829.framework.annotation.*;
@Model("Testas")
public class Test {
        
        @ForFields(valeur = "champ")
        String monChamp;
        public Test() {
        }
        public String getMonChamp() {
            return monChamp;
        }
        public void setMonChamp(String monChamp) {
            this.monChamp = monChamp;
        }
    
}

