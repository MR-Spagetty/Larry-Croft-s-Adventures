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

  @Test
  public void testLoadFromFile_NestedJSON() throws IOException {
    // Create temporary file with nested JSON content
    Path tempFile = tempDir.resolve("nested_test.json");
    String jsonContent = """
    {
      "person": {
        "name": "John Doe",
        "age": 30,
        "address": {
          "street": "123 Main St",
          "city": "Wellington"
        }
      },
      "phoneNumbers": [
        "123456789",
        "987654321"
      ]
    }
    """;
    Files.writeString(tempFile, jsonContent);

    // Load the JSON from file
    JSONType result = Persistency.loadFromFile(tempFile);

    assertTrue(result instanceof JSONObject);
    JSONObject jsonObject = (JSONObject) result;

    JSONObject person = (JSONObject) jsonObject.get("person");
    assertEquals(new JSONString("John Doe"), person.get("name"));
    assertEquals(new JSONLong(30L), person.get("age"));

    JSONObject address = (JSONObject) person.get("address");
    assertEquals(new JSONString("123 Main St"), address.get("street"));
    assertEquals(new JSONString("Wellington"), address.get("city"));

    JSONList phoneNumbers = (JSONList) jsonObject.get("phoneNumbers");
    assertEquals(new JSONString("123456789"), phoneNumbers.getElements().get(0));
    assertEquals(new JSONString("987654321"), phoneNumbers.getElements().get(1));
  }

  @Test
  void testLoadFromFile_MalformedJSON() throws IOException {
    Path tempFile = Files.createTempFile("test", ".json");
    Files.writeString(tempFile, "{malformed json}");  // Write malformed JSON

    assertThrows(IllegalArgumentException.class, () -> {
      Persistency.loadFromFile(tempFile);
    });

    Files.delete(tempFile);  // Clean up
  }


  @Test
  public void testSaveToFile() throws IOException {
    // Create a sample JSONObject to save
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("name", "Jane Doe");
    jsonObject.put("age", 25L);

    JSONObject address = new JSONObject();
    address.put("street", "456 Side St");
    address.put("city", "Auckland");

    jsonObject.put("address", address);

    // Create a temporary file
    Path tempFile = Files.createTempFile("json_output", ".json");

    // Save the JSONObject to the file
    Persistency.saveToFile(jsonObject, tempFile);

    // Read the file back in and parse it
    String fileContent = Files.readString(tempFile);
    JSONObject loadedObject = (JSONObject) Persistency.loadFromFile(tempFile);

    // Parse the expected and actual JSON into JSONObject instances
    String expectedJson = """
  {
    "name": "Jane Doe",
    "age": 25,
    "address": {
      "street": "456 Side St",
      "city": "Auckland"
    }
  }
  """;

    JSONObject expectedObject = (JSONObject) Persistency.parseJSONString(expectedJson);

    // Assert that the expected and actual JSONObjects are equal, ignoring order
    assertEquals(expectedObject.toString(), loadedObject.toString());

    // Clean up the temporary file
    Files.deleteIfExists(tempFile);
  }


  @Test
  public void testSaveAndLoad() throws IOException {
    // Create a temporary file
    Path tempFile = tempDir.resolve("roundtrip_test.json");

    // Create a sample JSONObject
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("name", new JSONString("Alice"));
    jsonObject.put("age", new JSONLong(35L));

    JSONList hobbies = new JSONList();
    hobbies.add("reading");
    hobbies.add("cycling");
    jsonObject.put("hobbies", hobbies);

    // Save the JSON object to the file
    Persistency.saveToFile(jsonObject, tempFile);

    // Load the JSON from file
    JSONType result = Persistency.loadFromFile(tempFile);

    assertTrue(result instanceof JSONObject);
    JSONObject loadedJson = (JSONObject) result;

    assertEquals(new JSONString("Alice"), loadedJson.get("name"));
    assertEquals(new JSONLong(35L), loadedJson.get("age"));

    JSONList loadedHobbies = (JSONList) loadedJson.get("hobbies");
    assertEquals(new JSONString("reading"), loadedHobbies.getElements().get(0));
    assertEquals(new JSONString("cycling"), loadedHobbies.getElements().get(1));
  }



}