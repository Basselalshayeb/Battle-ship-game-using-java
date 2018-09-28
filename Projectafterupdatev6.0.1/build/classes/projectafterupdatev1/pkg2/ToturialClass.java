/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectafterupdatev1.pkg2;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Bassel
 */
public class ToturialClass implements Serializable{
    String nameoftheattacker;
    int xaxis,yaxis;
    Date date;
    static final long  serialVersionUID=4;
    ToturialClass(String name,int x,int y,Date date){
        nameoftheattacker=name;
        xaxis=x;
        yaxis=y;
        this.date=date;
    }
    @Override
    public String toString(){
       String temp;
       temp=nameoftheattacker+" "+xaxis+" "+yaxis;
       return temp;
    }
}
