package test.nz.ac.wgtn.swen225.lc.persistency;

import nz.ac.wgtn.swen225.lc.persistency.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JSONObjectTest {

  @Test
  public void testPutAndGetString() {
    JSONObject obj = new JSONObject();
    obj.put("name", "John Doe");

    assertEquals(new JSONString("John Doe"), obj.get("name"));
  }

  @Test
  public void testPutAndGetInteger() {
    JSONObject obj = new JSONObject();
    obj.put("age", 25);

    assertEquals(new JSONLong(25L), obj.get("age"));
  }

  @Test
  public void testPutAndGetBoolean() {
    JSONObject obj = new JSONObject();
    obj.put("isAdmin", true);

    assertEquals(JSONBool.True, obj.get("isAdmin"));
  }

  @Test
  public void testToString() {
    JSONObject obj = new JSONObject();
    obj.put("name", "John Doe");
    obj.put("age", 25);

    String expected = "JSONObject: {name=JSONString[value=John Doe], age=JSONLong[value=25]}";
    assertEquals(expected, obj.toString());
  }
}