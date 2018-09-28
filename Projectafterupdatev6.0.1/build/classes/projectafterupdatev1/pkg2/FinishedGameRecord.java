/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectafterupdatev1.pkg2;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Bassel
 */
public class FinishedGameRecord implements Serializable{
    //static final long serilVersionUID=2;
    Date startadate,enddate;
    String nameoftheplayer,nameoftheenemy,theresult;
    boolean won;
    int gameID;
     static final long  serialVersionUID=2;
    FinishedGameRecord(){}
    FinishedGameRecord(String name,String nameoftheenemy,Date startdate,Date enddate,int gameID,String theresult){
    this.nameoftheplayer=name;
    this.startadate=startdate;
    this.enddate=enddate;
    this.gameID=gameID;
    this.nameoftheenemy=nameoftheenemy;
    this.theresult=theresult;
    }
    
}
