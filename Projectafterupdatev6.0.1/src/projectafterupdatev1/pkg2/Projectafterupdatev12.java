/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectafterupdatev1.pkg2;


import java.io.IOException;
import java.io.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.*;

/**
 *
 * @author Bassel
 */
public class Projectafterupdatev12 {
      
    static Integer gamewaiter = 0;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        GameOptions game = new GameOptions();
        game.setLocation(500, 80);
        game.setVisible(true);
        synchronized (gamewaiter) {
            try {
                gamewaiter.wait();
            } catch (InterruptedException e) {
            }
        }
        if (game.Loadgame == true) {
            BattleShipGame game1=new BattleShipGame();
            try {
                game1.Load(GameOptions.choosenfile);
            } catch (IOException ex) {}
            catch (ClassNotFoundException ex) {}
            

        } else {
            BattleShipGame game1 = new BattleShipGame();
            game1.Start();
        }
        //testing the load shit from the big class
       /* File f=new File(new File("").getAbsolutePath()+"\\src\\projectafterupdatev1\\pkg2\\forSavingHollyGame");
        ObjectInputStream obj=new ObjectInputStream(new FileInputStream(f));
        
        BattleShipGame temp=(BattleShipGame)obj.readObject();
        System.out.println(temp.startdate);
        
        obj.close();*/
        //testing the toturial
        /*File f=new File(new File("").getAbsolutePath()+"\\src\\projectafterupdatev1\\pkg2\\ToturialFile");
        ObjectInputStream obj=new ObjectInputStream(new FileInputStream(f));
        WritableToturialClass temp;
        temp=(WritableToturialClass)obj.readObject();
        System.out.println(temp.gameID);
        temp=(WritableToturialClass)obj.readObject();
        System.out.println(temp.gameID);
       obj.close();*/
        //testing the scoreboard
        /*File f=new File(new File("").getAbsolutePath()+"\\src\\projectafterupdatev1\\pkg2\\ScoreBoard");
        ObjectInputStream obj=new ObjectInputStream(new FileInputStream(f));
        ScoreRecord temp=(ScoreRecord)obj.readObject();
        System.out.println(temp.name);
         temp=(ScoreRecord)obj.readObject();
        System.out.println(temp.name);
        obj.close();*/
        //testing the Game Records
        /*File f=new File(new File("").getAbsolutePath()+"\\src\\projectafterupdatev1\\pkg2\\GameRecords");
        ObjectInputStream obj=new ObjectInputStream(new FileInputStream(f));
        FinishedGameRecord temp=(FinishedGameRecord)obj.readObject();
        System.out.println(temp.enddate);
        temp=(FinishedGameRecord)obj.readObject();
        System.out.println(temp.enddate);
        obj.close();*/
        
        //initlizing the fileNames 
        /*File f=new File(new File("").getAbsoluteFile()+"\\src\\projectafterupdatev1\\pkg2\\filesNames");
        ObjectOutputStream obj=new ObjectOutputStream(new FileOutputStream(f));
        obj.writeObject(null);
        obj.close();*/
        //inilize the Game saving shit
        /*File f=new File(new File("").getAbsolutePath()+"\\src\\projectafterupdatev1\\pkg2\\forSavingHollyGame");
        ObjectOutputStream obj=new ObjectOutputStream(new FileOutputStream(f));
        obj.writeObject(new BattleShipGame());
        obj.close();*/
        //inilizq the toturial file
        
       /* File f=new File(new File("").getAbsolutePath()+"\\src\\projectafterupdatev1\\pkg2\\ToturialFile");
        ObjectOutputStream obj=new ObjectOutputStream(new FileOutputStream(f));
        obj.writeObject(new WritableToturialClass());
        
        obj.close();*/
        
        
      /* //initlizing the Score Board
        File f=new File(new File("").getAbsolutePath()+"\\src\\projectafterupdatev1\\pkg2\\ScoreBoard");
        ObjectOutputStream obj=new ObjectOutputStream(new FileOutputStream(f));
        obj.writeObject(new ScoreRecord("0", false));
        obj.close();*/
        
        //initlizing the GameRecords
                /* File f=new File(new File("").getAbsolutePath()+"\\src\\projectafterupdatev1\\pkg2\\GameRecords");
                ObjectOutputStream obj=new ObjectOutputStream(new FileOutputStream(f));
       // while(true){
            try{
               
                
                FinishedGameRecord temp=new FinishedGameRecord("0","0",new Date(),new Date(),0,"0");
                obj.writeObject(temp);
                obj.close();
            }catch(EOFException e){
            }
        //}*/
    }

}
