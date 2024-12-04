package view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * master view class to handle different panel displays.
 */
public class MasterView extends JFrame implements MasterViewInterface {

  private static final long serialVersionUID = 1L;
  private JPanel currentPanel;
  
  /**
   * Constructor for the master view frame.
   */
  public MasterView() {
    this.setTitle("Clinic Management System");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(800, 600);
    
    JPanel aboutPage = new AboutPage();
    
    this.setLayout(new BorderLayout());
    
    this.add(aboutPage, BorderLayout.CENTER);
    currentPanel = aboutPage;
    
    aboutPage.setVisible(true);
  }
  
  @Override
  public void switchPanel(JPanel newPanel) {
    if (currentPanel != null) {
      currentPanel.setVisible(false);
    }
    this.add(newPanel, BorderLayout.CENTER);
    newPanel.setVisible(true);
    currentPanel = newPanel;
  }
  

}
