package clinic;

/**
 * Represents different room types in a clinic. 
 */
public enum RoomType {
  EXAM("exam"),
  PROCEDURE("procedure"),
  WAITING("waiting");

  private final String disp;

  private RoomType(String disp) {
    this.disp = disp;
  }

  @Override
  public String toString() {
    return disp;
  }
}
