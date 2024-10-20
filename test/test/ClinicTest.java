package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import clinic.Clinic;
import clinic.ClinicInterface;
import clinic.ClinicalStaff;
import clinic.ClinicalStaffInterface;
import clinic.CprLevel;
import clinic.EducationLevel;
import clinic.NonClinicalStaff;
import clinic.Patient;
import clinic.PatientInterface;
import clinic.Room;
import clinic.RoomInterface;
import clinic.RoomType;
import clinic.Staff;
import clinic.StaffFactoryHelper;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Test Class for all classes within clinic package.
 */
public class ClinicTest {
  private ClinicInterface clinic;
  private RoomInterface examRoom;
  private RoomInterface primaryWaitingRoom;
  private PatientInterface sally;
  
  /**
   * Sets up testing Environment before conduction tests.
   */
  @Before
  public void setUp() {
    sally = new Patient(1, "Sally", "Johnson", "01/02/1990");
    primaryWaitingRoom = new Room(0, 0, 20, 20, RoomType.WAITING, "Primary Waiting Room");
    examRoom = new Room(21, 0, 40, 20, RoomType.EXAM, "Exam Room 1");
    clinic = new Clinic("Local Clinic", 5, 10, 20, primaryWaitingRoom);
    clinic.getRooms().add(examRoom);
  }
  
