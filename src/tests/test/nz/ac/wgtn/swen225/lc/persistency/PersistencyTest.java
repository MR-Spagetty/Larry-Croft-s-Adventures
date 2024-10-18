package test.nz.ac.wgtn.swen225.lc.persistency;

import nz.ac.wgtn.swen225.lc.persistency.JSONList;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

public class PersistencyTest {

  @Test
  public void testPutAndGetString() {
    JSONObject obj = new JSONObject();
    obj.put("key1", "value1");
    assertEquals("value1", obj.get("key1").toString(), "Should store and retrieve a string value");
  }

  @Test
  public void testPutAndGetInteger() {
    JSONObject obj = new JSONObject();
    obj.put("key2", 42);
    assertEquals(42, obj.get("key2").toLong(), "Should store and retrieve an integer as a long");
  }

  @Test
  public void testPutAndGetBoolean() {
    JSONObject obj = new JSONObject();
    obj.put("key3", true);
    assertTrue(obj.get("key3").toBool(), "Should store and retrieve a boolean value");
  }

  @Test
  public void testPutJSONObject() {
    JSONObject obj = new JSONObject();
    JSONObject nestedObj = new JSONObject();
    nestedObj.put("nestedKey", "nestedValue");
    obj.put("key4", nestedObj);

    assertTrue(obj.get("key4") instanceof JSONObject, "Should store and retrieve a nested JSONObject");
    assertEquals("nestedValue", ((JSONObject) obj.get("key4")).get("nestedKey").toString());
  }

  @Test
  public void testLoadFromFile() throws IOException {
    // AssuDeveloper 4 <dev4@example.internal> "testObject.json" is a valid file for testing
    JSONType loadedObject = Persistency.loadFromFile("src/main/nz/ac/wgtn/swen225/lc/persistency/testObject.json");
    assertNotNull(loadedObject, "The object should be loaded from the file");
  }

  @Test
  public void testSaveToFile() throws IOException {
    JSONObject obj = new JSONObject();
    obj.put("key1", "value1");
    Persistency.saveToFile(obj, "testOutput.json");

    JSONType loadedObject = Persistency.loadFromFile("testOutput.json");
    assertEquals(obj, loadedObject, "The saved object should match the loaded object");
  }

  @Test
  public void testJSONArrayConversion() {
    JSONObject obj = new JSONObject();
    obj.put("key", List.of(1, 2, 3)); // AssuDeveloper 4 <dev4@example.internal> List is converted to JSONArray

    JSONType result = obj.get("key");
    assertTrue(result instanceof JSONList, "Should store a list as a JSONList");
    assertEquals(3, ((JSONList) result).size(), "The JSONList should have 3 elements");
  }
}
