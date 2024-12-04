package view;

import clinic.ClinicStaffAndPatientInfo;
import controller.ClinicControllerInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
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
    
    JPanel subPanel = new JPanel();
    subPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    JLabel header = new JLabel("Welcome to the Clinic Management System", JLabel.CENTER);
    header.setFont(new Font("Calibri", Font.BOLD, 30));
    header.setForeground(Color.BLUE);
    subPanel.add(header);
    this.add(subPanel);
    
    StringBuilder sb = new StringBuilder();
    sb.append("Created by: Rajiv Ragavan");
    JTextArea info = new JTextArea(sb.toString());
    info.setBackground(getBackground());
    info.setEditable(false);
    info.setWrapStyleWord(true);
    info.setFont(new Font("Calibri", Font.BOLD, 20));
    info.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.add(info);
  }
  
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
  }

  @Override
  public void showMessage(String message) {
  }

  @Override
  public void clear() {
  }

}
