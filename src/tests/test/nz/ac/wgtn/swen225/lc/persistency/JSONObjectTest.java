package test.nz.ac.wgtn.swen225.lc.persistency;

import nz.ac.wgtn.swen225.lc.persistency.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.json.JSONArray;

import java.math.BigDecimal;
import java.util.List;



public class JSONObjectTest {

  private JSONObject obj;

  @BeforeEach
  public void setUp() {
    obj = new JSONObject();  // Create a fresh instance before each test
  }

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
  public void testPutAndGetBigDecimal() {
    JSONObject obj = new JSONObject();
    BigDecimal bigDecimalValue = new BigDecimal("1500.5"); // Use BigDecimal instead of double
    obj.put("salary", bigDecimalValue);

    assertEquals(new JSONDouble(1500.5), obj.get("salary"));
  }


  @Test
  public void testConvertJSONArrayToList() {
    JSONArray jsonArray = new JSONArray();
    jsonArray.put("John Doe");
    jsonArray.put(25);
    jsonArray.put(true);
    jsonArray.put(1500.5);

    List<JSONType> jsonList = JSONObject.convertJSONArrayToList(jsonArray);

    assertEquals(3, jsonList.size());
    assertEquals(new JSONString("John Doe"), jsonList.get(0));
    assertEquals(new JSONLong(25L), jsonList.get(1));
    assertEquals(JSONBool.True, jsonList.get(2));
  }

  @Test
  public void testToString() {
    JSONObject obj = new JSONObject();
    obj.put("name", "John Doe");
    obj.put("age", 25);

    String expected = "JSONObject: {name=JSONString[value=John Doe], age=JSONLong[value=25]}";
    //String expected = "JSONObject: {name=JSONString[value=John Doe], isAdmin=True, age=JSONLong[value=25]}";
    assertEquals(expected, obj.toString());
  }
}