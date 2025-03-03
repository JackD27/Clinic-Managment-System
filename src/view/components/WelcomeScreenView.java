package view.components;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * A dialog that displays a welcome message to the user.
 */
public class WelcomeScreenView extends JDialog {

  /**
   * Creates a new WelcomeScreenView.
   *
   * @param parent the parent frame
   */
  public WelcomeScreenView(JFrame parent) {
    super(parent, "Welcome", true);
    setLayout(new BorderLayout());
    setSize(400, 200);
    setLocationRelativeTo(parent);
    JLabel welcomeLabel = new JLabel(
            "<html><div style='text-align: center;'>"
                    + "Welcome to the Clinic Management System!<br><br>"
                    + "CSC 5010 Milestone 4 Project<br>"
                    + "By Jackson Davis<br>",
            SwingConstants.CENTER);
    welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    add(welcomeLabel, BorderLayout.CENTER);

    JButton closeButton = new JButton("Continue");
    closeButton.addActionListener(e -> dispose());
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(closeButton);
    add(buttonPanel, BorderLayout.SOUTH);
  }

  /**
   * Displays the dialog.
   */
  public void showDialog() {
    setVisible(true);
  }
}
