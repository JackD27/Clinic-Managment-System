package utils;

import java.util.Random;

/**
 * HelperClass class that provides helper methods.
 */
public class HelperClass {
  /**
   * Generates a random string of the given length. For giving an ID to a clinical staff member.
   *
   * @param length the length of the string to generate
   * @return the random string
   */
  public String generateRandomNumericString(int length) {
    String digits = "0123456789";
    Random random = new Random();
    StringBuilder sb = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      int index = random.nextInt(digits.length());
      sb.append(digits.charAt(index));
    }
    return sb.toString();
  }
}

