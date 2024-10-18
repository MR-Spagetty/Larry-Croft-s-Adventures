package nz.ac.wgtn.swen225.lc.renderer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Plays audio files from resources.
 *
 * @author Developer 2 <dev2@example.internal> 300651343
 */
public class Sound {
  /**
   * Finds a sound file and plays it. Acceptable filenames are: gameStart gameWin gameDeath
   * buttonPress movement
   *
   * @param filename used to identify sound file from resources
   */
  public void playSound(String filename) {
    try {
      // Get audio file as stream
      File file = Path.of("src", "resources", filename + ".wav").toFile();
      AudioInputStream sound = AudioSystem.getAudioInputStream(file);
      // Convert to a clip
      Clip clip = AudioSystem.getClip();
      clip.open(sound);
      // Play clip
      clip.start();
      // Wait for it to end then end it
      while (clip.isRunning()) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
        }
      }
      clip.close();
    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
      System.err.println("Sound.play():\n" + e);
    }
  }
}
