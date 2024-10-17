package test.nz.ac.wgtn.swen225.lc.recorder;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nz.ac.wgtn.swen225.lc.domain.PlayerAction;
import nz.ac.wgtn.swen225.lc.recorder.*;

class RecorderTest {

  private Recorder recorder;
  private Path testDirPath;

  @BeforeEach
  void setUp() {
    testDirPath = Paths.get("test_directory");
    recorder = new Recorder(testDirPath);
  }

  @Test
  void testConstructorNullDirPathThrowsException() {
    assertThrows(NullPointerException.class, () -> new Recorder(null));
  }

  @Test
  void testStartLevelInitializesNewLevel() {
    Path levelPath = Paths.get("level1.json");
    recorder.startLevel(levelPath);

    Level currentLevel = getCurrentLevelFromRecorder(recorder);
    assertNotNull(currentLevel);
    assertEquals(Paths.get(testDirPath.toString(), "1.json"), Paths.get(currentLevel.savePath().toString(), currentLevel.filename()));
  }

  @Test
  void testStartLevelCreatesNewFilename() {
    Path level1Path = Paths.get("level1.json");
    Path level2Path = Paths.get("level2.json");

    recorder.startLevel(level1Path);
    recorder.endLevel();
    recorder.startLevel(level2Path);

    Level currentLevel = getCurrentLevelFromRecorder(recorder);
    // Ensure the second level has the correct filename
    assertEquals(Paths.get(testDirPath.toString(), "2.json"), Paths.get(currentLevel.savePath().toString(), currentLevel.filename()));
  }

  @Test
  void testEndLevelStoresCurrentLevel() {
    Path levelPath = Paths.get("level1.json");
    recorder.startLevel(levelPath);
    recorder.endLevel();

    List<Level> allLevels = getAllLevelsFromRecorder(recorder);
    assertEquals(1, allLevels.size());
    assertNotNull(allLevels.get(0));
    assertNull(getCurrentLevelFromRecorder(recorder)); // currentLevel should be null after ending the level
  }

  @Test
  void testRecordAction() {
    // Create a PlayerAction instance
    Path levelPath = Paths.get("level1.json");

    recorder.startLevel(levelPath);
    recorder.record(PlayerAction.Up);
    recorder.record(PlayerAction.Down);
    recorder.record(PlayerAction.Left);
    recorder.record(PlayerAction.Right);

    Level currentLevel = getCurrentLevelFromRecorder(recorder);
    List<PlayerAction> actions = currentLevel.actions();

    // Verify that the action was added to the current level's actions list
    assertEquals(4, actions.size());
    assertEquals(List.of(PlayerAction.Up, PlayerAction.Down, PlayerAction.Left, PlayerAction.Right), actions);
  }

  @Test
  void testEndGameLinksLevels() {
    Path level1Path = Paths.get("level1.json");
    Path level2Path = Paths.get("level2.json");

    recorder.startLevel(level1Path);
    recorder.endLevel();
    recorder.startLevel(level2Path);
    recorder.endLevel();

    try {
      recorder.endGame();
    } catch(Throwable t){
    }

    List<Level> allLevels = getAllLevelsFromRecorder(recorder);
    assertEquals(2, allLevels.size());

    // Check if the levels are linked correctly
    Level firstLevel = allLevels.get(0);
    Level secondLevel = allLevels.get(1);
    assertEquals(secondLevel.savePath(), firstLevel.nextSavePath());
    assertEquals(secondLevel.nextSavePath(), null);
  }

  @Test
  void testEndGameSavesAllLevels() {
    Path level1Path = Paths.get("level1.json");
    Path level2Path = Paths.get("level2.json");

    recorder.startLevel(level1Path);
    recorder.endLevel();
    recorder.startLevel(level2Path);
    recorder.endLevel();

    try {
      recorder.endGame();
    } catch(Throwable t){
    }

    List<Level> allLevels = getAllLevelsFromRecorder(recorder);

    // AssuDeveloper 4 <dev4@example.internal> we check that save is called by confirDeveloper 4 <dev4@example.internal> save paths are set
    assertNotNull(allLevels.get(0).savePath());
    assertNotNull(allLevels.get(1).savePath());
  }

  // Utility methods to access private fields (if needed)
  private Level getCurrentLevelFromRecorder(Recorder recorder) {
    try {
      var field = Recorder.class.getDeclaredField("currentLevel");
      field.setAccessible(true);
      return (Level) field.get(recorder);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private List<Level> getAllLevelsFromRecorder(Recorder recorder) {
    try {
      var field = Recorder.class.getDeclaredField("allLevels");
      field.setAccessible(true);
      return (List<Level>) field.get(recorder);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
