package view;

import clinic.ClinicInterface3;
import clinic.ClinicStaffAndPatientInfo;
import clinic.PatientInterface;
import clinic.PatientInterface2;
import clinic.Room;
import clinic.RoomInterface;
import controller.Features;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


/**
 * Panel for the Clinic display.
 */
public class ClinicLayoutPage extends JPanel implements ClinicViewInterface {
  private static final long serialVersionUID = 1L;
  private final Map<RoomInterface, JButton> roomButtons;
  private final Map<String, JPanel> roomPatients;
  private final Map<PatientInterface, JButton> patientButtons;
  private  PatientInterface patient;
  private RoomInterface room;
  
  /**
   * Creates the layout  of clinic.
   * @param clinic the clinic model.
   */
  public ClinicLayoutPage(ClinicInterface3 clinic) {
    this.setLayout(new BorderLayout());
    roomButtons = new HashMap<>();
    roomPatients = new HashMap<>();
    patientButtons = new HashMap<>();
    
    JLabel clinicNameLabel = new JLabel(clinic.getClinicName(), SwingConstants.CENTER);
    clinicNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
    clinicNameLabel.setPreferredSize(new Dimension(800, 50));
    this.add(clinicNameLabel, BorderLayout.NORTH);
    
    JPanel roomPanelContainer = new JPanel();
    roomPanelContainer.setLayout(new GridLayout(0, 3, 10, 10));
    roomPanelContainer.setPreferredSize(new Dimension(800, 600));
    
    List<RoomInterface> rooms = clinic.getRooms();
    
    for (RoomInterface room : rooms) {
      JPanel roomPanel = new JPanel();
      roomPanel.setLayout(new BorderLayout());
      roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
      
      JButton roomButton = new JButton(room.getRoomName());
      roomButton.setFont(new Font("Arial", Font.BOLD, 20));
      roomButton.setForeground(Color.BLACK);
      roomButton.setEnabled(false);
      roomPanel.add(roomButton, BorderLayout.NORTH);
      roomButtons.put(room, roomButton);
      
      JPanel patientPanel = new JPanel();
      patientPanel.setLayout(new BoxLayout(patientPanel, BoxLayout.Y_AXIS));
      roomPatients.put(room.getRoomName(), patientPanel);
      
      updatePatientButtons(room, patientPanel);
      JScrollPane patientScroll = new JScrollPane(patientPanel);
      
      roomPanel.add(patientScroll, BorderLayout.CENTER);
      roomPanelContainer.add(roomPanel);
    }
    JScrollPane scrollableRoomPanelContainer = new JScrollPane(roomPanelContainer);
    this.add(scrollableRoomPanelContainer, BorderLayout.CENTER);
  }
  
  private void updatePatientButtons(RoomInterface room, JPanel patientPanel) {
    
    patientPanel.removeAll();
    
    UIManager.put("Button.disabledText", Color.BLACK);
    UIManager.put("Button.disabledBackground", Color.LIGHT_GRAY);
    List<PatientInterface> patients = room.getResidents();
    for (PatientInterface patient : patients) {
      PatientInterface2 patient2 = (PatientInterface2) patient;
      if (patient2.isActive()) {
        StringBuilder patientName = new StringBuilder();
        patientName.append(patient.getFirstName() + " " + patient.getLastName());
        JButton patientButton = new JButton(patientName.toString());
        patientButton.setFont(new Font("Arial", Font.PLAIN, 20));
        patientButton.setEnabled(false);
      
        patientPanel.add(patientButton);
        patientButtons.put(patient, patientButton);
      }
    }
    patientPanel.revalidate();
    patientPanel.repaint();
  }
  
  
  
  @Override 
  public void enableRoomSelectionAssignRoom(Features f) {
    for (Map.Entry<RoomInterface, JButton> entry : roomButtons.entrySet()) {
      JButton button = entry.getValue();
      RoomInterface room = entry.getKey();
      button.setEnabled(true);
      button.addActionListener(l -> f.processRoom(room));
    }
  }

  @Override
  public void disableRoomSelection() {
    for (JButton button : roomButtons.values()) {
      button.setEnabled(false);
      for (ActionListener listener : button.getActionListeners()) {
        button.removeActionListener(listener);
      }
    }
  }

  @Override
  public void enablePatientSelectionAssignRoom(Features f) {
    for (Map.Entry<PatientInterface, JButton> entry : patientButtons.entrySet()) {
      JButton button = entry.getValue();
      PatientInterface patient = entry.getKey();
      button.setEnabled(true);
      button.addActionListener(l -> f.processPatient(patient));
    }
  }
  
  @Override
  public void enablePatientSelectionDisplayPatient(Features f) {
    for (Map.Entry<PatientInterface, JButton> entry : patientButtons.entrySet()) {
      JButton button = entry.getValue();
      PatientInterface patient = entry.getKey();
      button.setEnabled(true);
      button.addActionListener(l -> f.processPatientDisplayPatient(patient));
    }
  }
  
  @Override
  public void enablePatientSelectionUnassignStaff(Features f) {
    for (Map.Entry<PatientInterface, JButton> entry : patientButtons.entrySet()) {
      JButton button = entry.getValue();
      PatientInterface patient = entry.getKey();
      button.setEnabled(true);
      button.addActionListener(l -> f.processPatientUnassignStaff(patient));
    }
  }
  
  @Override
  public void enablePatientSelectionSendHome(Features f) {
    for (Map.Entry<PatientInterface, JButton> entry : patientButtons.entrySet()) {
      JButton button = entry.getValue();
      PatientInterface patient = entry.getKey();
      button.setEnabled(true);
      button.addActionListener(l -> f.processPatientSendHome(patient));
    }
  }

  @Override
  public void disablePatientSelection() {
    for (JButton button : patientButtons.values()) {
      button.setEnabled(false);
      
      for (ActionListener listener : button.getActionListeners()) {
        button.removeActionListener(listener);
      }
    }
  }
  

  @Override
  public void enablePatientSelectionAssignStaff(Features f) {
    for (Map.Entry<PatientInterface, JButton> entry : patientButtons.entrySet()) {
      JButton button = entry.getValue();
      PatientInterface patient = entry.getKey();
      button.setEnabled(true);
      button.addActionListener(l -> f.processPatientAssignStaff(patient));
    }
  }
}
