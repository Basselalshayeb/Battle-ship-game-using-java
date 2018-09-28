package projectafterupdatev1.pkg2;
import java.io.Serializable;
import java.util.Objects;
import java.util.*;
//Partially is for new destroyed part of ship
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Bassel
 */

enum AttackResult{Partially,Tottaly,Lastship,Disappoint,BombExploded}
 abstract public class Player implements Serializable{
     String name;
    Integer attackx,attacky;
    Integer numofdestroyedships=0;
    Ship[] ships;
    Grid board,note;
 int  length,width,numberofships;
 Player(){}
    Player(int numberofships,int length,int width){
        this.length=length;
        this.width=width;
        this.numberofships=numberofships;
       // ships=new Ship[5];
        board=new Grid(length,width);
        note=new Grid(length,width);
        // constant distribution 
        //testing new stuff
        //ships=Projectafterupdatev12.game.userships;
           
            //initlizq board with our ships
       // board.SetGrid();
            // if we want to distribute in another combination the user enter it.
            
    }
    ///Accept the attack from the other player 
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
            //   numberoffallships++
        }
        if (Objects.equals(finall,AttackResult.Disappoint)){
            //if bomb here isbombs here should chek if bomb is alive too and then kill the bomb here instant
            if (board.IsBombHerepc(square.x,square.y)){
                finall=AttackResult.BombExploded;
                Integer x1=square.x,y1=square.y;
                Vector<Integer> xaxis=new Vector<Integer>(),yaxis=new Vector<Integer>();
                //8 ifs here
                if (x1+1<=length){
                    xaxis.add(x1+1);
                    yaxis.add(y1);
                }
                if (x1-1>=1)
                {
                    xaxis.add(x1-1);
                    yaxis.add(y1);
                }
                if (y1+1<=width)
                {
                    yaxis.add(y1+1);
                    xaxis.add(x1);
                }
                if(y1-1>=1){
                    yaxis.add(y1-1);
                    xaxis.add(x1);
                }//corner
                if(x1+1<=length && y1+1<=width){
                    yaxis.add(y1+1);
                    xaxis.add(x1+1);
                }
                if(x1-1>=1 && y1-1>=1){
                    xaxis.add(x1-1);
                    yaxis.add(y1-1);
                }
                if(x1+1<=length && y1-1>=1){
                    xaxis.add(x1+1);
                    yaxis.add(y1-1);
                }
                if(x1-1>=1 &&  y1+1<=width){
                    xaxis.add(x1-1);
                    yaxis.add(y1+1);
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
        //if for LastShip
        if (numofdestroyedships.equals(ships.length))
        {
            return AttackResult.Lastship;
        }
        return finall;
        
        
    }
    //accept the result and give the message and print the result on the note 
    //edit the accept attack result
    public void AcceptAttackResult(AttackResult attackResult){
        
        if (Objects.equals(attackResult, AttackResult.BombExploded))
        {
            note.SetGridCell(attackx, attacky, SquareState.water);
            
            
            
        }else 
        if(Objects.equals(attackResult, AttackResult.Partially) || Objects.equals(attackResult, AttackResult.Lastship) ||Objects.equals(attackResult, AttackResult.Tottaly))
        {
            note.SetGridCell(attackx, attacky, SquareState.destroyedpartofship);
        }
        else if (Objects.equals(attackResult, AttackResult.Disappoint))
            note.SetGridCell(attackx,attacky, SquareState.water);
        
    }
    /// Attack the opponent overrided to the userPlayer and ComputerPlayer  
    abstract public Square AttackOpponent(); 
    //comunicate with the bombs and start it 
    public void startbomb(){
        board.bombsinitlize();
    }
    public void startbombpc(){
        board.bombsinitlizepc();
    }
    
    
    public class Grid implements Serializable{ 
        Bomb mybombs;
        Bombpc mybombspc;
        public int length, width;
        public Square[][] grid;

        Grid(int length, int width){
            this.length = length;
            this.width = width;
            grid = new Square[length + 1][width + 1];
            
            for (int i = 1; i <= length; i++) {
                for (int j = 1; j <= width; j++) {
                    grid[i][j] = new Square(i, j, SquareState.unknown);
                }
            } 
        }
        Grid(int length, int width,Square[][] grid){
            this.length=length;
            this.width=width;
            this.grid=grid;
        }
        //function to intilize the bomb with the loaded ifo
       public void NewBomb(boolean b,int x,int y){
           mybombs=new Bomb();
           mybombs.x=x;
           mybombs.y=y;
           mybombs.live=b;
           mybombs.start();
       }
       public void NewBombpc(boolean b,int x,int y){
           mybombspc=new Bombpc();
           mybombspc.x=x;
           mybombspc.y=y;
           mybombspc.live=b;
           mybombspc.start();
       }
      //function to initlize the bombs when needed
        public void bombsinitlize(){
            mybombs=new Bomb();
            mybombs.initlizebomb();
            mybombs.start();
        }
        public void bombsinitlizepc(){
             mybombspc=new Bombpc();
            mybombspc.initlizebomb();
            mybombspc.start();
        }
        public void PrintGrid() {
            for (int i = 1; i <= length; i++) {
                for (int j = 1; j <= width; j++) {
                    if (mybombspc.x==i && mybombspc.y==j)
                        System.out.print("& ");
                    else
                    grid[i][j].PrintState();
                }
                System.out.println();
            }
        }
        
        //set the specific ships we have
        public void SetGrid(){

            for (int i=0;i<ships.length;i++){
               for(int j=0;j<ships[i].v.size();j++){
                   int x=ships[i].v.elementAt(j).x;
                   int y=ships[i].v.elementAt(j).y;
                   grid[x][y].ChangeState(SquareState.partofship);
                   //hon mnshan tt3ba al player board
                   BattleShipGame.boarddata[x-1][y]="#";
               }
            }
             for (int i = 1; i <= length; i++) {
                for (int j = 1; j <= width; j++) {
                    if (!Objects.equals(grid[i][j].state,SquareState.partofship))
                    grid[i][j].ChangeState(SquareState.water);
                }
            }
            
        }
        
         public void SetGridPc(){

            for (int i=0;i<ships.length;i++){
               for(int j=0;j<ships[i].v.size();j++){
                   int x=ships[i].v.elementAt(j).x;
                   int y=ships[i].v.elementAt(j).y;
                   grid[x][y].ChangeState(SquareState.partofship);
               }
            }
             for (int i = 1; i <= length; i++) {
                for (int j = 1; j <= width; j++) {
                    if (!Objects.equals(grid[i][j].state,SquareState.partofship))
                    grid[i][j].ChangeState(SquareState.water);
                }
            }
            
        }

        //without bisher
        //to change the square state

        public void GridAcceptAttack(int x, int y) {
                grid[x][y].ChangeState(SquareState.destroyedpartofship);

        }
        public void SetGridCell(int x,int y,SquareState state){
            if (!Objects.equals(grid[x][y].state,SquareState.destroyedpartofship))
            grid[x][y].ChangeState(state);
        }
        
        //isbomb here
        public boolean IsBombHere(int x,int y){
            if (mybombs.x==x && mybombs.y==y && mybombs.live==true)
            {
                mybombs.killthebomb();
                return true;
            }
            return false;
        }
        public boolean IsBombHerepc(int x,int y){
            if (mybombspc.x==x && mybombspc.y==y && mybombspc.live==true)
            {
                mybombspc.killthebomb();
                return true;
            }
            return false;
        }
        public class Bomb extends Thread implements Serializable{
        int x=-1,y=-1;
        public void initlizebomb(){
            boolean check=false;
            int temp=400;
            while (check==false && temp>0){
            Random r=new Random();
             int xB=r.nextInt(length)+1;
             int yB=r.nextInt(width)+1;
             if (Objects.equals(grid[xB][yB].state, SquareState.water)){
                 this.x=xB;this.y=yB;check=true;
                 BattleShipGame.boarddata[xB-1][yB]="&";
             }
            temp--;
            }
            if (check==false)
                live=false;
            BattleShipGame.playerboard.repaintafterupdate();
        }
              boolean live=true;
              public void run(){
            while(live==true){
                BattleShipGame.playerboard.repaintafterupdate();
             try{
                sleep(1500);
                }catch(Exception e){}
           //hon lazm 7rk all3'm move the bombs
             Random r=new Random();
             int t=r.nextInt(4);
             //0 right 1 left 2up 3 bottom 
             if (t==0){
                 x++;
                 
                 //chek if the square is empty or not if yes go there if not sleep again
                 if (x>length || !Objects.equals(grid[x][y].state,SquareState.water)){
                     x--; 
                     continue;}
                 else{
                     //System.out.println(x-2+" "+length);
                BattleShipGame.boarddata[x-2][y]=".";
                BattleShipGame.boarddata[x-1][y]="&";}
                 
             }else if (t==1){
                 x--;
                 
                 if (x<1 || !Objects.equals(grid[x][y].state,SquareState.water)){
                     x++;
                     continue;}else {
                     BattleShipGame.boarddata[x-1][y]="&";
                     BattleShipGame.boarddata[x][y]=".";
                 }
             }else if (t==2){
                 y--;
                 
                 if (y<1 || !Objects.equals(grid[x][y].state,SquareState.water)){
                     y++;
                     continue;}else {
                     BattleShipGame.boarddata[x-1][y]="&";
                      BattleShipGame.boarddata[x-1][y+1]=".";
                 }
                
             }else if (t==3){
                 y++;
                 if (y>width || !Objects.equals(grid[x][y].state,SquareState.water)){
                     y--;
                     continue;}else {
                    //  System.out.println(y+" "+width);
                 BattleShipGame.boarddata[x-1][y-1]=".";
                  BattleShipGame.boarddata[x-1][y]="&";}
             }
             
            }
        }
              public void killthebomb(){
                  this.live=false;
                  this.x=-1;
                  this.y=-1;
                  this.stop();
              }
    }//end of bomb class
        
        public class Bombpc extends Thread implements Serializable{
        int x=-1,y=-1;
        public void initlizebomb(){
            boolean check=false;
            int temp=400;
            while (check==false && temp>0){
            Random r=new Random();
             int xB=r.nextInt(length)+1;
             int yB=r.nextInt(width)+1;
             
             if (Objects.equals(grid[xB][yB].state, SquareState.water)){
                 this.x=xB;this.y=yB;check=true;
             }
            temp--;
            }
            if (check==false)
            {
                live=false;
            }
        }
              boolean live=true;
              public void run(){
            while(live==true){
             try{
                sleep(1500);
                }catch(Exception e){}
           //hon lazm 7rk all3'm move the bombs
             Random r=new Random();
             int t=r.nextInt(4);
             //0 right 1 left 2up 3 bottom 
             if (t==0){
                 x++;
                 
                 //chek if the square is empty or not if yes go there if not sleep again
                 if (x>length || !Objects.equals(grid[x][y].state,SquareState.water)){
                     x--; 
                     continue;}
                 
                 
             }else if (t==1){
                 x--;
                 
                 if (x<1 || !Objects.equals(grid[x][y].state,SquareState.water)){
                     x++;
                     continue;}
             }else if (t==2){
                 y--;
                 
                 if (y<1 || !Objects.equals(grid[x][y].state,SquareState.water)){
                     y++;
                     continue;}
                
             }else if (t==3){
                 y++;
                 if (y>width || !Objects.equals(grid[x][y].state,SquareState.water)){
                     y--;
                     continue;}
             }
             
            }
        }
              public void killthebomb(){
                  this.live=false;
                  this.x=-1;
                  this.y=-1;
                  this.stop();
              }
    }
    }//end of grid class   
    
    
}
