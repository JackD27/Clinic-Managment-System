package main;

import controller.ClinicController;
import java.util.HashMap;
import javax.swing.SwingUtilities;
import model.clinic.Clinic;
import view.MainView;

/**
 * Represents the main class of the clinic application.
 */
public class Main {

  /**
   * The main method of the clinic application.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      Clinic clinic = new Clinic("Clinic", new HashMap<>(), new HashMap<>());
      MainView view = new MainView();
      new ClinicController(clinic, view);
    });
  }
}
