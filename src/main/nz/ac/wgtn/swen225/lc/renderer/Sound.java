package nz.ac.wgtn.swen225.lc.renderer;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
  private AudioInputStream sound;

  /**
   * Description TODO
   *
   * @param o
   */
  public Sound(Object o) {
    this.sound = resolveSound(o);
  }

  /**
   * Description TODO
   *
   * @param o
   * @return
   */
  private AudioInputStream resolveSound(Object o) {
    try {
      return switch (o) {
        case Sound s ->
            AudioSystem.getAudioInputStream(
                Sound.class.getClassLoader().getResource("placeholder.wav"));
        default -> null;
      };
    } catch (UnsupportedAudioFileException | IOException e) {
      System.err.println("Sound.resolveSound(Object):\n" + e);
      return null;
    }
  }

  /** Description TODO */
  public void play() {
    try {
      Clip clip = AudioSystem.getClip();
      clip.open(sound);
    } catch (LineUnavailableException | IOException e) {
      System.err.println("Sound.play():\n" + e);
    }
  }
}
