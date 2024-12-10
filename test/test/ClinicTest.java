package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import clinic.Clinic;
import clinic.Clinic2;
import clinic.Clinic3;
import clinic.ClinicInterface;
import clinic.ClinicInterface3;
import clinic.ClinicStaffAndPatientInfo;
import clinic.ClinicalStaff;
import clinic.ClinicalStaffInterface;
import clinic.CprLevel;
import clinic.EducationLevel;
import clinic.NonClinicalStaff;
import clinic.Patient;
import clinic.PatientInterface;
import clinic.PatientInterface2;
import clinic.PatientInterface3;
import clinic.Record;
import clinic.Room;
import clinic.RoomInterface;
import clinic.RoomType;
import clinic.Staff;
import clinic.StaffFactoryHelper;
import controller.ClinicController;
import controller.ClinicControllerGui;
import controller.ClinicControllerInterface;
import controller.Features;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import view.MasterView;
import view.MasterViewInterface;


/**
 * JUnit Test Class for all classes within clinic package.
 */
public class ClinicTest {
  private ClinicInterface3 clinic;
  private RoomInterface primaryWaitingRoom;
  private Appendable outputN;
  private Readable inputN;
  private Random random;
  private ClinicController controller;
  private MasterViewInterface view;
  
  /**
   * Sets up testing Environment before conduction tests.
   */
  @Before
  public void setUp() throws IOException {
    random = new Random();
    primaryWaitingRoom = new Room(28, 0, 35, 5, RoomType.WAITING, "Front Waiting Room");
    clinic = Clinic3.readFile(new FileReader("res/clinicfile.txt"));
    inputN  = new InputStreamReader(System.in);
    outputN = System.out;
    view = new MasterView();
  }
  
  @Test
  public void testClinic() {
    assertEquals(clinic, new Clinic3("Cybernetic Implant Clinic", 
        5, 6, 7, clinic.getRoomFromNumber(1)));
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
    assertEquals("Cybernetic Implant Clinic", clinic.getClinicName());
  }
  
  @Test
  public void testGetNumRooms() {
    assertEquals(5, clinic.getNumRooms());
  }
  
  @Test
  public void testGetNumPatients() {
    assertEquals(7, clinic.getNumPatients());
  }
  
  @Test
  public void testGetNumStaff() {
    assertEquals(6, clinic.getNumStaff());
  }
  
  @Test
  public void testAddNewPatient() {
    clinic.addNewPatient("Sally", "Johnson", "01/02/1990");
    List<PatientInterface> patients = clinic.getPatients();
    PatientInterface newPatient = patients.get(patients.size() - 1);
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
    Staff newStaff = staffs.get(staffs.size() - 1);
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
    RoomInterface room1 = clinic.getRoomFromNumber(1);
    assertEquals("Front Waiting Room", room1.getRoomName());
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
  public void assignMoveAssign() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    PatientInterface beth = clinic.findPatient("Beth", "Bunion");
    RoomInterface pwr = clinic.getRooms().get(0);
    RoomInterface tri = clinic.getRooms().get(1);
    clinic.assignPatient(beth, pwr);
    clinic.assignPatient(aandi, tri);
    assertTrue(clinic.getRooms().get(1).getResidents().contains(aandi));
    
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
  public void testAssignOneStaffToMultiple() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    PatientInterface beth = clinic.findPatient("Beth", "Bunion");
    ClinicalStaffInterface amy = (ClinicalStaffInterface) clinic.getEmployees().get(0);
    clinic.assignStaff(beth, amy);
    clinic.assignStaff(aandi, amy);
    assertTrue(beth.getAllocated().contains(amy));
    assertTrue(aandi.getAllocated().contains(amy));  
  }
  