  @Test
  public void testClinic() {
    assertEquals(clinic, new Clinic("Local Clinic", 5, 10, 20, primaryWaitingRoom));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void nullNameClinicTest() {
    new Clinic(null, 5, 10, 20, primaryWaitingRoom);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void negNumRoomsClinicTest() {
    new Clinic("Local Clinic", -5, 10, 20, primaryWaitingRoom);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void negNumStaffClinicTest() {
    new Clinic("Local Clinic", 5, -10, 20, primaryWaitingRoom);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void negNumPatientsClinicTest() {
    new Clinic("Local Clinic", 5, 10, -20, primaryWaitingRoom);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void nullWaitingRoomClinicTest() {
    new Clinic("Local Clinic", 5, 10, -20, null);
  }
  
  @Test
  public void testGetClinicName() {
    assertEquals("Local Clinic", clinic.getClinicName());
  }
  
  @Test
  public void testGetNumRooms() {
    assertEquals(5, clinic.getNumRooms());
  }
  
  @Test
  public void testGetNumPatients() {
    assertEquals(20, clinic.getNumPatients());
  }
  
  @Test
  public void testGetNumStaff() {
    assertEquals(10, clinic.getNumStaff());
  }
  
  @Test
  public void testAddNewPatient() {
    clinic.addNewPatient("Sally", "Johnson", "01/02/1990");
    List<PatientInterface> patients = clinic.getPatients();
    PatientInterface newPatient = patients.get(0);
    assertEquals("Sally", newPatient.getFirstName());
    assertEquals("Johnson", newPatient.getLastName());
    assertEquals("01/02/1990", newPatient.getDateOfBirth());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void nullFirstNameNewPatientTest() {
    clinic.addNewPatient(null, "Johnson", "01/02/1990");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void nullLastNameNewPatientTest() {
    clinic.addNewPatient("Sally", null, "01/02/1990");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void nullDobNameNewPatientTest() {
    clinic.addNewPatient("Sally", "Johnson", null);
  }
  
  @Test
  public void testAddNewClinicalStaff() {
    clinic.addNewClinicalStaff("Doctor", "John", "Smith", EducationLevel.DOCTORAL, "1000000000");
    List<Staff> staffs = clinic.getEmployees();
    Staff newStaff = staffs.get(0);
    assertEquals("Doctor", newStaff.getJobTitle());
    assertEquals("John", newStaff.getFirstName());
    assertEquals("Smith", newStaff.getLastName());
    assertEquals(EducationLevel.DOCTORAL, newStaff.getEducationLevel());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void nullJobTitleNewClinicalStaff() {
    clinic.addNewClinicalStaff(null, "John", "Smith", EducationLevel.DOCTORAL, "1000000000");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void nullFirstNameNewClinicalStaff() {
    clinic.addNewClinicalStaff("Doctor", null, "Smith", EducationLevel.DOCTORAL, "1000000000");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void nullLastNameNewClinicalStaff() {
    clinic.addNewClinicalStaff("Doctor", "John", null, EducationLevel.DOCTORAL, "1000000000");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void nullEducationLevelNewClinicalStaff() {
    clinic.addNewClinicalStaff("Doctor", "John", "Smith", null, "1000000000");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void incorrectNpiLevelNewClinicalStaff() {
    clinic.addNewClinicalStaff("Doctor", "John", "Smith", EducationLevel.DOCTORAL, "10000000000");
  }
  
  @Test
  public void testGetRoomFromNumber() {
    assertEquals(primaryWaitingRoom, clinic.getRoomFromNumber(1));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testGetRoomFromNegativeNumber() {
    assertEquals(primaryWaitingRoom, clinic.getRoomFromNumber(-1));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testGetRoomFromBigNumber() {
    assertEquals(primaryWaitingRoom, clinic.getRoomFromNumber(100));
  }
  
  @Test
  public void testSendHome() {
    Patient patient = new Patient(1, "Sally", "Johnson", "01/02/1990");
    clinic.addNewPatient("Sally", "Johnson", "01/02/1990");
    ClinicalStaff john = new ClinicalStaff("Doctor", "John", 
        "Smith", EducationLevel.DOCTORAL, "1000000000");
    
    clinic.sendHome(patient, john);
    assertTrue(patient.getApproval());
  }
    
  @Test(expected = IllegalArgumentException.class)
  public void testNullSendHome() {
    clinic.addNewPatient("Sally", "Johnson", "01/02/1990");
    clinic.sendHome(null, null);
  }
  
  @Test
  public void testDeactivateClinicalStaffClinic() {
    ClinicalStaff john = new ClinicalStaff("Doctor",
        "John", "Smith", EducationLevel.DOCTORAL, "1000000000");
    clinic.addNewPatient("Sally", "Johnson", "01/02/1990");
    Patient patient = new Patient(1, "Sally", "Johnson", "01/02/1990");
    clinic.assignStaff(patient, john);
    
    clinic.deactivateClinicalStaffClinic(john);
    List<Staff> staffList = clinic.getEmployees();
    assertFalse(staffList.contains(john));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullDeactivateStaff() {
    clinic.deactivateClinicalStaffClinic(null);
  }
 
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullPatientAssignPatient() {
    clinic.assignPatient(null, primaryWaitingRoom);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullRoomAssignPatient() {
    PatientInterface patient = new Patient(1, "Sally", "Johnson", "01/02/1990");
    clinic.addNewPatient("Sally", "Johnson", "01/02/1990");
    clinic.assignPatient(patient, null);
  }
  
  @Test
  public void testAssignStaff() {
    ClinicalStaffInterface john = new ClinicalStaff("Doctor",
        "John", "Smith", EducationLevel.DOCTORAL, "1000000000");
    
    clinic.addNewPatient("Sally", "Johnson", "01/02/1990");
    PatientInterface patient = clinic.getPatients().get(0);
    clinic.assignStaff(patient, john);
    List<ClinicalStaffInterface> allPatient = patient.getAllocated();
    assertTrue(allPatient.contains(john));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullPatientAssignStaff() {
    ClinicalStaff john = new ClinicalStaff("Doctor",
        "John", "Smith", EducationLevel.DOCTORAL, "1000000000");
    
    clinic.addNewPatient("Sally", "Johnson", "01/02/1990");
    PatientInterface patient = clinic.getPatients().get(0);
    clinic.assignStaff(null, john);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNullStaffAssignStaff() {
    ClinicalStaff john = new ClinicalStaff("Doctor",
        "John", "Smith", EducationLevel.DOCTORAL, "1000000000");
    
    clinic.addNewPatient("Sally", "Johnson", "01/02/1990");
    PatientInterface patient = clinic.getPatients().get(0);
    clinic.assignStaff(patient, null);
  }
  
  @Test
  public void testRoomConstruction() {
    Room r1 = new Room(5, 6, 15, 15, RoomType.EXAM, "ExamRoom1");
    assertEquals("ExamRoom1", r1.getRoomName());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testRoomBigXsConstruction() {
    new Room(16, 6, 15, 15, RoomType.EXAM, "ExamRoom1");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testRoomBigYsConstruction() {
    new Room(5, 16, 15, 15, RoomType.EXAM, "ExamRoom1");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testRoomNullNameConstruction() {
    new Room(5, 6, 15, 15, RoomType.EXAM, null);
  }
  
  @Test
  public void testGetRoomName() {
    assertEquals("Primary Waiting Room", primaryWaitingRoom.getRoomName());
  }
  
  @Test
  public void testGetRoomType() {
    assertEquals(RoomType.WAITING, primaryWaitingRoom.getRoomType());
  }

  @Test
  public void testPlacePatient() {
    PatientInterface patient = new Patient(1, "Sally", "Johnson", "01/02/1990");
    primaryWaitingRoom.placePatient(patient);
    List<PatientInterface> temp = primaryWaitingRoom.getResidents();
    assertTrue(temp.contains(patient));
  }
  
  @Test
  public void testWaitingRoom() {
    assertTrue(primaryWaitingRoom.isWaitingRoom());
    assertFalse(examRoom.isWaitingRoom());
  }
  
  @Test
  public void testRemovePatient() {
    Patient patient = new Patient(1, "Sally", "Johnson", "01/02/1990");
    primaryWaitingRoom.placePatient(patient);
    primaryWaitingRoom.removePatient(patient);
    List<PatientInterface> temp = primaryWaitingRoom.getResidents();
    assertFalse(temp.contains(patient));
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testRemoveNullPatient() {
    Patient patient = new Patient(1, "Sally", "Johnson", "01/02/1990");
    primaryWaitingRoom.placePatient(patient);
    primaryWaitingRoom.removePatient(null);

  }
  
  @Test
  public void testIsOccupied() {
    Patient patient = new Patient(1, "Sally", "Johnson", "01/02/1990");
    examRoom.placePatient(patient);
    assertTrue(examRoom.isOccupied());
    examRoom.removePatient(patient);
    assertFalse(examRoom.isOccupied());
  }
  
  @Test
  public void testPatientConstructor() {
    Patient patient = new Patient(1, "Sally", "Johnson", "01/02/1990");
    assertEquals("Sally", patient.getFirstName());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPatientConstructorNegativeRoom() {
    Patient patient = new Patient(-1, "Sally", "Johnson", "01/02/1990");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPatientConstructorNullName() {
    Patient patient = new Patient(1, null, "Johnson", "01/02/1990");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPatientConstructorNullName2() {
    Patient patient = new Patient(1, "Sally", null, "01/02/1990");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPatientConstructorNullDate() {
    Patient patient = new Patient(1, "Sally", "Johnson", null);
  }
  
  @Test
  public void testGetRoomNumber() {
    assertEquals(1, sally.getRoomNumber());
  }
  
  @Test
  public void testGetFirstName() {
    assertEquals("Sally", sally.getFirstName());
  }
  
  @Test
  public void testGetLastName() {
    assertEquals("Johnson", sally.getLastName());
  }
  
  @Test
  public void testGetDateOfBirth() {
    assertEquals("01/02/1990", sally.getDateOfBirth());
  }
  
  @Test
  public void testGetApproval() {
    assertFalse(sally.getApproval());
  }
  
  @Test
  public void testSetRoomNumber() {
    sally.setRoomNumber(2);
    assertEquals(2, sally.getRoomNumber());
  }
  
  @Test
  public void testSetApproval() {
    ClinicalStaff john = new ClinicalStaff("Doctor",
        "John", "Smith", EducationLevel.DOCTORAL, "1000000000");
    sally.setApproval(john, true);
    assertTrue(sally.getApproval());
  }
  
  @Test
  public void testRemoveClinicalStaffmember() {
    ClinicalStaff john = new ClinicalStaff("Doctor",
        "John", "Smith", EducationLevel.DOCTORAL, "1000000000");
    sally.getAllocated().add(john);
    sally.removeClinicalStaffMember(john);
    List<ClinicalStaffInterface> temp = sally.getAllocated();
    assertFalse(temp.contains(john));
  }
  
  @Test
  public void testClinicalStaffConstruction() {
    ClinicalStaff john = new ClinicalStaff("Doctor",
        "John", "Smith", EducationLevel.DOCTORAL, "1000000000");
    assertEquals("Doctor", john.getJobTitle());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testClinicalStaffConstructionNullJob() {
    ClinicalStaff john = new ClinicalStaff(null,
        "John", "Smith", EducationLevel.DOCTORAL, "1000000000");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testClinicalStaffConstructionNullName1() {
    ClinicalStaff john = new ClinicalStaff("Doctor",
        null, "Smith", EducationLevel.DOCTORAL, "1000000000");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testClinicalStaffConstructionNullName2() {
    ClinicalStaff john = new ClinicalStaff("Doctor",
        "John", null, EducationLevel.DOCTORAL, "1000000000");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testClinicalStaffConstructionNullEducationLevel() {
    ClinicalStaff john = new ClinicalStaff("Doctor",
        "John", "Smith", null, "1000000000");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testClinicalStaffConstructionNullNpiLevel() {
    ClinicalStaff john = new ClinicalStaff("Doctor",
        "John", "Smith", EducationLevel.DOCTORAL, null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testClinicalStaffConstructionWrongNpiLevel() {
    ClinicalStaff john = new ClinicalStaff("Doctor",
        "John", "Smith", EducationLevel.DOCTORAL, "10000000000");
  }
  
  @Test
  public void testGetClinicalJobTitle() {
    ClinicalStaff john = new ClinicalStaff("Doctor",
        "John", "Smith", EducationLevel.DOCTORAL, "1000000000");
    assertEquals("Doctor", john.getJobTitle());
  }
  
  @Test
  public void testGetClinicalFirstName() {
    ClinicalStaff john = new ClinicalStaff("Doctor",
        "John", "Smith", EducationLevel.DOCTORAL, "1000000000");
    assertEquals("John", john.getFirstName());
  }
  
  @Test
  public void testGetClinicalLastName() {
    ClinicalStaff john = new ClinicalStaff("Doctor",
        "John", "Smith", EducationLevel.DOCTORAL, "1000000000");
    assertEquals("Smith", john.getLastName());
  }
  
  @Test
  public void testGetClinicalEducationLevel() {
    ClinicalStaff john = new ClinicalStaff("Doctor",
        "John", "Smith", EducationLevel.DOCTORAL, "1000000000");
    assertEquals(EducationLevel.DOCTORAL, john.getEducationLevel());
  }
  
  @Test
  public void testNonClinicalConstructor() {
    NonClinicalStaff jack = new NonClinicalStaff("Reception", "Jack", 
        "Smith", EducationLevel.DOCTORAL, CprLevel.A);
    assertEquals("Reception", jack.getJobTitle());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNonClinicalConstructorNullJob() {
    NonClinicalStaff jack = new NonClinicalStaff(null, "Jack", 
        "Smith", EducationLevel.DOCTORAL, CprLevel.A);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNonClinicalConstructorNullFirstName() {
    NonClinicalStaff jack = new NonClinicalStaff("Reception", null, 
        "Smith", EducationLevel.DOCTORAL, CprLevel.A);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNonClinicalConstructorNullLastName() {
    NonClinicalStaff jack = new NonClinicalStaff("Reception", "Jack", 
        null, EducationLevel.DOCTORAL, CprLevel.A);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNonClinicalConstructorNullEducationLevel() {
    NonClinicalStaff jack = new NonClinicalStaff("Recption", "Jack", 
        "Smith", null, CprLevel.A);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNonClinicalConstructorNullCprLevel() {
    NonClinicalStaff jack = new NonClinicalStaff("Reception", "Jack", 
        "Smith", EducationLevel.DOCTORAL, null);
  }
  
  @Test
  public void testGetNonClinicalJobTitle() {
    NonClinicalStaff jack = new NonClinicalStaff("Reception", "Jack", 
        "Smith", EducationLevel.DOCTORAL, CprLevel.A);
    assertEquals("Reception", jack.getJobTitle());
  }
  
  @Test
  public void testGetNonClinicalFirstName() {
    NonClinicalStaff jack = new NonClinicalStaff("Reception", "Jack", 
        "Smith", EducationLevel.DOCTORAL, CprLevel.A);
    assertEquals("Jack", jack.getFirstName());
  }
  
  @Test
  public void testGetNonClinicalLastName() {
    NonClinicalStaff jack = new NonClinicalStaff("Reception", "Jack", 
        "Smith", EducationLevel.DOCTORAL, CprLevel.A);
    assertEquals("Smith", jack.getLastName());
  }
  
  @Test
  public void testGetNonClinicalEducationLevel() {
    NonClinicalStaff jack = new NonClinicalStaff("Reception", "Jack", 
        "Smith", EducationLevel.DOCTORAL, CprLevel.A);
    assertEquals(EducationLevel.DOCTORAL, jack.getEducationLevel());
  }
  
  
  
}
