package nz.ac.wgtn.swen225.lc.renderer;

import java.io.IOException;
import java.nio.file.Path;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
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
      // Get input stream from file
      AudioInputStream sound =
          AudioSystem.getAudioInputStream(Path.of("src", "resources", filename + ".wav").toFile());
      // Get format
      AudioFormat format = sound.getFormat();
      // Setup Dataline
      DataLine.Info info = new DataLine.Info(Clip.class, format);
      // Convert to a clip
      Clip clip = (Clip) AudioSystem.getLine(info);
      clip.open(sound);
      // Play clip
      clip.start();
    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
      System.err.println("Sound.play():\n" + e);
    }
  }
}
