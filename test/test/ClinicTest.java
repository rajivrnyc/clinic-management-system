package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import clinic.Clinic;
import clinic.ClinicInterface;
import clinic.ClinicalStaff;
import clinic.CprLevel;
import clinic.EducationLevel;
import clinic.NonClinicalStaff;
import clinic.Patient;
import clinic.Room;
import clinic.RoomInterface;
import clinic.RoomType;
import clinic.Staff;
import clinic.StaffFactoryHelper;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Test Class for all classes within clinic package.
 */
public class ClinicTest {
  private Clinic clinic;
  private Room primaryWaitingRoom;
	
  @Before
  public void setUp() {
    primaryWaitingRoom = new Room(0, 0, 20, 20, RoomType.WAITING, "Primary Waiting Room");
    clinic = new Clinic("Local Clinic", 5, 10, 20, primaryWaitingRoom);
  }
  
  @Test
  public void testClinic() {
    assertEquals(clinic, new Clinic("Local Clinic", 5, 10, 20, primaryWaitingRoom));
  }

}
