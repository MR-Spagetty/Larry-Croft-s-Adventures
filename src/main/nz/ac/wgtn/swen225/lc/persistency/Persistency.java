package nz.ac.wgtn.swen225.lc.persistency;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class  Persistency {
  public static void saveToFile(String filename, JSONType object) throws IOException {
    Gson gson = new Gson();
    String json = gson.toJson(object);
    FileUtils.writeStringToFile(new File(filename), json, "UTF-8");
  }

  public static JSONType loadFromFile(String filename) throws IOException {
    String json = FileUtils.readFileToString(new File(filename), "UTF-8");

    // Parse the string using JSONTOKener to determine structure
    JSONTokener tokener = new JSONTokener(json);
    Object parsedJson = tokener.nextValue();

    // Chekc if it's a JSON Object or Array and do action accordingly
    if (parsedJson instanceof JSONObject) {
      return parseTheJSONObject((JSONObject) parsedJson);
    } else if (parsedJson instanceof JSONArray) {
      return parseTheJSONArray((JSONArray) parsedJson);
    }

    throw new IllegalArgumentException("Invalid JSON Format");
  }


// Parsing JSONObject
  private static JSONObject parseTheJSONObject(JSONObject jsonObject) {
    JSONObject customObject = new JSONObject();

    for (String key : jsonObject.keySet()) {
      Object value = jsonObject.get(key);

      switch (value) {
        case JSONObject object -> customObject.put(key, parseTheJSONObject(object));
        case JSONArray objects -> customObject.put(key, parseTheJSONArray(objects));
        case String s -> customObject.put(key, s);
        case Long l -> customObject.put(key, l);
        case Double v -> customObject.put(key, v);
        case Boolean b -> customObject.put(key, b);
        case null, default -> customObject.put(key, 0); // Handle null values
      }
    }

    return customObject;
  }

  private static JSONType parseTheJSONArray(JSONArray parsedJson) {

  }

}

