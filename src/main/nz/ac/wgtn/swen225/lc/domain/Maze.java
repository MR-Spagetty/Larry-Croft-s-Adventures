package nz.ac.wgtn.swen225.lc.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


import nz.ac.wgtn.swen225.lc.domain.entities.Entity;
import nz.ac.wgtn.swen225.lc.domain.tiles.ModifiableTile;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;

import nz.ac.wgtn.swen225.lc.persistency.JSONObject;
import nz.ac.wgtn.swen225.lc.persistency.*;
import nz.ac.wgtn.swen225.lc.domain.entities.Player;

public class Maze {
  private List<Tile> tiles = new ArrayList<>();
  public final long maxTicks;
  final String ID;

  /**
   * Creates a new empty maze with the given time limit
   *
   * @param maxTicks the time limit in ticks
   * @param ID the ID of the level
   */
  public Maze(long maxTicks, String ID) {
    if (maxTicks < 0) {
      throw new IllegalArgumentException("maxTicks may not be negative");
    }
    this.maxTicks = maxTicks;
    this.ID = "";
  }

  /**
   * Creates a new maze with the given time limit, tiles, and entities
   *
   * @param maxTicks the time limit in ticks
   * @param ID the id of the level
   * @param tiles the tiles to fill teh maze with
   * @param entities the entities to populate the maze with
   */
  public Maze(long maxTicks, String ID, List<Tile> tiles, List<Entity> entities) {
    this(maxTicks, ID);
    tiles.forEach(this::addTile);
    this.tiles.sort(Tile::compareTo);
    entities.forEach(this::addEntity);
  }

  /**
   * Gets the Maze id as a long for use by entities to generate their UIDS
   *
   * @return the longified ID
   */
  public long longID() {
    long longID = 0;
    for (char c : this.ID.toCharArray()) {
      longID <<= 8;
      longID |= c;
    }
    return longID;
  }

  /**
   * tries to add the given entity to the maze
   *
   * @param toAdd the entity to add
   * @throws IllegalArgumentException if there is no tile at the coordinates of the given entity
   */
  public void addEntity(Entity toAdd) {
    Tile reqTile =
        getTile(toAdd.location())
            .orElseThrow(
                () -> new IllegalArgumentException("No tile exists at the required coordinates"));
    toAdd.maze(this);
    reqTile.put(toAdd);
  }

  /**
   * tries to add the given tile to the maze
   *
   * @param tile the tile to add
   * @throws IllegalArgumentException if there is already a tile at the coordinates of the given
   *     tile
   */
  public void addTile(Tile tile) {
    if (getTile(tile.location()).isPresent()) {
      throw new IllegalArgumentException("Tile at those coordinates already exists");
    }
    tiles.add(tile);
  }

  /**
   * tries to get the tile at the given coordinates
   *
   * @param at the coordinates to get the tile at
   * @return an {@link Optional} containing the tile at the requested location, or an empty {@link
   *     Optional} if there is no such tile
   */
  public Optional<Tile> getTile(Point at) {
    tiles.sort(Tile::compareTo);
    return tiles.parallelStream().filter(t -> t.location().equals(at)).findAny();
  }

  /**
   * gets the tiles within the specified region
   *
   * @param around the center point of the region
   * @param range the range (inclusive ±) the region occupies in a square around around
   * @return the tiles within that region
   */
  public List<Tile> getTiles(Point around, long range) {
    tiles.sort(Tile::compareTo);
    return tiles.parallelStream()
        .filter(
            t -> {
              Point p = t.location();
              return (p.x() >= around.x() - range)
                  && (p.x() <= around.x() + range)
                  && (p.y() >= around.y() - range)
                  && (p.y() <= around.y() + range);
            })
        .sorted()
        .toList();
  }

  /**
   * tries to get the entity at the given point
   *
   * @param at the point to get the entity from
   * @return an {@link Optional} containing the entity currently at the requested location, or an
   *     empty {@link Optional} if there is no such entity
   */
  public Optional<Entity> getEntity(Point at) {
    return getTile(at).flatMap(Tile::getOccupant);
  }

  /**
   * gets an unmodifiable list of all the entities in the maze
   *
   * @return the entities in the maze
   */
  public List<Entity> getEntities() {
    return tiles.stream()
        .map(Tile::getOccupant)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .toList();
  }

  /**
   * get all the entities in the specified region
   *
   * @param around the center point of the region
   * @param range the range (inclusive ±) the region occupies in a square around around
   * @return the entities within that region
   */
  public List<Entity> getEntities(Point around, long range) {
    return getTiles(around, range).stream()
        .map(Tile::getOccupant)
        .flatMap(Optional::stream)
        .toList();
  }

  public static Maze fromJSON(JSONType json) {

    if (json instanceof JSONObject data) {
      Player p = Player.fromJSON((JSONObject)data.get("player"));
      return fromJSON(data, basicMazeData(data), List.of(), List.of(p));

    } else {
      throw new IllegalArgumentException("Expected JSONObject got " + json.getClass().getName());
    }
  }

  public static Maze fromJSONState(
      List<Tile> changedTiles, List<Entity> entities, JSONObject levelJSON) {
    return fromJSON(levelJSON, basicMazeData(levelJSON), changedTiles, entities);
  }

  private static Maze basicMazeData(JSONObject data) {
    JSONType maxTicks = data.get("maxTicks");
    if (!(maxTicks instanceof JSONLong)) {
      throw new IllegalArgumentException(
          "Expect JSONLong at \"maxTicks\" got " + maxTicks.getClass().getName());
    }
    long maxTicksVal = ((JSONLong) maxTicks).get();
    JSONType ID = data.get("ID");
    if (!(ID instanceof JSONString)) {
      throw new IllegalArgumentException(
          "Expect JSONLong at \"maxTicks\" got " + ID.getClass().getName());
    }
    String IDVal = ((JSONString) ID).get();
    return new Maze(maxTicksVal, IDVal);
  }

  private static Maze fromJSON(
      JSONObject json, Maze maze, List<Tile> changedTiles, List<Entity> entities) {
    changedTiles.forEach(maze::addTile);
    JSONType tiles = json.get("tiles");
    if (!(tiles instanceof JSONList)) {
      throw new IllegalArgumentException(
          "Expect JSONList at \"tiles\" got " + tiles.getClass().getName());
    }
    List<Point> tilesExistAt = changedTiles.stream().map(Tile::location).toList();
    ((JSONList) tiles)
        .getElements().stream()
            .map(Tile::fromJSON)
            .filter(t -> tilesExistAt.stream().noneMatch(et -> et.equals(t.location())))
            .forEach(maze::addTile);
    JSONType entitiesJSON = json.get("entities");
    if (!(entitiesJSON instanceof JSONList)) {
      throw new IllegalArgumentException(
          "Expect JSONList at \"entities\" got " + entitiesJSON.getClass().getName());
    }
    if (entities.isEmpty()) {
      ((JSONList) entitiesJSON)
          .getElements().stream().map(Entity::fromJSON).forEach(maze::addEntity);
    } else {
      entities.forEach(maze::addEntity);
    }
    return maze;
  }

  public List<ModifiableTile> getModifiableTiles() {
    return this.tiles.stream()
        .<ModifiableTile>mapMulti(
            (t, cons) -> {
              if (t instanceof ModifiableTile mt) cons.accept(mt);
            })
        .toList();
  }
}
