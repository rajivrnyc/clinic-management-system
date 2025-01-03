package clinic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class to represent the behavior of a room within the clinic.
 * Rooms have bottom-left and top-right coordinates, a type and a name.
 */

public class Room implements RoomInterface {
  private int x1;
  private int x2;
  private int y1;
  private int y2;
  private RoomType typeRoom;
  private String roomName;
  private List<PatientInterface> residents;
  
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
    this.y1 = y1;
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
  
  @Override
  public RoomType getRoomType() {
    return this.typeRoom;
  }
  
  @Override
  public String getRoomName() {
    return this.roomName;
  }
  
  @Override
  public void placePatient(PatientInterface patient) {
    this.residents.add(patient);
  }
  
  @Override
  public boolean isWaitingRoom() {
    if (this.typeRoom == RoomType.WAITING) {
      return true;
    }
    return false;
  }
  
  @Override
  public void removePatient(PatientInterface patient) {
    if (patient == null) {
      throw new IllegalArgumentException("Patient was not in this room.");
    }
    this.residents.remove(patient);
  }
  
  @Override
  public boolean isOccupied() {
    if (!this.residents.isEmpty()) {
      return true;
    }
    return false;
  }
  
  @Override
  public List<PatientInterface> getResidents() {
    return new ArrayList<>(this.residents);
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Room)) {
      return false;
    }
    Room other = (Room) obj;
    return this.x1 == other.x1
             && this.x2 == other.x2
             && this.y1 == other.y1
             && this.y2 == other.y2
             && this.roomName.equals(other.roomName)
             && this.typeRoom == other.typeRoom;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(this.x1, this.x2, this.y1, this.y2, this.roomName, this.typeRoom);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Room Name: ").append(roomName).append("\nRoom Type: ").append(typeRoom)
    .append("\nPatient Details: ");
    
    if (residents.isEmpty()) {
      sb.append("\nNone");
    } else {
      for (PatientInterface patient : residents) {
        PatientInterface2 patientI = (PatientInterface2) patient;
        if (patientI.isActive()) {
          sb.append("\n---------------------------------------");
          sb.append("\n").append(patient.getFirstName()).append(" ")
        .append(patient.getLastName()).append(", Room Number: ").append(patient.getRoomNumber())
          .append(", Clinical Staff Allocated: ");
        
          if (patient.getAllocated().isEmpty()) {
            sb.append("None");
          } else {
            for (ClinicalStaffInterface cstaff : patient.getAllocated()) {
              sb.append(cstaff.getTitle()).append(cstaff.getFirstName())
            .append(" ").append(cstaff.getLastName())
              .append(", ");
            }
            sb.setLength(sb.length() - 2);
          }
          Record mostRecent = patientI.getMostRecentVisit();
          if (mostRecent != null) {
          
            sb.append("\n   Chief Complaint: ").append(mostRecent.getChiefComplaint());
          
          } else {
            sb.append("\n   Patient has no Visit Records");
          }
        
        }
      }
    }
    sb.append("\n---------------------------------------");
    return sb.toString();
  }

  
  /**
   * Method used to interpret the Room portion of a text file passed in
   * to the model.
   * 
   * @param roomText A line of text describing a room object.
   * @return a Room object by interpreting a text block that defines a room
   */
  public static Room textRoom(String roomText) {
    String[] splitRoom = roomText.split("\\s+");
      
    int tempx1 = Integer.parseInt(splitRoom[0]);
    int tempy1 = Integer.parseInt(splitRoom[1]);
    int tempx2 = Integer.parseInt(splitRoom[2]);
    int tempy2 = Integer.parseInt(splitRoom[3]);
    RoomType tempRoomType = RoomType.valueOf(splitRoom[4].toUpperCase());
    String roomNameText = pullRoomName(splitRoom);
    return new Room(tempx1, tempy1, tempx2, tempy2, tempRoomType, roomNameText);
  }
}
