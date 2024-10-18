package test.nz.ac.wgtn.swen225.lc.persistency;

import nz.ac.wgtn.swen225.lc.persistency.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

public class PersistencyTest {

  @TempDir
  Path tempDir;

  @Test
  public void testLoadFromFile() throws IOException {
    // Create temporary file with some JSON content
    Path tempFile = tempDir.resolve("test.json");
    String jsonContent = "{\"name\": \"John Doe\", \"age\": 30}";
    Files.writeString(tempFile, jsonContent);

    // Load the JSON from file
    JSONType result = Persistency.loadFromFile(tempFile);

    assertTrue(result instanceof JSONObject);
    JSONObject jsonObject = (JSONObject) result;

    assertEquals(new JSONString("John Doe"), jsonObject.get("name"));
    assertEquals(new JSONLong(30L), jsonObject.get("age"));
  }

  @Test
  public void testLoadFromFile_FileNotFound() {
    // Use a non-existent file path
    Path invalidFile = tempDir.resolve("non_existent_file.json");

    // Expect an IOException to be thrown
    Exception exception = assertThrows(IOException.class, () -> {
      Persistency.loadFromFile(invalidFile);
    });

    String expectedMessage = "File not found";
    assertTrue(exception.getMessage().contains(expectedMessage));
  }
}
