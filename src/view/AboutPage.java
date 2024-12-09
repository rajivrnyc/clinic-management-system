package view;

import clinic.ClinicStaffAndPatientInfo;
import clinic.PatientInterface;
import clinic.RoomInterface;
import controller.ClinicControllerInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * Implementation of the displayed view for the clinic management system.
 */
public class AboutPage extends JPanel implements ClinicViewInterface {
  private static final long serialVersionUID = 1L;

  /**
   * Sets up the about page for the clinic.
   * 
   */
  public AboutPage() {
    
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
    this.add(Box.createVerticalStrut(400));
    JLabel header = new JLabel("Welcome to the Clinic Management System", JLabel.CENTER);
    header.setFont(new Font("Calibri", Font.BOLD, 40));
    header.setForeground(Color.BLUE);
    header.setAlignmentX(Component.CENTER_ALIGNMENT);
    header.setAlignmentY(Component.CENTER_ALIGNMENT);
    this.add(header);
    
    this.add(Box.createVerticalStrut(20));
    
    
    
    StringBuilder sb = new StringBuilder();
    sb.append("Created by: Rajiv Ragavan");
    JTextArea info = new JTextArea(sb.toString());
    info.setBackground(getBackground());
    info.setEditable(false);
    info.setWrapStyleWord(true);
    info.setLineWrap(false);
    info.setFont(new Font("Calibri", Font.BOLD, 22));
    info.setAlignmentX(Component.CENTER_ALIGNMENT);
    info.setMaximumSize(new Dimension(240, 60));
    this.add(info);
  }

  @Override
  public void enableRoomSelection(ActionListener onRoomSelected) {
  }

  @Override
  public void disableRoomSelection() {
  }

  @Override
  public void enablePatientSelection(ActionListener onPatientSelected) {
  }

  @Override
  public void disablePatientSelection() {
  }

  @Override
  public PatientInterface getSelectedPatient(JButton selectedButton) {
    return null;
  }

  @Override
  public RoomInterface getSelectedRoom(JButton selectedButton) {
    return null;
  }

}
