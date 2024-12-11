package controller;


import clinic.Clinic2;
import clinic.Clinic3;
import clinic.ClinicInterface3;
import clinic.ClinicStaffAndPatientInfo;
import clinic.ClinicalStaffInterface;
import clinic.ClinicalStaffInterface2;
import clinic.PatientInterface;
import clinic.PatientInterface2;
import clinic.RoomInterface;
import clinic.Staff;
import clinic.StaffClass;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import view.ClinicLayoutPage;
import view.MasterViewInterface;

/**
 * Controller to support view implementation for the mvc.
 */
public class ClinicControllerGui extends ClinicController2 implements Features {
  private  ClinicInterface3 model;
  private final MasterViewInterface view;
  private ClinicLayoutPage clinicLayoutPage;
  private PatientInterface selectedPatient;
  private RoomInterface room;
  private ClinicalStaffInterface selectedPatientStaff;
  private ClinicalStaffInterface2 selectedStaff;
  
  
  /**
   * Creates a gui controller for view implementation where it takes model and
   * view and updates them based on user input.
   * 
   * @param in user input in the form of text
   * @param out is System output
   * @param model the model of the clinic
   * @param view the view of the clinic
   */
  public ClinicControllerGui(Readable in, Appendable out, ClinicInterface3 model, 
         MasterViewInterface view) {
    super(in, out, model);
    this.model = model;
    this.view = view;
    view.setFeatures(this);
    
    
  }

  @Override
  public void loadClinicFile() {
    try {
      FileReader fileReader = new FileReader("res/clinicfile.txt");
      model = Clinic3.readFile(fileReader);
      view.refresh(model);
      view.setMenuItems(true);
      showMessage("Clinic loaded successfully.");
       
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
  }

  @Override
  public void clearAllRecords() {
    ClinicCommand3 clearCommand = new ClearRecordsView(this.model, this.view);
    clearCommand.execute();

  }

  @Override
  public void exitProgram() {
    ClinicCommand3 exit = new ExitProgramView(this.model, this.view);
    exit.execute();
  }

  @Override
  public void registerNewPatient() {
    ClinicCommand3 register = new RegisterPatientView(this.model, this.view);
    register.execute();
  }
  
  @Override
  public void assignPatientToRoom() {
    PatientToRoomView toRoom = new PatientToRoom(this.model, this.view);
    toRoom.execute();
  }
  
  @Override
  public void assignStafftoPatient() {
    AssignStaffViewInterface assignStaff = new AssignStaffView(this.model, this.view);
    assignStaff.execute();
  }

  @Override
  public void displayPatientInfo() {
    DisplayPatientInterface displayPatient = new DisplayPatientView(this.model, this.view);
    displayPatient.execute();
  }

  @Override
  public void unassignStaffFromPatient() {
    UnassignStaffViewInterface unassignStaff = new UnassignStaffView(this.model, this.view);
    unassignStaff.execute();
  }
  

  @Override
  public void sendPatientHome() {
    SendHomeViewInterface sendHome = new SendHomeView(this.model, this.view);
    sendHome.execute();
  }
  
  @Override
  public void go() {
    view.makeVisible();
  }
  
  /**
   * A function to display a JOptionPane message.
   * 
   * @param message the message to display
   */
  protected void showMessage(String message) {
    JOptionPane.showMessageDialog(null, message);
  }
}
