package nz.ac.wgtn.swen225.lc.renderer;

import java.io.IOException;
import javax.sound.sampled.*;

public class Sound {
    private AudioInputStream sound;

    public Sound(Object o) {
        this.sound = resolveSound(o);
    }

    private AudioInputStream resolveSound(Object o) {
        try{
            return switch (o) {
                case Sound s -> AudioSystem.getAudioInputStream(Sound.class.getClassLoader().getResource("placeholder.wav"));
                default -> null;
            };
        }catch(UnsupportedAudioFileException | IOException e){
            System.err.println("Sound.resolveSound(Object), failed to read file:\n" + e);
            return null;
        }
    }
    public void play() {
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
        }catch(LineUnavailableException | IOException e){
            System.err.println("Sound.play(), failed to read file:\n" + e);
        }
    }
}
