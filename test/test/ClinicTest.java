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
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Test Class for all classes within clinic package.
 */
public class ClinicTest {
  private ClinicInterface clinic;
  private RoomInterface primaryWaitingRoom;
  private Random random;
  
  /**
   * Sets up testing Environment before conduction tests.
   */
  @Before
  public void setUp() throws IOException {
    random = new Random();
    primaryWaitingRoom = new Room(28, 0, 35, 5, RoomType.WAITING, "Front Waiting Room");
    clinic = Clinic.readFile(new FileReader("res/clinicfile.txt"));
  }
  
  @Test
  public void testClinic() {
    assertEquals(clinic, new Clinic("Cybernetic Implant Clinic", 
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
  public void testCreatePatientRecord() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    aandi.addRecord("Headache", 33);
    assertTrue(!aandi.getVisitRecord().isEmpty());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPatientRecord() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    aandi.addRecord(null, 32);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTemperature() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    aandi.addRecord("Headache", -21);
  }
  
  @Test
  public void testZeroTemperature() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    aandi.addRecord("Feeling sick", 0);
    assertTrue(!aandi.getVisitRecord().isEmpty());
  }
  
  @Test
  public void testMultipleVisitRecords() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    aandi.addRecord("Feeling sick", 32);
    aandi.addRecord("Headache", 31);
    assertEquals(2, aandi.getVisitRecord().size());
  }
  
  @Test
  public void testTemperatureWithDecimal() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    aandi.addRecord("Feeling sick", 32.1);
    assertTrue(!aandi.getVisitRecord().isEmpty());
  }
  
  @Test
  public void testPatientToStringNoRecords() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
    assertEquals("Patient Name: Aandi Acute\n"
        + "Date of Birth: 1/1/1981\n"
        + "Room Number: 1\n"
        + "Assigned Staff: None\n"
        + "Visit Info: Patient has no visit records.", aandi.toString().trim());  
  }
  
  @Test
  public void testPatientToString() {
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
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
    PatientInterface aandi = clinic.findPatient("Aandi", "Acute");
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
    PatientInterface clive = clinic.findPatient("Clive", "Cardiac");
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
    PatientInterface clive = clinic.findPatient("Clive", "Cardiac");
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
  
}
