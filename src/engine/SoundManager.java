package engine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundManager {
    private Clip clip;

    public SoundManager(String fileName){
        try{
            File file = new File(fileName);
            if(file.exists()){
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
            else{
                throw new RuntimeException("sound file not found");
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
        clip.close();
    }
}