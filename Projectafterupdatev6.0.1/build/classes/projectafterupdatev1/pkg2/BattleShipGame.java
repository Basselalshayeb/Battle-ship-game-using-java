/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectafterupdatev1.pkg2;

import java.awt.Color;
import java.io.File;
import java.io.Serializable;
import java.util.Objects;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Bassel
 */
public class BattleShipGame extends GameManager implements Serializable{
 public  static  Player_Board playerboard=new Player_Board();
    Date startdate,enddate;
    public final String filesNames="filesNames";
    
    String playername;
  static Integer savewaiter=1;
  SaveThread savethread=new SaveThread();
    MusicThread music=new MusicThread();
public  static Object[][] notedata,boarddata;
public static Object[] boardsheader;

static public boolean BoardCheck[][]=new boolean[100][100]; 
List<ToturialClass> moves=new ArrayList<ToturialClass>();
    BattleShipGame(){
        savethread.start();
        /*numberoplayers=2;
        //12/3
        numberofships=GameOptions.userships.length;
        //use the number of row and number of coloumns to set your arrays
        notedata=new Object[GameOptions.Lengthoftheship][GameOptions.Widthoftheship+1];
        boarddata=new Object[GameOptions.Lengthoftheship][GameOptions.Widthoftheship+1];*/
    }
    
        //initlize the tables data and Initialize the players data(human and pc)

