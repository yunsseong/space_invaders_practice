package engine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class SoundManager {
    private Clip clip;

    public SoundManager(String filePath){
        try{
            File file = new File(filePath);
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

    /**  play sound once */
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

    public void setVolume(float volume){
        float minimum = -40, maximum = 6;
        float value = Math.min((minimum + (0.46f*volume)), maximum);
        FloatControl floatControl = (FloatControl)(clip.getControl(FloatControl.Type.MASTER_GAIN));
        floatControl.setValue(value);
    }
}