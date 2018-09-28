/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectafterupdatev1.pkg2;

import java.util.List;
import java.util.*;


/**
 *
 * @author Bassel
 */
interface IPlayer{
    public void Subscribe(Player player);
    public void Leave(Player player);
}
abstract public class GameManager implements IPlayer{
    List<Player> Players=new ArrayList<Player>();
    int numberoplayers;
    int numberofships;
    Scanner input=new Scanner(System.in);
    
    GameManager(){}
  abstract  public void Start();
  abstract  public void Stop(Player x);
    @Override
    public void Subscribe(Player player){
        Players.add(player);
    }
    @Override
    public void Leave(Player player){
    Players.remove(player);
    }
}
