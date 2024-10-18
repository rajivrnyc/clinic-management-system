package clinic;

import java.time.LocalDateTime;

/**
 * Record of a patient upon their most recent
 * visit to the clinic stating their time of arrival, chief complaint
 * and their body temperature.
 */
public class VisitRecord implements Record {
  private final LocalDateTime dateArrival;
  private final String complaint;
  private final double temperature;
  
  /**
   * Creates an instance of a patient's visit record where datearrival is the patient's
   * arrival to the clinic, complaint is the patient's chief complaint to the clinic,
   * and temperature is the patient's body temperature in Celsius rounded to 1 decimal.
   * 
   * @param dateArrival the patient's arrival date and time to the clinic.
   * @param complaint the patient's chief complaint upon coming to the clinic.
   * @param temperature the patient's temperature in Celsius upon coming to the clinic.
   */
  public VisitRecord(LocalDateTime dateArrival, String complaint, double temperature) {
    if (dateArrival == null || complaint == null) {
      throw new IllegalArgumentException("Patient cannot have a null arrival or complaint.");
    }
    
    this.dateArrival = dateArrival;
    this.complaint = complaint;
    this.temperature = temperature;
  }

  @Override
  public LocalDateTime getDate() {
    return null;
  }

  @Override
  public String getChiefComplaint() {
    return null;
  }

  @Override
  public double getBodyTemperature() {
    return 0;
  }

}
