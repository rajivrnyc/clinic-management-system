package view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * master view class to handle different panel displays.
 */
public class MasterView extends JFrame implements MasterViewInterface {

  private static final long serialVersionUID = 1L;
  private JPanel currentPanel;
  private JMenuBar menuBar;
  private JMenu fileMenu;
  private JMenuItem loadMenuItem;
  private JMenuItem clearMenuItem;
  private JMenuItem quitMenuItem;
  
  /**
   * Constructor for the master view frame.
   */
  public MasterView() {
    this.setTitle("Clinic Management System");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1920, 1080);
    
    menuBar = new JMenuBar();
    fileMenu = new JMenu("File");
    
    loadMenuItem = new JMenuItem("Load Clinic Data");
    clearMenuItem = new JMenuItem("Clear All Records");
    quitMenuItem = new JMenuItem("Quit");
    
    fileMenu.add(loadMenuItem);
    fileMenu.add(clearMenuItem);
    fileMenu.addSeparator();
    fileMenu.add(quitMenuItem);
    
    menuBar.add(fileMenu);
    
    this.setJMenuBar(menuBar);
    
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
