package view.util;

import java.awt.Container;
import java.awt.GridBagConstraints;
import javax.swing.JButton;

/**
 * Represents a helper class for UI components.
 */
public class UiHelper {
  private UiHelper() {
    // Private constructor to prevent instantiation
  }

  /**
   * Adds a button to the container.
   *
   * @param container the container to add the button to
   * @param gbc the grid bag constraints to use
   * @param y the y position of the button
   * @param button the button to add
   */
  public static void addButton(Container container, GridBagConstraints gbc, int y, JButton button) {
    gbc.gridx = 0;
    gbc.gridy = y;
    gbc.gridwidth = 2;
    container.add(button, gbc);
  }
}
