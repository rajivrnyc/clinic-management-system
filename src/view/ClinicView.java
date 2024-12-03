package view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
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
public class ClinicView extends JFrame implements ClinicViewInterface {
  private final JTextArea displayArea;
  private final JLabel title;
  private final JButton quitButton;
  private final JButton buttonAction;
  
  /**
   * Sets up the view for the clinic.
   */
  public ClinicView() {
    super("Clinic");
    this.setSize(800, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    this.title = new JLabel("Clinic", SwingConstants.CENTER);
    this.add(title);
    
    this.displayArea = new JTextArea();
    this.displayArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(displayArea);
    this.add(scrollPane, BorderLayout.CENTER);
    
    JPanel buttons = new JPanel();
    this.quitButton = new JButton("Exit");
    this.buttonAction = new JButton("Run Command");
    buttons.add(buttonAction);
    buttons.add(quitButton);
    this.add(buttons);
  }
  
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void addCommandListener(ActionListener listener) {
    this.buttonAction.addActionListener(listener);
  }

  @Override
  public void addQuitListener(ActionListener listener) {
    this.quitButton.addActionListener(listener);
  }

  @Override
  public void showMessage(String message) {
    this.displayArea.append(message);
  }

  @Override
  public void clear() {
    this.displayArea.setText("");
  }

  @Override
  public void exit() {
    this.dispose();

  }

}
