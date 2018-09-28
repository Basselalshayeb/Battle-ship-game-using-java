/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectafterupdatev1.pkg2;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Bassel
 */
public class HumenAttackThread extends Thread implements Serializable{
    int temp=10;
    HumenAttackThread(){
    }
    HumenAttackThread(int t){
        temp=t;
    }
    public void run(){
        while (temp>0){
        try{
            sleep(1000);
        }catch (Exception e){}
        temp--;
        BattleShipGame.playerboard.repaintaftersecondscount();
        }
        synchronized(HumenPlayer.x){
            HumenPlayer.x.notify();
        }
        
    }
}
