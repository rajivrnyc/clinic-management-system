package clinic;

import java.util.ArrayList;
import java.util.List; 
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
  private List<Patient> residents;
  
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
    this.residents = new ArrayList<>();
  }
  
  /*
   * Helper Method for building the room name string.
   */
  private static String pullRoomName(String[] splitRoom) {
    StringBuilder roomNameTextBuild = new StringBuilder();
    for (int i = 5; i < splitRoom.length; i++) {
      if (i > 5) {
        roomNameTextBuild.append(" ");
      }
      roomNameTextBuild.append(splitRoom[i]);
    }
    return roomNameTextBuild.toString();
  }
  
  /**
   * Method used to interpret the Room portion of a text file passed in
   * to the model.
   * 
   * @param roomText A line of text describing a room object.
   * @return a Room object by interpreting a text block that defines a room
   */
  public static Room textRoom(String roomText) {
    String[] splitRoom = roomText.split(" ");
      
    int tempx1 = Integer.parseInt(splitRoom[0]);
    int tempy1 = Integer.parseInt(splitRoom[1]);
    int tempx2 = Integer.parseInt(splitRoom[2]);
    int tempy2 = Integer.parseInt(splitRoom[3]);
    RoomType tempRoomType = RoomType.valueOf(splitRoom[4].toUpperCase());
    String roomNameText = pullRoomName(splitRoom);
    return new Room(tempx1, tempy1, tempx2, tempy2, tempRoomType, roomNameText);
  }
}
