/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectafterupdatev1.pkg2;

import java.io.Serializable;

/**
 *
 * @author Bassel
 */
public class ScoreRecord implements Serializable{
 String name;
 boolean win;
 static final long  serialVersionUID=5;
 ScoreRecord(String name,boolean win){
     this.name=name;
     this.win=win;
 }

    ScoreRecord() {
    }
}