    @Override
    public void Start(){
        //Constructer
        numberoplayers=2;
        //12/3
       
        //numberofships=GameOptions.userships.length;
        numberofships=GameOptions.userships.length;
        //use the number of row and number of coloumns to set your arrays
        notedata=new Object[GameOptions.Lengthoftheship][GameOptions.Widthoftheship+1];
        boarddata=new Object[GameOptions.Lengthoftheship][GameOptions.Widthoftheship+1];
        //end of constructer
       music.start();
        boardsheader=new Object[GameOptions.Widthoftheship+1];
        boardsheader[0]=new Object();
        boardsheader[0]="";
        for(int i=1;i<=GameOptions.Widthoftheship;i++){
            boardsheader[i]=new Object();
            boardsheader[i]=(char)(i+'A'-1);
        }
        for (int i=0;i<GameOptions.Lengthoftheship;i++)
            for(int j=0;j<=GameOptions.Widthoftheship;j++){
                notedata[i][j]=new Object();
                notedata[i][j]="N";
                boarddata[i][j]=new Object();
                boarddata[i][j]=".";
            }
        for(int i=0;i<GameOptions.Lengthoftheship;i++){
            boarddata[i][0]=i+1;
            notedata[i][0]=i+1;
            
        }
        playername=GameOptions.Name;
        Subscribe(new HumenPlayer(numberofships,GameOptions.Lengthoftheship, GameOptions.Widthoftheship,playername));  
        Subscribe(new RandomComputerPlayer(numberofships,GameOptions.Lengthoftheship, GameOptions.Widthoftheship));
        Players.get(0).startbomb();
        Players.get(1).startbombpc();
        playerboard.repaintafterupdate();
        playerboard.fixtablessize(GameOptions.Widthoftheship, GameOptions.Lengthoftheship);
        playerboard.setVisible(true);
       //starting the date 
        startdate=new Date();
        RunGame();
        
    }
    public void Load(String ss) throws IOException, ClassNotFoundException{
        FileInputStream ins=null;
     try {
          numberoplayers=2;
          //old file name javatest.ser
         ins = new FileInputStream(new File(new File("").getAbsolutePath()+"\\src\\projectafterupdatev1\\pkg2\\"+ss));
         ObjectInputStream obj=new ObjectInputStream(ins);
         boardsheader=(Object[])obj.readObject();
         boarddata=(Object[][])obj.readObject();
         notedata=(Object[][])obj.readObject();
         music.start();
         String n1=(String)obj.readObject();
         Ship[] s=(Ship[])obj.readObject();
         String n2=(String)obj.readObject();
         Ship[] s2=(Ship[])obj.readObject();
         numberofships=s.length;
         
         Square[][] g1=(Square[][])obj.readObject();
         boolean live1=(boolean)obj.readObject();
         int x1=(int)obj.readObject(),y1=(int)obj.readObject();
         
         Square[][] g2=(Square[][])obj.readObject();
         boolean live2=(boolean)obj.readObject();
         int x2=(int)obj.readObject(),y2=(int)obj.readObject();
         BoardCheck=(boolean[][])obj.readObject();
         int turntimer=(int)obj.readObject();
         playername=n1;
         Subscribe(new HumenPlayer(s.length,g1.length-1, g1[0].length-1,n1,s,g1,live1,x1,y1,turntimer));  
        Subscribe(new RandomComputerPlayer(s.length,g1.length-1, g1[0].length-1,s2,g2,live2,x2,y2,turntimer));
        obj.close();
        ins.close();
     } catch (FileNotFoundException ex) {   System.out.println("sdsds");  } 
     finally {
        /* try {
             ins.close();
         } catch (IOException ex) {  System.out.println("sdsds");     }*/
     }
     
      playerboard.repaintafterupdate();
        playerboard.fixtablessize(boarddata.length, boarddata[0].length);
        playerboard.LoadingComboBox(boarddata.length, boarddata[0].length-1);
        playerboard.setVisible(true);
        
     RunGame();
    }
     /// start switching between user and computer
    public void RunGame(){
        
        for (int i=0;i<numberoplayers;i++){    
            if (Objects.equals(Players.get(i).numofdestroyedships,numberofships))
            {
                Leave(Players.get(i));
            }
            if (Players.size()==1){
                Stop(Players.get(0));
                return ;
            }
            Square attackmove=Players.get(i).AttackOpponent();
            if (attackmove==null){
                if (i==1)
                i=-1;
                continue;
            }
            //know the player name and the attack point and add it to the list
            moves.add(new ToturialClass(Players.get(i).name,attackmove.x,attackmove.y,new Date()));
            //to know the targeted player
            int temp=i+1;
            if (temp==2)
                temp=0;
            AttackResult result=Players.get(temp).AcceptAttack(attackmove); 
            Players.get(i).AcceptAttackResult(result);
            playerboard.repaintafterupdate();
            
            if (i==1)
                i=-1;
        }
    }
    /// fire a player so he can't play anymore 
    public void Stop(Player player){
       
        if (Players.size()==1)
        {
            enddate=new Date();
            //write the result in a file 
            //read the last game id and plus it with 1
            int gameID=0;
            String result="";
            boolean playerwin=false;
            File f = new File(new File("").getAbsolutePath() + "\\src\\projectafterupdatev1\\pkg2\\GameRecords");
            ObjectInputStream obj=null;
            try {
                obj = new ObjectInputStream(new FileInputStream(f));
            } catch (IOException ex) {System.out.println("exeption for newing the obj in game id initllize");}
            while (true) {
                try {

                    FinishedGameRecord temp = (FinishedGameRecord) obj.readObject();
                    gameID=temp.gameID;
                } catch (EOFException e) {
                    try {
                        obj.close();
                    } catch (IOException ex) {}
                    break;
                } catch (IOException ex) {System.out.println("gameID initlizing IOE error");         
                } catch (ClassNotFoundException ex) {System.out.println("gameID initlizing Class not found error");
                }
            }
            
            gameID++;
            if (Objects.equals(Players.get(0).name, new String("Random PC")))
            {
                result="PC Win";
                
            }else
            {
                playerwin=true;
                result=Players.get(0).name+" Win";
            }
            //insert the new game record 
            FinishedGameRecord lastscore=new FinishedGameRecord(playername,"Random PC",startdate,enddate,gameID,result);
            try {
                FileOutputStream os=new FileOutputStream(new File(new File("").getAbsoluteFile()+"\\src\\projectafterupdatev1\\pkg2\\GameRecords"),true);
                AppendingObjectOutputStream obj2=new  AppendingObjectOutputStream(os);
                
                obj2.writeObject(lastscore);
                
                obj2.close();
                os.close();
            } catch (FileNotFoundException ex) {System.out.println("writeing finished game record 111a");} 
            catch (IOException ex){System.out.println("111b");}
           
            //writing to the score board 
            
            File f3 = new File(new File("").getAbsolutePath() + "\\src\\projectafterupdatev1\\pkg2\\ScoreBoard");
            try {
                AppendingObjectOutputStream obj4sore=new AppendingObjectOutputStream(new FileOutputStream(f3,true));
                
                obj4sore.writeObject(new ScoreRecord(playername, playerwin));
                
                obj4sore.close();
            } catch (FileNotFoundException ex) {System.out.println("File not Found obj 4 score");
            } catch (IOException ex) {System.out.println("IOEexception obj4score");
            }
            
            
            //insert the toturial records
            File f2 = new File(new File("").getAbsolutePath() + "\\src\\projectafterupdatev1\\pkg2\\ToturialFile");
            try {
                
                AppendingObjectOutputStream obj3=new AppendingObjectOutputStream(new FileOutputStream(f2,true));
                WritableToturialClass temp=new WritableToturialClass();
                temp.Initlize(moves.size());
                temp.gameID=gameID;
                temp.grid=boarddata;
                temp.note=notedata;
                temp.header=boardsheader;
                for(int i=0;i<moves.size();i++){
                    temp.add(moves.get(i).nameoftheattacker, moves.get(i).xaxis, moves.get(i).xaxis,moves.get(i).date);
                    moves.get(i);
                }
                obj3.writeObject(temp);
                obj3.close();
            } catch (FileNotFoundException ex) {System.out.println("File not Found obj 3 ");
            } catch (IOException ex) {System.out.println("IOEexception obj3");
            }
            //end of the adding operations
            
            WinnerDisplay win=new WinnerDisplay();
            win.setWinner(Players.get(0).name);
            //make the list disappear before the saving shit happen
            playerboard.repaintafterupdate();
            playerboard.setVisible(false);
            win.setLocation(500,300);
            win.setVisible(true);
            music.StopTheMusic();
            music.PlayWinner();
           
            
        }
     }//testing new stuff
    

