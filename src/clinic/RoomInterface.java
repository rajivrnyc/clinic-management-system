package clinic;

/**
 * Represents a room within the clinic.
 */
public interface RoomInterface {

  /**
 * Find the room type of a certain room.
 * 
 * @return the room type of a certain room.
 */
  RoomType getRoomType();
  
  
  /**
   * Gets the room name of the current Room object.
   * 
   * @return A String describing the name of the room.
   */
  String getRoomName();
  
  /**
   * Places a patient within a room.
   * 
   * @param patient A patient that is staying within the clinic.
   */
  void placePatient(Patient patient);
  
  
  /**
   * Removes a patient from a room.
   * 
   * @param patient A patient that was staying within the clinic.
   */
  void removePatient(Patient patient);
}
