/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectafterupdatev1.pkg2;
import java.io.Serializable;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Bassel
 */

public class RandomComputerPlayer extends ComputerPlayer implements Serializable{
   static Integer wait1=0;
   int turntimer=GameOptions.Turntimerset;
    RandomComputerPlayer(int numberofships,int length,int width){
        this.name="Random PC";
       // super(numberofships,length,width);
        this.length=length;
        this.width=width;
        this.numberofships=numberofships;
        board=new Grid(length,width);
        note=new Grid(length,width);
        name="Random PC";
        //put my ships here
        //1-get the number of ships
           int[] a=new int[numberofships];
           for(int i=0;i<a.length;i++){
               a[i]=GameOptions.userships[i].v.size();
           }
           boolean[][] check=new boolean[1000][1000];
        Ship[] temp=new Ship[a.length];
        Random x=new Random();
        int iship=0;
        int Horizental=1;
        while (true){
            if (iship==a.length)
                break;
            int x1=x.nextInt(GameOptions.Lengthoftheship),y1=x.nextInt(GameOptions.Widthoftheship);
            x1++;
            y1++;
            if(Horizental==1){
                Horizental=0;
            int x2=x1,y2=y1+a[iship]-1;
            boolean bug=false;
                for(int i=y1;i<=y2;i++){
                    if  (check[x1][i]==true)
                        bug=true;
                }
                if (bug==true)
                    continue;
            if (y2<=GameOptions.Widthoftheship)
            {
                //check if it's placed here before
                String begin=(x1+"")+(char)(y1+'a'-1);
                String end=(x2+"")+(char)(y2+'a'-1);
                temp[iship]=new Ship(begin,end);
                iship++; 
                for(int i=y1;i<=y2;i++){
                    check[x1][i]=true;
                }
            }
            else
                continue;
            }
            else{
                Horizental=1;    
                int x2=x1+a[iship]-1,y2=y1;
                //loop to check every part if it's false
                boolean bug=false;
                for(int i=x1;i<=x2;i++){
                    if  (check[i][y1]==true)
                        bug=true;
                }
                if (bug==true)
                    continue;
            if (x2<=GameOptions.Lengthoftheship)
            {
                //check if placed here before
                String begin=(x1+"")+(char)(y1+'a'-1);
                String end=(x2+"")+(char)(y2+'a'-1);
                temp[iship]=new Ship(begin,end);
                iship++; 
                for(int i=x1;i<=x2;i++){
                    check[i][y1]=true;
                }
            }
            else
                continue;
            
            }
        }//end of loops
        ships=temp;
        //testingnew stuff
        board.SetGridPc();
    }
     RandomComputerPlayer(int numberofships,int length,int width,Ship[] ships,Square[][] sqgrid,boolean b,int x,int y,int turntime){
     this.name="Random PC";
         this.length=length;
        this.width=width;
        this.numberofships=numberofships;
        board=new Grid(length,width);
        note=new Grid(length,width);
        name="Random PC";
        this.ships=ships;
        board.grid=sqgrid;
     //board.SetGrid();
        board.NewBombpc(b,x,y);
        turntimer=turntime;
        
     }
    @Override
    public Square AttackOpponent() {
        //shelo
        //after verion 5
        PCattackthread mythread=new PCattackthread(turntimer);
        mythread.setlenghtandwidth(length, width);
        mythread.start();
        BattleShipGame.playerboard.SetTurn(new String("PC Turn"));
        synchronized(wait1){
            try {
                wait1.wait();
            } catch (InterruptedException ex) {}
       }
        wait1=0;
//        Random x=new Random();
//        int xx=x.nextInt(length)+1;
//        int yy=x.nextInt(width)+1;
        attackx=mythread.returnx();
        attacky=mythread.returny();
        System.out.println(attackx+" "+attacky);
        mythread.stop();
       return new Square(attackx,attacky,SquareState.unknown);
    }
 
}
