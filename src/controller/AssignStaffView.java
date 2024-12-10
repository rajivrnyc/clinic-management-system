package controller;


import clinic.ClinicInterface3;
import clinic.ClinicalStaffInterface;
import clinic.ClinicalStaffInterface2;
import clinic.PatientInterface;
import clinic.RoomInterface;
import clinic.Staff;
import clinic.StaffClass;
import java.util.ArrayList;
import java.util.List;
import view.ClinicLayoutPage;
import view.MasterViewInterface;

/**
 * Command in view to assign staff to a patient.
 */
public class AssignStaffView implements AssignStaffViewInterface {
  private ClinicInterface3 model;
  private MasterViewInterface view;
  private ClinicLayoutPage clinicLayoutPage;
  private PatientInterface selectedPatient;
  private ClinicalStaffInterface2 selectedStaff;

  @Override
  public void execute() {
    // TODO Auto-generated method stub

  }

  @Override
  public void processPatientAssignStaff(PatientInterface patient) {
    // TODO Auto-generated method stub

  }
  
  private List<ClinicalStaffInterface2> getClinStaff() {
    List<ClinicalStaffInterface2> clin = new ArrayList<>();
    List<Staff> staff = model.getEmployees();
    List<StaffClass> stfClass = new ArrayList<>();
    for (Staff emp : staff) {
      stfClass.add((StaffClass) emp);
    }
    for (StaffClass stf : stfClass) {
      if (stf.isClinicalStaff()) {
        clin.add((ClinicalStaffInterface2) stf);
      }
    }
    return clin;
  }

}
