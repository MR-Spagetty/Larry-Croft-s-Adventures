package nz.ac.wgtn.swen225.lc.persistency;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class Persistency {
  public static void saveToFile(String filename, Object object) throws IOException {
    Gson gson = new Gson();
    String json = gson.toJson(object);
    FileUtils.writeStringToFile(new File(filename), json, "UTF-8");
  }

  public static <T> T loadFromFile(String filename, Class<T> clazz) throws IOException {
    String json = FileUtils.readFileToString(new File(filename), "UTF-8");
    Gson gson = new Gson();
    return gson.fromJson(json, clazz);
  }
}

