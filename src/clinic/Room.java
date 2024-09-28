package clinic;

/**
 * A class to represent the behavior of a room within the clinic.
 * Rooms have bottom-left and top-right coordinates, a type and a name.
 */
public class Room {
  private int x1;
  private int x2;
  private int y1;
  private int y2;
  private RoomType typeRoom;
  private String roomName;
  
  /**
   * Constructor for a room with bottom-left and top-right coordinates, a type
   * and a name.
   * 
   * @param x1 The x-coordinate for the bottom-left corner of the room.
   * @param x2 The x-coordinate for the top-right corner of the room.
   * @param y1 The y-coordinate for the bottom-left corner of the room.
   * @param y2 The y-coordinate for the top-right corner of the room.
   * @param roomType The type of room.
   * @param roomName The name of the room.
   */
  public Room(int x1, int y1, int x2, int y2, RoomType roomType, String roomName) {
    if (x1 >= x2 || y1 >= y2) {
      throw new IllegalArgumentException("Room coordinates are invalid. "
       + "x2 must be greater than x1 and y2 must be greater than y1.");
    }
    
    if (roomName == null) {
      throw new IllegalArgumentException("Room name cannot be null.");
    }
    
    this.x1 = x1;
    this.y1 = y2;
    this.x2 = x2;
    this.y2 = y2;
    this.typeRoom = roomType;
    this.roomName = roomName;
  }
}
