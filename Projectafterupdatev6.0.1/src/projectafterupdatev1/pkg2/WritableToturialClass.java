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
public class WritableToturialClass implements Serializable{
    ToturialClass[] moves=null;
    int i=0;
    int gameID=0;
    Object[][] grid,note;
    Object[] header;
    public void Initlize(int size){
        moves=new ToturialClass[size];
    }
    public void add(String name,int x,int y,Date date){
        moves[i]=new ToturialClass(name,x,y,date);
        i++;
    }
}
