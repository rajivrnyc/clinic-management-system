package view;

import clinic.ClinicInterface3;
import clinic.ClinicStaffAndPatientInfo;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
  private JMenuItem registerPatient;
  private JMenuItem assignToRoom;
  private JMenuItem assignStaff;
  private JMenuItem displayPatientInfo;
  private JMenuItem unassignStaff;
  private JMenuItem sendHome;
  
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
    
    
    
    registerPatient = new JMenuItem("Register a New Patient");
    assignToRoom = new JMenuItem("Assign Patient to Room");
    assignStaff = new JMenuItem("Assign Clinical Staff to Patient");
    displayPatientInfo = new JMenuItem("Display Patient Info");
    unassignStaff = new JMenuItem("Unassign Clinical Staff");
    sendHome = new JMenuItem("Send Patient Home");
    
    JMenu featuresMenu = new JMenu("Features");
    
    featuresMenu.add(registerPatient);
    featuresMenu.add(assignToRoom);
    featuresMenu.add(assignStaff);
    featuresMenu.add(displayPatientInfo);
    featuresMenu.add(unassignStaff);
    featuresMenu.add(sendHome);
    
    menuBar.add(featuresMenu);
    
    this.setJMenuBar(menuBar);
    
    JPanel aboutPage = new AboutPage();
    
    this.setLayout(new BorderLayout());
    this.add(aboutPage, BorderLayout.CENTER);
    currentPanel = aboutPage;
    
    currentPanel.setVisible(true);
  }
  
  @Override
  public void switchPanel(JPanel newPanel) {
    if (currentPanel != null) {
      currentPanel.setVisible(false);
    }
    this.add(newPanel, BorderLayout.CENTER);
    newPanel.setVisible(true);
    currentPanel = newPanel;
    this.revalidate();
    this.repaint();
  }

  @Override
  public void setFeatures(Features f) {
    loadMenuItem.addActionListener(l -> f.loadClinicFile());
    clearMenuItem.addActionListener(l -> f.clearAllRecords());
    quitMenuItem.addActionListener(l -> f.exitProgram());
    registerPatient.addActionListener(l -> f.registerNewPatient());
    assignToRoom.addActionListener(l -> f.assignPatientToRoom());
    assignStaff.addActionListener(l -> f.assignStafftoPatient());
    displayPatientInfo.addActionListener(l -> f.displayPatientInfo());
    unassignStaff.addActionListener(l -> f.unassignStaffFromPatient());
    sendHome.addActionListener(l -> f.sendPatientHome());
  }
  
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }
  
  @Override
  public void refresh(ClinicInterface3 model) {
    JPanel clinicLayout = new ClinicLayoutPage(model);
    this.switchPanel(clinicLayout);
  }
  

}
