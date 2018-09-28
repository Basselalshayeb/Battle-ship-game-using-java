/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectafterupdatev1.pkg2;

import java.io.InputStream;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Bassel
 */
public class HumenPlayer extends Player implements Serializable{
    static String x="1a";
    static String y;
    int turntimer=GameOptions.Turntimerset;
   HumenPlayer(int numberofships,int length,int width,String name){
       //super(numberofships,length,width);
       this.length=length;
        this.width=width;
        this.numberofships=numberofships;
        board=new Grid(length,width);
        note=new Grid(length,width);
        ships=GameOptions.userships;
           
            //initlizq board with our ships
        board.SetGrid();
       
        this.name=name;
    }
   HumenPlayer(int numberofships,int length,int width,String name,Ship[] ships,Square[][] sqgrid,boolean b,int x,int y,int turntime){
       //super(numberofships,length,width);
       this.length=length;
        this.width=width;
        this.numberofships=numberofships;
        board=new Grid(length,width);
        note=new Grid(length,width);
        this.ships=ships;
           
            //initlizq board with our ships
        //board.SetGrid();
       board.grid=sqgrid;
        this.name=name;
        
        board.NewBomb(b,x,y);
        turntimer=turntime;
         
    }
    
    @Override
    public Square AttackOpponent(){
        /*synchronized(Player_Board.waitforhumen){
            Player_Board.waitforhumen.notify();
        }*/
        Player_Board.waitforhumen=10;
        
       /* System.out.println(name+" board : ");
       board.PrintGrid();
       System.out.println(name+" notebook");
       note.PrintGrid();
       System.out.println(name + " it's your turn to play ");
       //our holy code
       /*while (x=="1a"){
           if (Objects.equals(x, x1))
              System.out.println("wdwdwdwwd");
           if (b==10)
           {
               break;
           }    
       }*/
        //put the 10 second rule 
        HumenAttackThread mythread2=new HumenAttackThread(turntimer);
        mythread2.start();
        BattleShipGame.playerboard.SetTurn(new String(name+" Turn"));
       synchronized(x){
            try {
                x.wait();
            } catch (InterruptedException ex) {}
       }
       mythread2.stop();
       //stopthread=true;
       //System.out.println(x);
       BattleShipGame.playerboard.secondstimerreset();
       int row=1,col=1;
      //System.out.println(x+" "+y);
       //if the 10 second bassed we wil return null
       if (Objects.equals(x ,new String("1a") ))
           return null;
       try{
        
        row=Integer.parseInt(x);
        col=Integer.parseInt(y);
        
        
       if (Objects.equals(BattleShipGame.BoardCheck[row][col],true) && row==10000)
       throw new SquareAttackedBefore();
       else
           BattleShipGame.BoardCheck[row][col]=true;
       }catch (SquareAttackedBefore e){
           x="1a";
           return null;
       }catch (NumberFormatException e){
           x="1a";
           System.out.println("fuck"+row+" "+x);
           return null;
       }
       
       attackx=row;
       attacky=col;
       x="1a";
       return new Square(attackx,attacky,SquareState.unknown);
       }
    public void AcceptAttackResult(AttackResult attackResult){
         if (Objects.equals(attackResult, AttackResult.BombExploded))
        {
            note.SetGridCell(attackx, attacky, SquareState.water);
            //fill the 8 cells with X
            BattleShipGame.notedata[attackx-1][attacky]="X";
            Player_Board.dontAttackAgain[attackx][attacky]=true;
            int a[]={1,-1,0,0,1,-1,1,-1};
            int b[]={0,0,1,-1,1,-1,-1,1};
            for(int i=0;i<a.length;i++){
                int x1=attackx+a[i];
                int y1=attacky+b[i];
                if (x1<=length && x1>=1 && y1<=width && y1>=1){
                    Player_Board.dontAttackAgain[x1][y1]=true;
                    BattleShipGame.notedata[x1-1][y1]="X";
                    note.SetGridCell(x1, y1, SquareState.water);
                }
            }
        }else 
        if(Objects.equals(attackResult, AttackResult.Partially) || Objects.equals(attackResult, AttackResult.Lastship) ||Objects.equals(attackResult, AttackResult.Tottaly))
        {
            note.SetGridCell(attackx, attacky, SquareState.destroyedpartofship);
        }
        else if (Objects.equals(attackResult, AttackResult.Disappoint))
            note.SetGridCell(attackx,attacky, SquareState.water);
        //editing the data
        if (Objects.equals(attackResult, AttackResult.Disappoint) )
        {
            Player_Board.dontAttackAgain[attackx][attacky]=true;
            BattleShipGame.notedata[attackx-1][attacky]=".";
        }else if (Objects.equals(attackResult, AttackResult.Partially) || Objects.equals(attackResult,AttackResult.Tottaly))
        {
            Player_Board.dontAttackAgain[attackx][attacky]=true;
            BattleShipGame.notedata[attackx-1][attacky]="#";
        }
        
        //madam 3rftha mrra tania bl humen ma3at fe da3e llshr6
        System.out.println("Attack Result :"+"\n"+attackResult);
    }
    //override accept attack
    public AttackResult AcceptAttack(Square square){
       //Integer numberoffallsships=0;
       //attack problems
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
        
         if (Objects.equals(finall,AttackResult.Disappoint)){
            //if bomb here isbombs here should chek if bomb is alive too and then kill the bomb here instant
            if (board.IsBombHere(square.x,square.y)){
                finall=AttackResult.BombExploded;
                int x1=square.x,y1=square.y;
                BattleShipGame.boarddata[x1-1][y1]='.';
                Vector<Integer> xaxis=new Vector<Integer>(),yaxis=new Vector<Integer>();
                //8 ifs here
                if (x1+1<=length){
                    xaxis.add(x1+1);
                    yaxis.add(y1);
                    if (Objects.equals(BattleShipGame.boarddata[x1][y1],'#'))
              BattleShipGame.boarddata[x1][y1]='*';
                  
                }
                if (x1-1>=1)
                {
                    xaxis.add(x1-1);
                    yaxis.add(y1);
            if (Objects.equals(BattleShipGame.boarddata[x1-2][y1],'#'))
              BattleShipGame.boarddata[x1-2][y1]='*';
                }
                if (y1+1<=width)
                {
                    yaxis.add(y1+1);
                    xaxis.add(x1);
                    if (Objects.equals(BattleShipGame.boarddata[x1-1][y1+1],'#'))
              BattleShipGame.boarddata[x1-1][y1+1]='*';
                }
                if(y1-1>=1){
                    yaxis.add(y1-1);
                    xaxis.add(x1);
                    if (Objects.equals(BattleShipGame.boarddata[x1-1][y1-1],'#'))
              BattleShipGame.boarddata[x1-1][y1-1]='*';
                }//corner
                if(x1+1<=length && y1+1<=width){
                    yaxis.add(y1+1);
                    xaxis.add(x1+1);
                    if (Objects.equals(BattleShipGame.boarddata[x1][y1+1],'#'))
              BattleShipGame.boarddata[x1][y1+1]='*';
                }
                if(x1-1>=1 && y1-1>=1){
                    xaxis.add(x1-1);
                    yaxis.add(y1-1);
                    if (Objects.equals(BattleShipGame.boarddata[x1-2][y1-1],'#'))
              BattleShipGame.boarddata[x1-2][y1-1]='*';
                }
                if(x1+1<=length && y1-1>=1){
                    xaxis.add(x1+1);
                    yaxis.add(y1-1);
                    if (Objects.equals(BattleShipGame.boarddata[x1][y1-1],'#'))
              BattleShipGame.boarddata[x1][y1-1]='*';
                }
                if(x1-1>=1 &&  y1+1<=width){
                    xaxis.add(x1-1);
                    yaxis.add(y1-1);
                    if (Objects.equals(BattleShipGame.boarddata[x1-2][y1+1],'#'))
              BattleShipGame.boarddata[x1-2][y1+1]='*';
                }
                //walk through the 8 cells and destroy them all
                for (int j = 0; j < xaxis.size(); j++) {
                    for (int i = 0; i < ships.length; i++) {
                        if (ships[i].CheckPart(xaxis.elementAt(j), yaxis.elementAt(j))) {
                            board.GridAcceptAttack(xaxis.elementAt(j), yaxis.elementAt(j));
                            if (ships[i].FullDamage()) {
                              //  finall = AttackResult.Tottaly;
                                numofdestroyedships++;
                            }
                        }
                        
                    }
                } 
            }
        }
        
        if (Objects.equals(finall, AttackResult.Partially) || Objects.equals(finall, AttackResult.Tottaly))
            if (!Objects.equals(BattleShipGame.boarddata[square.x-1][square.y],'*'))
            BattleShipGame.boarddata[square.x-1][square.y]='*';
        //if for LastShip
        if (numofdestroyedships.equals(ships.length))
        {
            return AttackResult.Lastship;
        }
        return finall;
        
        
    }   

   
    }