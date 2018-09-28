/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectafterupdatev1.pkg2;

/**
 *
 * @author Bassel
 */
abstract public class ComputerPlayer extends Player{
   // ComputerPlayer(int x,int y,int z){
     //   super(x,y,z);
    //}
    
  /* public AttackResult AcceptAttack(Square square){
       //Integer numberoffallsships=0;
       //attack problems
       if(GameOptions.Cheatingenabled==true){
            System.out.println("PC Board :");
            board.PrintGrid();
        }
       AttackResult finall=AttackResult.Disappoint;
        for (int i=0;i<ships.length;i++){
            if (ships[i].CheckPart(square.x, square.y))
            {
                board.GridAcceptAttack(square.x, square.y);
               if (ships[i].FullDamage())
               {
                   finall=AttackResult.Tottaly;
                    numofdestroyedships++;
               }
               else 
                   finall=AttackResult.Partially;
            }
            //else
            //   numberoffallships++;
            
                
        }
        //if for LastShip
        
        if (numofdestroyedships.equals(ships.length))
        {
            return AttackResult.Lastship;
        }
        return finall;
        
       
        
    }*/
}