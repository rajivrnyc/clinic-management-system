package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * master view class to handle different panel displays.
 */
public class MasterView extends JFrame implements MasterViewInterface {
  private JPanel currentPanel;
  
  /**
   * Constructor for the master view frame.
   */
  public MasterView() {
    this.setTitle("Clinic Management System");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(800, 600);
    
    ClinicViewInterface aboutPage = new AboutPage();
    
    this.setLayout(new BorderLayout());
    
    this.add(aboutPage, BorderLayout.CENTER);
    currentPanel = aboutPage;
  }
  
  @Override
  public void switchPanel(JPanel newPanel) {
    // TODO Auto-generated method stub

  }

  @Override
  public void makeVisible() {
    // TODO Auto-generated method stub

  }

}
