/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectafterupdatev1.pkg2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;

/**
 *
 * @author Bassel
 */
public class MusicThread extends Thread implements Serializable{
    javazoom.jl.player.Player player;
    public void StopTheMusic(){
        player.close();
    }
    public void run() {
            try { 
               File file = new File(new File("").getAbsolutePath()+"\\src\\projectafterupdatev1\\pkg2\\alan");
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                try {
                     player = new javazoom.jl.player.Player(bis);
                     player.play();
                } catch (JavaLayerException ex) {
                }
            } catch (IOException e) {
            }
            
        
    }
    public void PlayWinner(){
        try { 
               File file = new File(new File("").getAbsolutePath()+"\\src\\projectafterupdatev1\\pkg2\\Winnermusic");
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis);
                try {
                    player = new javazoom.jl.player.Player(bis);
                    player.play();
                } catch (JavaLayerException ex) {
                }
            } catch (IOException e) {
            }

    }
}
