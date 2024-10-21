package clinic;

import java.time.LocalDateTime;

/**
 * Creates a record about a patient's information upon their visit.
 */
public interface Record {
  
  /**
  * A method used to get the patient's admission date into the hospital.
  * 
   * @return a String representing the patient's admission to the clinic.
  */
  String getDate();
  
  /**
   * Returns a string about the patients complaint upon coming to the hospital.
   * 
   * @return a String which stores the complaint
   */
  String getChiefComplaint();
  
  
  /**
   * Gets the patient's body temperature at time of reading in degrees Celsius.
   * The temperature is displayed to one decimal place of precision.
   * 
   * @return a patient's body temperature.
   */
  double getTemperature();
  
  List<Record> getVisitRecord();
}
