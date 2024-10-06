package test.nz.ac.wgtn.swen225.lc.recorder;

import nz.ac.wgtn.swen225.lc.recorder.Recorder;
import nz.ac.wgtn.swen225.lc.domain.PlayerAction;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;


/**
 * Test class for the Recorder.
 */
public class RecorderTest {

  private Recorder recorder;

  @BeforeEach
  public void setUp() {
    // Initialize the Recorder with a valid levelID
    recorder = new Recorder("Level1");
  }

  @Test
  public void testConstructor_nullLevelID_throwsException() {
    // Test that passing null to the constructor throws NullPointerException
    assertThrows(NullPointerException.class, () -> new Recorder(null));
  }

  @Test
  public void testConstructor_emptyLevelID_throwsException() {
    // Test that passing an empty string to the constructor throws IllegalArgumentException
    assertThrows(IllegalArgumentException.class, () -> new Recorder(""));
  }

  @Test
  public void testConstructor_validLevelID_initializesRecorder() {
    // Test that the Recorder is correctly initialized with a valid levelID
    Recorder recorder = new Recorder("Level1");
    assertNotNull(recorder);
  }

  @Test
  public void testRecord_addsPlayerAction() {
    // Test that a PlayerAction is correctly added to the playerActions list
    recorder.record(PlayerAction.Up);
    recorder.record(PlayerAction.Down);
    
    // Validate that the action has been added
    List<PlayerAction> actions = recorder.playerActions();
    assertEquals(2, actions.size());
    assertEquals(List.of(PlayerAction.Up, PlayerAction.Down), actions);
  }

  @Test
  public void testRecord_nullPlayerAction_throwsException() {
    // Test that recording a null PlayerAction throws a NullPointerException
    assertThrows(NullPointerException.class, () -> recorder.record(null));
  }
}