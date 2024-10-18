package nz.ac.wgtn.swen225.lc.renderer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
  /**
   * Finds a sound file and plays it. Acceptable filenames are: gameStart gameWin gameDeath
   * buttonPress movement
   *
   * @param filename used to identify sound file from resources
   */
  public void playSound(String filename) {
    try {
      //
      AudioInputStream sound =
          AudioSystem.getAudioInputStream(
              new File(Path.of("src", "resources", filename + ".wav").toString()));
      // Convert to a clip and play
      Clip clip = AudioSystem.getClip();
      clip.open(sound);
    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
      System.err.println("Sound.play():\n" + e);
    }
  }
}
