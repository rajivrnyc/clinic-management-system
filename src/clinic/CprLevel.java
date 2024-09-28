package clinic;

/**
 * Creates enumeration for CPR Level which is a field for non clinical
 * staff members describing the level of CPR they can perform.
 */
public enum CprLevel {
  A("A"),
  B("B"),
  C("C"),
  BLS("BLS");

  private final String disp;

  private CprLevel(String disp) {
    this.disp = disp;
  }

  @Override
  public String toString() {
    return disp;
  }
}