     public class SaveThread extends Thread implements Serializable{
         
         public void run(){
             while(true){
             synchronized(savewaiter){
                 try {
                     savewaiter.wait();
                 } catch (InterruptedException ex) {}
             }
             savewaiter=1;
             String temp=NewGame.fileName;
             
             File filenames =new File(new File("").getAbsolutePath()+"\\src\\projectafterupdatev1\\pkg2\\filesNames");
             AppendingObjectOutputStream objfile=null;
                 try {
                     objfile = new AppendingObjectOutputStream(new FileOutputStream(filenames,true));
                     objfile.writeObject(temp);
                     
                     objfile.close();
                 } catch (FileNotFoundException ex) {
                 } catch (IOException ex) {
                 }
             //old save file javatest.ser
             File file=new File(new File("").getAbsolutePath()+"\\src\\projectafterupdatev1\\pkg2\\"+temp);
             BattleShipGame gameobject=BattleShipGame.this;
             try {
                     FileOutputStream fileStream = new FileOutputStream(file);
                     ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
                     /// Save the objects you want
                     //1-boarddata and note data for the GUI done
                     objectStream.writeObject(boardsheader);
                     for(int i=0;i<boarddata.length;i++){
                         for(int j=0;j<boarddata[0].length;j++){
                             System.out.println(boarddata[i][j]+" ");}
                             System.out.println();
                     }
                     objectStream.writeObject(boarddata);
                     objectStream.writeObject(notedata);
                     //2- player data : Name - ships visited done
                     objectStream.writeObject(gameobject.Players.get(0).name);
                      objectStream.writeObject(gameobject.Players.get(0).ships);
                     objectStream.writeObject(gameobject.Players.get(1).name);
                     objectStream.writeObject(gameobject.Players.get(1).ships);
                     //3- griddata:grid - bombs live then x then y
                     objectStream.writeObject(gameobject.Players.get(0).board.grid);
                     objectStream.writeObject(gameobject.Players.get(0).board.mybombs.live);
                     objectStream.writeObject(gameobject.Players.get(0).board.mybombs.x);
                     objectStream.writeObject(gameobject.Players.get(0).board.mybombs.y);
                     objectStream.writeObject(gameobject.Players.get(1).board.grid);
                     objectStream.writeObject(gameobject.Players.get(1).board.mybombspc.live);
                     objectStream.writeObject(gameobject.Players.get(1).board.mybombspc.x);
                     objectStream.writeObject(gameobject.Players.get(1).board.mybombspc.y);
                     
                     //4- the visited array
                     objectStream.writeObject(BattleShipGame.BoardCheck); 
                     //5-turn timer
                     objectStream.writeObject(GameOptions.Turntimerset);
                     objectStream.close();
                     fileStream.close();
                     // print the gui that Saving success 
                     System.out.println("success Saving");
                 //trying to save using the full class
                 /*file=new File(new File("").getAbsolutePath()+"\\src\\projectafterupdatev1\\pkg2\\forSavingHollyGame");
                 ObjectOutputStream obj=new ObjectOutputStream(new FileOutputStream(file));
                 obj.writeObject(gameobject);
                 obj.close();*/
                 } catch (FileNotFoundException e) {
                     System.out.println("File not found");
                 } catch (IOException e) {
                     System.out.println("Error initializing stream");
                 } 

             
             }
         }
     }
}
