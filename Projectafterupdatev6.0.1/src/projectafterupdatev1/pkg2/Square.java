/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectafterupdatev1.pkg2;

import java.io.Serializable;
import java.util.Objects;

enum SquareState{water,partofship,destroyedpartofship,unknown};
/**
 *
 * @author Bassel
 */
public class Square implements Serializable{
    int x,y;
    SquareState state;
    Square(){
       x=1;
       y=1;
       state=SquareState.unknown;
   }
    Square(int x,int y,SquareState state){
       this.x=x;
       this.y=y;
       this.state=state;
   }
    public void ChangeState(SquareState state){
       this.state=state;
   }
    //check this after the first play
   public void PrintState(){
       if (Objects.equals(state, SquareState.water))
           System.out.print(". ");
       else if (Objects.equals(state, SquareState.partofship))
       System.out.print("# ");
       else if (Objects.equals(state, SquareState.destroyedpartofship))
           System.out.print("* ");
       else
           System.out.print("N");    
   }
   public SquareState ReturnState(){
       return state;
   }
   
}
