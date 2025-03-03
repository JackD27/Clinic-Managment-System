package staff;

import static org.junit.Assert.assertEquals;

import model.staff.ClinicalStaff;
import model.staff.CprLevels;
import model.staff.EducationLevel;
import model.staff.NonClinicalStaff;
import model.staff.StaffType;
import org.junit.Before;
import org.junit.Test;

/**
 * StaffTest class that tests the Staff class.
 */
public class StaffTest {
  private NonClinicalStaff staffNonClinical;
  private ClinicalStaff staffClinicalNurse;
  private ClinicalStaff staffClinicalPhysician;

  /**
   * Sets up the staff for testing.
   */
  @Before
  public void setUp() {
    staffNonClinical = new NonClinicalStaff("reception", "Frank", "Febrile",
            "allied", "B");
    staffClinicalNurse = new ClinicalStaff("nurse", "Nancy", "Danger",
            "masters", "8877665544");
    staffClinicalPhysician = new ClinicalStaff("physician", "John", "Doe", "doctoral",
            "1234567890");
  }

  @Test
  public void getFullName() {
    assertEquals("Frank Febrile", staffNonClinical.getFullName());
    assertEquals("Nancy Danger", staffClinicalNurse.getFullName());
    assertEquals("John Doe", staffClinicalPhysician.getFullName());
  }

  @Test
  public void getTitleAndFullName() {
    assertEquals("Nurse Nancy Danger", staffClinicalNurse.getFullNameWithTitle());
    assertEquals("Frank Febrile", staffNonClinical.getFullName());
    assertEquals("Dr. John Doe", staffClinicalPhysician.getFullNameWithTitle());
  }

  @Test
  public void getStaffType() {
    assertEquals(StaffType.NONCLINICAL, staffNonClinical.getStaffType());
    assertEquals(StaffType.CLINICAL, staffClinicalNurse.getStaffType());
    assertEquals(StaffType.CLINICAL, staffClinicalPhysician.getStaffType());
  }

  @Test
  public void getEducationLevel() {
    assertEquals(EducationLevel.ALLIED, staffNonClinical.getEducationLevel());
    assertEquals(EducationLevel.MASTERS, staffClinicalNurse.getEducationLevel());
    assertEquals(EducationLevel.DOCTORAL, staffClinicalPhysician.getEducationLevel());
  }

  @Test
  public void getCprLevel() {
    assertEquals(CprLevels.B, staffNonClinical.getCprLevel());
  }
}