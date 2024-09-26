package nz.ac.wgtn.swen225.lc.persistency;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;



public class JSONList<T extends JSONType> implements JSONType {
  private List<T> items = new ArrayList<>();

  public void add(T item) {
    items.add(item);
  }

  @Override
  public String toJson() {
    // Convert the list to JSON using a library like Gson, or manually
    return new Gson().toJson(items);
  }
}
