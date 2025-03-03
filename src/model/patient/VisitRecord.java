package model.patient;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a visit record of a patient. A visit record has a registration date and time, chief
 * complaint, and body temperature.
 */
public record VisitRecord(LocalDateTime registrationTime, String chiefComplaint,
                          double bodyTemp) implements Comparable<VisitRecord> {
  /**
   * Creates a new VisitRecord object with the given registration date and time, chief complaint,
   * and body temperature.
   *
   * @param registrationTime the registration date and time of the visit
   * @param chiefComplaint       the chief complaint of the visit
   * @param bodyTemp      the body temperature of the patient during the visit
   * @throws IllegalArgumentException if the registration date and time or chief complaint is null
   *                                  or if the body temperature is negative
   */
  public VisitRecord(LocalDateTime registrationTime, String chiefComplaint, double bodyTemp) {
    if (registrationTime == null || chiefComplaint == null) {
      throw new IllegalArgumentException("Registration date and time and chief complaint cannot be "
              + "null.");
    }
    if (bodyTemp < 0) {
      throw new IllegalArgumentException("Body temperature cannot be negative. You may have "
              + "hypothermia.");
    }
    this.registrationTime = registrationTime;
    this.chiefComplaint = chiefComplaint;
    this.bodyTemp = Math.round(bodyTemp * 10.0) / 10.0;
  }

  @Override
  public String toString() {
    return "Visit on: " + registrationTime
            + ", Chief Complaint: " + chiefComplaint
            + ", Body Temperature: " + bodyTemp + "°C";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VisitRecord that = (VisitRecord) o;
    return Double.compare(that.bodyTemp, bodyTemp) == 0
            && Objects.equals(registrationTime, that.registrationTime)
            && Objects.equals(chiefComplaint, that.chiefComplaint);
  }

  /**
   * Returns the registration date and time of the visit.
   *
   * @return the registration date and time of the visit
   */
  public LocalDateTime getRegistrationTime() {
    return registrationTime;
  }

  /**
   * Returns the registration date and time of the visit in a formatted string.
   *
   * @return the registration date and time of the visit in a formatted string
   */
  public String getRegistrationTimeFormatted() {
    return registrationTime.toLocalDate() + " " + registrationTime.toLocalTime();
  }

  /**
   * Returns the chief complaint of the visit.
   *
   * @return the chief complaint of the visit
   */
  public String getChiefComplaint() {
    return chiefComplaint;
  }

  /**
   * Returns the body temperature of the patient during the visit.
   *
   * @return the body temperature of the patient during the visit
   */
  public double getBodyTemp() {
    return bodyTemp;
  }

  @Override
  public int hashCode() {
    return Objects.hash(registrationTime, chiefComplaint, bodyTemp);
  }

  @Override
  public int compareTo(VisitRecord o) {
    return this.registrationTime.compareTo(o.registrationTime);
  }
}
