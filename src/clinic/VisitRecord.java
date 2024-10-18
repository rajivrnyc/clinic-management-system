package clinic;

import java.time.LocalDateTime;

/**
 * Creates a Record of a patient upon their most recent
 * visit to the clinic stating their time of arrival, chief complaint
 * and their body temperature.
 */
public class VisitRecord implements Record {
  private final LocalDateTime dateArrival;
  private final String complaint;
  private final double tempeprature;
  
  public VisitRecord(LocalDateTime dateArrival, String complaint, double temperature) {
   
    	
    }
    this.dateArrival = dateArrival;
    this.complaint = complaint;
    this.temp = temp;
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
