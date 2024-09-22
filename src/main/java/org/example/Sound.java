package org.example;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {

    private AudioInputStream audioInputStream;
    private Clip clip;



    public void playSound(String source) {
        try {
            // ציין כאן את הנתיב המלא לקובץ הסאונד
            File soundFile = new File(source);
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);

//            audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource("Lobby_audio.wav").toURI()).getAbsoluteFile());

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
//            clip.start();
//            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }




    public void startPlay(){
        this.clip.start();
    }

    public void loopPlay(){
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopPlay() {
        this.clip.close();
    }
}
