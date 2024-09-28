package clinic;

/**
 * Creates an enumerated type that represents different possible
 * education levels for staff at the clinic.
 */
public enum EducationLevel {
  DOCTORAL("doctoral"),
  MASTERS("masters"),
  ALLIED("allied");

  private final String disp;

  private EducationLevel(String disp) {
    this.disp = disp;
  }

  @Override
  public String toString() {
    return disp;
  }
}