  @Test
  public void testAssignMultipleStaffToOne() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    ClinicalStaffInterface benny = (ClinicalStaffInterface) clinic.getEmployees().get(1);
    ClinicalStaffInterface amy = (ClinicalStaffInterface) clinic.getEmployees().get(0);
    clinic.assignStaff(aandi, benny);
    clinic.assignStaff(aandi, amy);
    assertTrue(aandi.getAllocated().contains(benny));
    assertTrue(aandi.getAllocated().contains(amy));  
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void assignDeactivated() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    ClinicalStaffInterface amy = (ClinicalStaffInterface) clinic.getEmployees().get(0);
    amy.deactivate();
    clinic.assignStaff(aandi, amy);
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
    RoomInterface room1 = clinic.getRoomFromNumber(1);
    assertEquals("Front Waiting Room", room1.getRoomName());
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
    RoomInterface room1 = clinic.getRoomFromNumber(1);
    room1.isWaitingRoom();
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
    RoomInterface room1 = clinic.getRoomFromNumber(2);
    assertTrue(room1.isOccupied());
    PatientInterface beth = clinic.findPatient("Beth", "Bunion");
    room1.removePatient(beth);
    assertFalse(room1.isOccupied());
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
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    assertEquals(1, aandi.getRoomNumber());
  }
  
  @Test
  public void testGetFirstName() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    assertEquals("Aandi", aandi.getFirstName());
  }
  
  @Test
  public void testGetLastName() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    assertEquals("Acute", aandi.getLastName());
  }
  
  @Test
  public void testGetDateOfBirth() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    assertEquals("1/1/1981", aandi.getDateOfBirth());
  }
  
  @Test
  public void testGetApproval() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    assertFalse(aandi.getApproval());
  }
  
  @Test
  public void testSetRoomNumber() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    aandi.setRoomNumber(2);
    assertEquals(2, aandi.getRoomNumber());
  }
  
  @Test
  public void testSetApproval() {
    ClinicalStaff john = new ClinicalStaff("Doctor",
        "John", "Smith", EducationLevel.DOCTORAL, "1000000000");
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    aandi.setApproval(john, true);
    assertTrue(aandi.getApproval());
  }
  
  @Test
  public void testRemoveClinicalStaffmember() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    ClinicalStaffInterface amy = (ClinicalStaffInterface) clinic.getEmployees().get(0);
    aandi.getAllocated().add(amy);
    aandi.removeClinicalStaffMember(amy);
    List<ClinicalStaffInterface> temp = aandi.getAllocated();
    assertFalse(temp.contains(amy));
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
  
  @Test
  public void testGetLastPatientRecord() {
    PatientInterface2 aandi = clinic.findPatient("Aandi", "Acute");
    aandi.addRecord("Headache", 33);
    aandi.addRecord("Stomachache", 33);
    Record record1 = aandi.getVisitRecord().get(aandi.getVisitRecord().size() - 1);
    assertEquals(record1, aandi.getMostRecentVisit());
  }
  
  @Test
  public void testCreatePatientRecord() {
    PatientInterface2 aandi = clinic.findPatient("Aandi", "Acute");
    aandi.addRecord("Headache", 33);
    assertTrue(!aandi.getVisitRecord().isEmpty());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPatientRecord() {
    PatientInterface2 aandi = clinic.findPatient("Aandi", "Acute");
    aandi.addRecord(null, 32);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTemperature() {
    PatientInterface2 aandi = clinic.findPatient("Aandi", "Acute");
    aandi.addRecord("Headache", -21);
  }
  
  @Test
  public void testZeroTemperature() {
    PatientInterface2 aandi = clinic.findPatient("Aandi", "Acute");
    aandi.addRecord("Feeling sick", 0);
    assertTrue(!aandi.getVisitRecord().isEmpty());
  }
  
  @Test
  public void testMultipleVisitRecords() {
    PatientInterface2 aandi = clinic.findPatient("Aandi", "Acute");
    aandi.addRecord("Feeling sick", 32);
    aandi.addRecord("Headache", 31);
    assertEquals(2, aandi.getVisitRecord().size());
  }
  
  @Test
  public void testTemperatureWithDecimal() {
    PatientInterface2 aandi = clinic.findPatient("Aandi", "Acute");
    aandi.addRecord("Feeling sick", 32.1);
    assertTrue(!aandi.getVisitRecord().isEmpty());
  }
  
  @Test
  public void testPatientToStringNoRecords() {
    PatientInterface2 aandi = clinic.findPatient("Aandi", "Acute");
    assertEquals("Patient Name: Aandi Acute\n"
        + "Date of Birth: 1/1/1981\n"
        + "Room Number: 1\n"
        + "Assigned Staff: None\n"
        + "Visit Info: Patient has no visit records.", aandi.toString().trim());  
  }
  
  @Test
  public void testPatientToString() {
    PatientInterface2 aandi = clinic.findPatient("Aandi", "Acute");
    aandi.addRecord("Headache", 29.5);
    assertEquals("Patient Name: Aandi Acute\n"
            + "Date of Birth: 1/1/1981\n"
            + "Room Number: 1\n"
            + "Assigned Staff: None\n"
            + "Visit Info: \n"
            + "  Chief Complaint: Headache\n"
            + "  Temperature: 29.5 °C\n"
            + "  Visit Date: " + aandi.getMostRecentVisit().getDate(), aandi.toString().trim());

  }
  
  @Test
  public void testMultipleRecordsPatientToString() {
    PatientInterface2 aandi = clinic.findPatient("Aandi", "Acute");
    aandi.addRecord("Headache", 29.5);
    aandi.addRecord("Stomachache", 30.0);
    assertEquals("Patient Name: Aandi Acute\n"
            + "Date of Birth: 1/1/1981\n"
            + "Room Number: 1\n"
            + "Assigned Staff: None\n"
            + "Visit Info: \n"
            + "  Chief Complaint: Headache\n"
            + "  Temperature: 29.5 °C\n"
            + "  Visit Date: " + aandi.getMostRecentVisit().getDate() + "\n"
            + "\n"
            + "  Chief Complaint: Stomachache\n"
            + "  Temperature: 30.0 °C\n"
         + "  Visit Date: " + aandi.getMostRecentVisit().getDate().trim(), aandi.toString().trim());
  }
  
  @Test
  public void testRoomToString() {
    PatientInterface2 clive = clinic.findPatient("Clive", "Cardiac");
    clive.addRecord("Sick", 30);
    RoomInterface room1 = clinic.getRoomFromNumber(3);
    String roomDisplay = room1.toString();
    assertEquals("Room Name: Inside Waiting Room\n"
        + "Room Type: waiting\n"
        + "Patient Details: \n"
        + "---------------------------------------\n"
        + "Clive Cardiac, Room Number: 3, Clinical Staff Allocated: None\n"
        + "   Chief Complaint: Sick\n"
        + "---------------------------------------", roomDisplay);
  }
  
  @Test
  public void testRoomToStringMultipleRecords() {
    PatientInterface2 clive = clinic.findPatient("Clive", "Cardiac");
    clive.addRecord("Sick", 30);
    clive.addRecord("Hungry", 30);
    RoomInterface room1 = clinic.getRoomFromNumber(3);
    String roomDisplay = room1.toString();
    assertEquals("Room Name: Inside Waiting Room\n"
        + "Room Type: waiting\n"
        + "Patient Details: \n"
        + "---------------------------------------\n"
        + "Clive Cardiac, Room Number: 3, Clinical Staff Allocated: None\n"
        + "   Chief Complaint: Hungry\n"
        + "---------------------------------------", roomDisplay);
  }
  
  @Test
  public void testRoomToStringNoRecords() {
    RoomInterface room1 = clinic.getRoomFromNumber(3);
    String roomDisplay = room1.toString();
    assertEquals("Room Name: Inside Waiting Room\n"
        +   "Room Type: waiting\n"
        + "Patient Details: \n"
        + "---------------------------------------\n"
        + "Clive Cardiac, Room Number: 3, Clinical Staff Allocated: None\n"
        + "   Patient has no Visit Records\n"
        + "---------------------------------------", roomDisplay);
  }
  
  @Test
  public void testClinicalStaffToStringDoctor() {
    ClinicalStaffInterface doctor = (ClinicalStaffInterface) clinic.getEmployees().get(0);
    assertEquals("Job Title: physician\n"
        + "Clinical Staff Name: Dr. Amy Anguish\n"
        + "Education Level: doctoral\n"
        + "NPI Level: 1234567890", doctor.toString());
  }
  
  @Test
  public void testClinicalStaffToStringNurse() {
    ClinicalStaffInterface nurse = (ClinicalStaffInterface) clinic.getEmployees().get(2);
    assertEquals("Job Title: nurse\n"
        + "Clinical Staff Name: Nurse Camila Crisis\n"
        + "Education Level: doctoral\n"
        + "NPI Level: 2224443338", nurse.toString());
  }
  
  @Test
  public void testClinicalStaffNeither() {
    clinic.addNewClinicalStaff("Aid", "Allen", "Smith", EducationLevel.DOCTORAL, "1234567890");
    ClinicalStaffInterface staff = (ClinicalStaffInterface) 
        clinic.getEmployees().get(clinic.getEmployees().size() - 1);
    assertEquals("Job Title: Aid\n"
        + "Clinical Staff Name: Aid Allen Smith\n"
        + "Education Level: doctoral\n"
        + "NPI Level: 1234567890", staff.toString());
  }
  
  @Test
    public void testCommand1PatientDisplay() {
    StringBuilder out = new StringBuilder();
    String passIn = "1\n1\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("1\n1\nq\n", out.toString());
  }
  
  @Test
  public void testCommand2DisplayRoom() {
    StringBuilder out = new StringBuilder();
    String passIn = "2\n1\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("2\n1\nq\n", out.toString());
  }
  
  @Test
  public void testCommand3SeatingChart() {
    StringBuilder out = new StringBuilder();
    String passIn = "3\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("3\nq\n", out.toString());
  }
  
  @Test
  public void testCommand4AddPatient() {
    StringBuilder out = new StringBuilder();
    String passIn = "4\nSteve\nJohnson\n03/05/2000\nHeadache\n32\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("4\nSteve\nJohnson\n03/05/2000\nHeadache\n32\nq\n", out.toString());
  }
  
  @Test
  public void testCommand4NameEmpty() {
    StringBuilder out = new StringBuilder();
    String passIn = "4\n\nSteve\nJohnson\n03/05/2000\nHeadache\n32\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("4\n\nSteve\nJohnson\n03/05/2000\nHeadache\n32\nq\n", out.toString());
  }
  
  @Test
  public void testCommand4TempEmpty() {
    StringBuilder out = new StringBuilder();
    String passIn = "4\n\nSteve\nJohnson\n03/05/2000\nHeadache\n32\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("4\n\nSteve\nJohnson\n03/05/2000\nHeadache\n32\nq\n", out.toString());
  }
  
  @Test
  public void testCommand5NewClinStaff() {
    StringBuilder out = new StringBuilder();
    String passIn = "5\n\nphysician\nSteve\nJohnson\nmasters\n0123456789\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("5\n\nphysician\nSteve\nJohnson\nmasters\n0123456789\nq\n", out.toString());
  }
  
  @Test
  public void testCommand5NewClinStaffInavlid() {
    StringBuilder out = new StringBuilder();
    String passIn = "5\n\nphysician\nSteve\n\nJohnson\nabc\nmasters\n0123456789\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("5\n\nphysician\nSteve\n\nJohnson\nabc\nmasters\n0123456789\nq\n", out.toString());
  }
  
  @Test
  public void testCommand6ExistingPatient() {
    StringBuilder out = new StringBuilder();
    String passIn = "6\nAandi\nAcute\nHeadache\n32\nq";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("6\nAandi\nAcute\nHeadache\n32\nq\n", out.toString());
  }
  
  @Test
  public void testCommand6NameNotFound() {
    StringBuilder out = new StringBuilder();
    String passIn = "6\nAlex\nAcute\nAandi\nAcute\nHeadache\n32\nq";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("6\nAlex\nAcute\nAandi\nAcute\nHeadache\n32\nq\n", out.toString());
  }
  
  @Test
  public void testCommand6NameQuit() {
    StringBuilder out = new StringBuilder();
    String passIn = "6\nq\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("6\nq\nq\n", out.toString());
  }
  
  @Test
  public void testCommand7() {
    StringBuilder out = new StringBuilder();
    String passIn = "7\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("7\nq\n", out.toString());
  }
  
  @Test
  public void testCommand8() {
    StringBuilder out = new StringBuilder();
    String passIn = "8\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("8\nq\n", out.toString());
  }
  
  @Test
  public void testCommand9() {
    StringBuilder out = new StringBuilder();
    String passIn = "9\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("9\nq\n", out.toString());
  }
  
  @Test
  public void invalidPatient() {
    StringBuilder out = new StringBuilder();
    String passIn = "1\n12\n\nq\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("1\n12\n\nq\nq\n", out.toString());   
  }
  
  @Test
  public void invalidRoom() {
    StringBuilder out = new StringBuilder();
    String passIn = "2\n12\n3\n3\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("2\n12\n3\n3\nq\n", out.toString());   
  }
  
  @Test(expected = UnsupportedOperationException.class)
  public void testInvalidCommand() {
    StringBuilder out = new StringBuilder();
    String passIn = "12";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController(in, out, clinic);
    mockcontroller.go();
    assertEquals("1", out.toString());   
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testDeactivateStaffNullArgument() {
    Staff member = null;
    clinic.deactivateStaff(member);
  }
  
  @Test
  public void testDeactivateStaff() {
    Staff member = clinic.getEmployees().get(5);
    clinic.deactivateStaff(member);
    assertFalse(clinic.getEmployees().contains(member));
    assertEquals(5, clinic.getNumStaff());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testUnassignClinStaffNullArgument() {
    PatientInterface2 aandi = null;
    ClinicalStaffInterface amy = (ClinicalStaffInterface) clinic.getEmployees().get(0);
    clinic.unassignClinStaff(amy, aandi);
  }
  
  @Test
  public void testUnassignClinStaff() {
    PatientInterface2 aandi = clinic.findPatient("Aandi", "Acute");
    ClinicalStaffInterface amy = (ClinicalStaffInterface) clinic.getEmployees().get(0);
    clinic.assignStaff(aandi, amy);
    clinic.unassignClinStaff(amy, aandi);
    assertFalse(aandi.getAllocated().contains(amy));
  }
  
  @Test
  public void testListClinWithPatientInfo() {
    PatientInterface2 aandi = clinic.findPatient("Aandi", "Acute");
    ClinicalStaffInterface amy = (ClinicalStaffInterface) clinic.getEmployees().get(0);
    clinic.assignStaff(aandi, amy);
    String test = clinic.listClinWithPatient();
    assertEquals("Clinical Staff: Amy Anguish\n"
           + "  Assigned Patient: Aandi Acute", test);
  }
  
  @Test
  public void testListPatientNoVisitOneYear() {
    PatientInterface2 aandi = clinic.findPatient("Aandi", "Acute");
    LocalDateTime dateTime = LocalDateTime.parse("2020-11-11T19:09:48.601523100");
    PatientInterface2 beth = clinic.findPatient("Beth", "Bunion");
    LocalDateTime dateTime2 = LocalDateTime.parse("2019-11-11T19:09:48.601523100");
    PatientInterface2 clive = clinic.findPatient("Clive", "Cardiac");
    aandi.addOldRecord("Headache", 2.0, dateTime);
    beth.addOldRecord("Stomachache", 10.0, dateTime2);
    clive.addRecord("Neck Pain", 12.0);
    
    assertEquals("Patient Name: Aandi Acute\n"
             + "Date of Birth: 1/1/1981\n"
             + "Room Number: 1\n"
            + "Assigned Staff: None\n"
            + "Visit Info: \n"
            + "  Chief Complaint: Headache\n"
            + "  Temperature: 2.0 °C\n"
            + "  Visit Date: 11/11/2020 19:09:48\n"
            + " ------------------------------- \n"
            + "Patient Name: Beth Bunion\n"
            + "Date of Birth: 2/2/1982\n"
            + "Room Number: 2\n"
            + "Assigned Staff: None\n"
            + "Visit Info: \n"
            + "  Chief Complaint: Stomachache\n"
            + "  Temperature: 10.0 °C\n"
            + "  Visit Date: 11/11/2019 19:09:48\n"
            + " -------------------------------", clinic.listPatientVisitMoreThanYear());
  }
  
  @Test
  public void testVisitTwiceOneYear() {
    PatientInterface2 aandi = clinic.findPatient("Aandi", "Acute");
    LocalDateTime dateTime = LocalDateTime.parse("2024-11-11T19:09:48.601523100");
    PatientInterface2 beth = clinic.findPatient("Beth", "Bunion");
    LocalDateTime dateTime2 = LocalDateTime.parse("2020-11-11T19:09:48.601523100");
    LocalDateTime dateTime3 = LocalDateTime.parse("2024-11-12T19:09:48.601523100");
    aandi.addOldRecord("Headache", 2.0, dateTime);
    aandi.addOldRecord("Headache", 2.0, dateTime3);
    PatientInterface2 clive = clinic.findPatient("Clive", "Cardiac");
    beth.addOldRecord("Stomachache", 10.0, dateTime2);
    clive.addRecord("Neck Pain", 12.0);
    
    assertEquals("Patient Name: Aandi Acute\n"
          + "Date of Birth: 1/1/1981\n"
          + "Room Number: 1\n"
          + "Assigned Staff: None\n"
          + "Visit Info: \n"
          + "  Chief Complaint: Headache\n"
          + "  Temperature: 2.0 °C\n"
          + "  Visit Date: 11/11/2024 19:09:48\n\n"
          + "  Chief Complaint: Headache\n"
          + "  Temperature: 2.0 °C\n"
          + "  Visit Date: 11/12/2024 19:09:48\n"
          + " -------------------------------", clinic.listVisitTwiceOneYear());
  }
  
  @Test
  public void testCommand10PatientDisplay() {
    StringBuilder out = new StringBuilder();
    String passIn = "10\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController2(in, out, clinic);
    mockcontroller.go();
    assertEquals("10\nq\n", out.toString());
  }
  
  @Test
  public void testCommand11DisplayMap() {
    StringBuilder out = new StringBuilder();
    String passIn = "11\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController2(in, out, clinic);
    mockcontroller.go();
    assertEquals("11\nq\n", out.toString());
  }
  
  @Test
  public void testCommand12DeactivateSelectedClinStaff() {
    StringBuilder out = new StringBuilder();
    String passIn = "12\n1\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController2(in, out, clinic);
    mockcontroller.go();
    assertEquals("12\n1\nq\n", out.toString());
  }
  
  @Test
  public void testCommand12DeactivateSelectedNonClinStaff() {
    StringBuilder out = new StringBuilder();
    String passIn = "12\n6\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController2(in, out, clinic);
    mockcontroller.go();
    assertEquals("12\n6\nq\n", out.toString());
  }
  
  @Test
  public void testCommand12DeactivateSelectedQuitEarly() {
    StringBuilder out = new StringBuilder();
    String passIn = "12\nq\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController2(in, out, clinic);
    mockcontroller.go();
    assertEquals("12\nq\nq\n", out.toString());
  }
  
  @Test
  public void testCommand12DeactivateSelectedOutOfBounds() {
    StringBuilder out = new StringBuilder();
    String passIn = "12\n8\n\nq\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController2(in, out, clinic);
    mockcontroller.go();
    assertEquals("12\n8\n\nq\nq\n", out.toString());
  }
  
  @Test
  public void testCommand12DeactivateSelectedWrongNumberFormat() {
    StringBuilder out = new StringBuilder();
    String passIn = "12\nfour\n\nq\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController2(in, out, clinic);
    mockcontroller.go();
    assertEquals("12\nfour\n\nq\nq\n", out.toString());
  }
  
  @Test
  public void testCommand13DisplayPatientsMoreThanYear() {
    StringBuilder out = new StringBuilder();
    String passIn = "13\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController2(in, out, clinic);
    mockcontroller.go();
    assertEquals("13\nq\n", out.toString());
  }
  
  @Test
  public void testCommand14DisplayPatientsTwiceOneYear() {
    StringBuilder out = new StringBuilder();
    String passIn = "14\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController2(in, out, clinic);
    mockcontroller.go();
    assertEquals("14\nq\n", out.toString());
  }
  
  @Test
  public void testCommand15UnassignClinicalStaffFromPatientNoAssigned() {
    StringBuilder out = new StringBuilder();
    String passIn = "15\n1\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController2(in, out, clinic);
    mockcontroller.go();
    assertEquals("15\n1\nq\n", out.toString());
  }
  
  @Test
  public void testCommand15UnassignClinicalStaffFromPatientQuitEarly() {
    StringBuilder out = new StringBuilder();
    String passIn = "15\nq\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController2(in, out, clinic);
    mockcontroller.go();
    assertEquals("15\nq\nq\n", out.toString());
  }
  
  @Test
  public void testCommand15UnassignClinicalStaffFromPatientSuccessfully() {
    StringBuilder out = new StringBuilder();
    PatientInterface2 aandi = clinic.findPatient("Aandi", "Acute");
    ClinicalStaffInterface amy  = (ClinicalStaffInterface) clinic.getEmployees().get(0);
    clinic.assignStaff(aandi, amy);
    String passIn = "15\n1\n1\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController2(in, out, clinic);
    mockcontroller.go();
    assertEquals("15\n1\n1\nq\n", out.toString());
  }
  
  @Test
  public void testCommand15UnassignClinicalStaffFromPatientInvalidNumber() {
    StringBuilder out = new StringBuilder();
    String passIn = "15\n8\n\nq\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController2(in, out, clinic);
    mockcontroller.go();
    assertEquals("15\n8\n\nq\nq\n", out.toString());
  }
  
  @Test
  public void testCommand15UnassignClinicalStaffFromPatientInvalidNumberFormat() {
    StringBuilder out = new StringBuilder();
    String passIn = "15\nFour\n\nq\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController2(in, out, clinic);
    mockcontroller.go();
    assertEquals("15\nFour\n\nq\nq\n", out.toString());
  }
  
  @Test
  public void testCommand16ListClinAndAssignedPatients() {
    StringBuilder out = new StringBuilder();
    String passIn = "16\nq\n";
    Reader in = new StringReader(passIn);
    ClinicControllerInterface mockcontroller = new MockClinicController2(in, out, clinic);
    mockcontroller.go();
    assertEquals("16\nq\n", out.toString());
  }
  
  @Test
  public void testSendHomeOccupied() {
    PatientInterface2 beth = clinic.findPatient("Beth", "Bunion");
    ClinicalStaffInterface amy = (ClinicalStaffInterface) clinic.getEmployees().get(0);
    RoomInterface room = clinic.getRoomFromNumber(2);
    clinic.sendHome(beth, amy);
    assertEquals(false, room.isOccupied());
  }
  
  @Test
  public void testReset() {  
    ClinicInterface3 clin = new Clinic3("Cybernetic Implant Clinic", 
            5, 6, 7, clinic.getRoomFromNumber(1));
    clinic.reset();
    clin.reset();
    assertEquals(clin.toString(), clinic.toString());
  }
  
  @Test
  public void testFeatureLoadClinic() {
    
    MockView mockView = new MockView();
    StringBuilder out = new StringBuilder();
    Readable in = new InputStreamReader(System.in);
    ClinicInterface3 mockModel = new MockModel(out);
    Features controller = new TestClinicControllerGui(in, out, mockModel, mockView);
    controller.loadClinicFile();
    assertTrue(mockView.refreshCalled);
    assertTrue(mockView.setMenuItemsCalled);
  }
  
  @Test
  public void testActivate() {
    PatientInterface2 aandi = clinic.findPatient("Aandi", "Acute");
    ClinicalStaffInterface amy = (ClinicalStaffInterface) clinic.getEmployees().get(0);
    clinic.sendHome(aandi, amy);
    PatientInterface3 aandi1 = (PatientInterface3) aandi;
    aandi1.activate();
    assertTrue(aandi.isActive());
  }
  
  @Test
  public void testFeatureClearRecords() {
    MockView mockView = new MockView();
    StringBuilder out = new StringBuilder();
    ClinicInterface3 mockModel = new MockModel(out);
    Readable in = new InputStreamReader(System.in);
    Features controller = new TestClinicControllerGui(in, out, mockModel, mockView);
    controller.clearAllRecords();
    assertTrue(mockView.refreshCalled);
    assertTrue(mockView.setMenuItemsCalled);
    assertEquals("Reset Called.", out.toString());
  }
  
  @Test
  public void testFeatureExitProgram() {
    MockView mockView = new MockView();
    StringBuilder out = new StringBuilder();
    ClinicInterface3 mockModel = new MockModel(out);
    Readable in = new InputStreamReader(System.in);
    Features controller = new TestClinicControllerGui(in, out, mockModel, mockView);
    controller.exitProgram();
    assertFalse(mockView.refreshCalled);
    assertFalse(mockView.setMenuItemsCalled);
  }
  
  @Test
  public void testFeatureRegisterNewPatient() {
    MockView mockView = new MockView();
    StringBuilder out = new StringBuilder();
    ClinicInterface3 mockModel = new MockModel(out);
    Readable in = new InputStreamReader(System.in);
    Features controller = new TestClinicControllerGui(in, out, mockModel, mockView);
    controller.registerNewPatient();
    assertTrue(mockView.refreshCalled);
    assertFalse(mockView.setMenuItemsCalled);
    assertEquals("First LastFirst Last dobgot patients", out.toString());
  }
  
  @Test
  public void testFeatureAssignPatientToRoom() {
    MockView mockView = new MockView();
    StringBuilder out = new StringBuilder();
    ClinicInterface3 mockModel = new MockModel(out);
    Readable in = new InputStreamReader(System.in);
    Features controller = new TestClinicControllerGui(in, out, mockModel, mockView);
    controller.assignPatientToRoom();
    assertTrue(mockView.refreshCalled);
    assertFalse(mockView.setMenuItemsCalled);
    assertEquals("First LastFirst Last dobgot patients", out.toString());
  }
  
}
