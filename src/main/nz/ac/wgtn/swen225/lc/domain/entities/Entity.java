package nz.ac.wgtn.swen225.lc.domain.entities;

import java.util.Optional;
import nz.ac.wgtn.swen225.lc.domain.Maze;
import nz.ac.wgtn.swen225.lc.domain.Point;
import nz.ac.wgtn.swen225.lc.domain.entities.enemies.Enemy;
import nz.ac.wgtn.swen225.lc.persistency.JSONLong;
import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.JSONString;
import nz.ac.wgtn.swen225.lc.persistency.JSONType;

/**
 * Basic interface that all entities in the game must implement.
 *
 * <p>This interface provides methods for managing entities' state, location, and game ticks.
 *
 * @author MR-Spagetty <54694556+MR-Spagetty@users.noreply.github.com>
 */
public interface Entity {

  /**
   * Gets the unique id of the entity for use in identification of the entity
   *
   * <p>primarily designed for use in randomisation of actions
   *
   * @return the unique id of this entity
   */
  long getUID();

  /**
   * Returns the timestamp of the last game tick when this entity was updated.
   *
   * @return the timestamp of the last game tick
   */
  long lastTicked();

  /**
   * Updates the state of this entity based on the given game tick.
   *
   * @param tick the current game tick
   */
  void tick(long tick);

  /**
   * Returns the current location of this entity in the game world.
   *
   * @return the current location of this entity
   */
  Point location();

  /**
   * Sets the current location of this entity in the game world.
   *
   * @param newLocation the new location
   * @throws IllegalArgumentException if the location is invalid
   */
  void location(Point newLocation);

  /**
   * check if the given entity can touch this entity
   *
   * @param touchee the entity to touch this entity
   * @return whether this entity can be touched
   */
  public boolean canTouch(Entity touchee);

  /**
   * touches this entity as the given entity
   *
   * @param touchee the entity to execute the touch as
   */
  public void touch(Entity touchee);

  /**
   * gets the maze that this Entity inhabits
   *
   * @return the inhabited maze
   */
  Maze maze();

  /**
   * sets the maze that this Entity inhabits
   *
   * <p>Should be only useable once
   *
   * @param maze the maze to inhabit
   */
  void maze(Maze maze);

  /**
   * returns a JSON representation of this entity
   *
   * @return a JSON representation of this entity
   */
  JSONType toJson();

  /**
   * deserializes the given json data into the entity it represents
   *
   * @param json the json data
   * @return the entity represented by the given data
   * @throws IllegalArgumentException if the data is incorrectly formatted or is for an unknown
   *     entity type
   */
  public static Entity fromJSON(JSONType json) {
    if (!(json instanceof JSONObject)) {
      throw new IllegalArgumentException(
          "Expected JSONObject but got " + json.getClass().getName());
    }
    if (!(((JSONObject) json).get("type") instanceof JSONString)) {
      throw new IllegalArgumentException(
          "Expected JSONString at \"type\" but found "
              + ((JSONObject) json).get("type").getClass().getName());
    }
    String type = ((JSONString) ((JSONObject) json).get("type")).get();
    return switch (type) {
      case "Player" -> Player.fromJSON((JSONObject) json);
      case "MoveableBlock" -> MoveableBlock.fromJSON((JSONObject) json);
      case "Bug", "BitFlipper" -> Enemy.fromJSON(json);
      default -> throw new IllegalArgumentException("Unknown entity type: " + type);
    };
  }

  /**
   * extracts the individual id for an entity from the given data
   *
   * @param data the data to extract from
   * @return the extracted id
   * @throws IllegalArgumentException if the id could not be found
   */
  static long idFromJSON(JSONObject data) {
    return Optional.ofNullable(data.get("indID"))
        .map(
            id -> {
              if (!(id instanceof JSONLong)) {
                throw new IllegalArgumentException(
                    "Expected JSONLong at \"indID\" but found " + id.getClass().getName());
              }
              return ((JSONLong) id).get();
            })
        .orElseThrow(() -> new IllegalArgumentException("Expected element at key \"indID\""));
  }
}