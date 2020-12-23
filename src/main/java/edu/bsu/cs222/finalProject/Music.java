package edu.bsu.cs222.finalProject;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {

    Long currentFrame;
    Clip clip;
    public String clipStatus;

    public Music()
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        String filePath = "src/main/resources/music/encomium.wav";
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream( new File( filePath ).getAbsoluteFile() );
        clip = AudioSystem.getClip();
        clip.open( audioInputStream );
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void play() {
        clip.start();
        clipStatus = "playing";
    }

    public void pause() {
        if ( clipStatus.equals("paused"))
        { System.out.println("audio is already paused");
            return; }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        clipStatus = "paused";
    }

    public void stop() {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

//    public void resumeAudio() throws UnsupportedAudioFileException,
//            IOException, LineUnavailableException {
//        if ( clipStatus.equals("playing")) {
//            System.out.println("Audio is already "+
//                    "being played");
//            return;
//        }
//        clip.close();
//        resetAudioStream();
//        clip.setMicrosecondPosition(currentFrame);
//        this.play();
//    }



//    public void restart() throws IOException, LineUnavailableException,
//            UnsupportedAudioFileException {
//        clip.stop();
//        clip.close();
//        resetAudioStream();
//        currentFrame = 0L;
//        clip.setMicrosecondPosition(0);
//        this.play();
//    }



//    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
//            LineUnavailableException
//    {
//        audioInputStream = AudioSystem.getAudioInputStream(
//                new File(filePath).getAbsoluteFile());
//        clip.open(audioInputStream);
//        clip.loop(Clip.LOOP_CONTINUOUSLY);
//    }
}
