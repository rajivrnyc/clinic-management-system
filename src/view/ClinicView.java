package view;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;


/**
 * Implementation of the displayed view for the clinic management system.
 */
public class ClinicView extends JFrame implements ClinicViewInterface {
  private final JTextArea displayArea;
  private final JPanel buttonPanel;
  private final JButton quitButton;
  private final JButton[] buttonList;
  
  public ClinicView() {
    super("Clinic");
    this.setSize(800,600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
  }
  
  @Override
  public void makeVisible() {
    // TODO Auto-generated method stub
  }

  @Override
  public void addCommandListener(ActionListener listener) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addQuitListener(ActionListener listener) {
    // TODO Auto-generated method stub

  }

  @Override
  public void showMessage(String message) {
    // TODO Auto-generated method stub

  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub

  }

  @Override
  public void exit() {
    // TODO Auto-generated method stub

  }

}
