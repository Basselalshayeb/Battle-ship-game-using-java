/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectafterupdatev1.pkg2;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @author Bassel
 */
public class PCattackthread extends Thread implements Serializable{
    int xx,yy,length,width;
    int temp=10;
    public void setlenghtandwidth(int length,int width){
        this.length=length;
        this.width=width;
    }
    PCattackthread(){
    }
    PCattackthread(int t){
        temp=t;
    }
    public void run(){
      
        BattleShipGame.playerboard.repaintaftersecondscount();
      while (temp>0){
        try{
            sleep(1000);
        }catch(Exception e){}
       temp--;
      BattleShipGame.playerboard.repaintaftersecondscount();
      }
      Random x=new Random();
         xx=x.nextInt(length)+1;
         yy=x.nextInt(width)+1;
      BattleShipGame.playerboard.secondstimerreset();
      synchronized(RandomComputerPlayer.wait1){
          RandomComputerPlayer.wait1.notifyAll();
      }
    }
    public int returnx(){
        return xx;
    }
    public int returny(){
        return yy;
    }
}

