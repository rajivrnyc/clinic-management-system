package clinic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
   * @param complaint the patient's chief complaint upon coming to the clinic.
   * @param temperature the patient's temperature in Celsius upon coming to the clinic.
   */
  public VisitRecord(String complaint, double temperature) {
    if (complaint == null) {
      throw new IllegalArgumentException("Patient cannot have a null arrival or complaint.");
    }
    
    this.dateArrival = LocalDateTime.now();
    this.complaint = complaint;
    this.temperature = Math.round(temperature * 10.0) / 10.0;
  }

  @Override
  public String getDate() {
    DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    return dateArrival.format(dtFormat);
  }

  @Override
  public String getChiefComplaint() {
    return complaint;
  }

  @Override
  public double getTemperature() {
    return temperature;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof VisitRecord)) {
      return false;
    }
    VisitRecord other = (VisitRecord) obj;
    return this.dateArrival.isEqual(other.dateArrival)
        && this.complaint.equals(other.complaint)
        && Double.compare(this.temperature, other.temperature) == 0;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(dateArrival, complaint, temperature);
  }
  
  @Override
  public String toString() {
    DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    StringBuilder sb = new StringBuilder();
    
    sb.append("").append(dateArrival.format(dtFormat)).append(", Complaint: ").append(complaint)
    .append(", Temperature: ").append(temperature).append(" °C");
    
    return sb.toString();
  }

}
