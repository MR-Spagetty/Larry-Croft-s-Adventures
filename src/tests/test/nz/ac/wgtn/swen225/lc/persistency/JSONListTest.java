package test.nz.ac.wgtn.swen225.lc.persistency;

import nz.ac.wgtn.swen225.lc.persistency.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JSONListTest {

  @Test
  public void testAddString() {
    JSONList list = new JSONList();
    list.add("Hello");

    assertEquals(new JSONString("Hello"), list.getElements().get(0));
  }

  @Test
  public void testAddInteger() {
    JSONList list = new JSONList();
    list.add(42L);

    assertEquals(new JSONLong(42L), list.getElements().get(0));
  }

  @Test
  public void testToString() {
    JSONList list = new JSONList();
    list.add("Hello");
    list.add(42L);

    String expected = "JSONList: [JSONString[value=Hello], JSONLong[value=42]]";
    assertEquals(expected, list.toString());
  }
}
