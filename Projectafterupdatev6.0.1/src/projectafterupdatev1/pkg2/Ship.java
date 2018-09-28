/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectafterupdatev1.pkg2;

import java.io.Serializable;
import java.util.*;
import javafx.util.Pair;
/**
 *
 * @author Bassel
 */
public class Ship implements Serializable{
    private int numberOfParts;
        public Vector< Square > v;   
        Integer tst=10;
        //to initilze it with two ranges
        Ship(String begin,String end){ 
            v=new Vector<Square>();
            
            int numberop=0;
            if (Objects.equals(begin.charAt(0), end.charAt(0)))
            {
                //x axis
                int x=begin.charAt(0)-'0';
                char a=begin.charAt(1),b=end.charAt(1);
                while(a<=b){
                    numberop++;
                    v.add(new Square(x,(a-'a')+1,SquareState.partofship));
                    a++;
                }
            }
            else{
                //y axis
                int y=begin.charAt(1)-'a';
                y++;
                int a=begin.charAt(0)-'0',b=end.charAt(0)-'0';
                while(a<=b)
                {
                    numberop++;
                    v.add(new Square(a,y,SquareState.partofship));
                    a++;
                }
            }
            this.numberOfParts=numberop;
                
        }
        public void AddPart(int x,int y){
            v.add(new Square(x,y,SquareState.partofship));
        }
        public boolean CheckPart(int x,int y){
            //fix the check
            int check=-1;
            Square temp=new Square(x,y,SquareState.partofship);
            for (int i=0;i<v.size();i++)
            {
                if (v.elementAt(i).x==x && v.elementAt(i).y==y && Objects.equals(SquareState.partofship, v.elementAt(i).state))
                    check=i;
                    
            }
            if (check==-1)
                return false;
            else{ // the ship is found but we srill need to check if it was hitten before 
            if(CheckAvailability(check)){
            AfterDamage(check);
            return true;
            
            }
            else
                return true;
                  //return check;
        }
        }
        public boolean CheckAvailability(int index ){
             if(Objects.equals(v.elementAt(index).state, SquareState.partofship))
                 return true;
             return false;
        }
         //***       
        public void AfterDamage(int check){
        // Decreament The Parts
            numberOfParts--;
            v.elementAt(check).ChangeState(SquareState.destroyedpartofship);
            
        }
        //Damage  
        public boolean FullDamage(){
            return numberOfParts==0;
        }
        
        
        
}
