package model.room;

/**
 * Represents a point in 2D space. A point has an x and y coordinate.
 */
public class Point {
  private final int coordinateX;
  private final int coordinateY;

  /**
   * Creates a new Point object with the given x and y coordinates.
   *
   * @param coordinateX the x coordinate
   * @param coordinateY the y coordinate
   */
  public Point(int coordinateX, int coordinateY) {
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;
  }

  /**
   * Returns the x coordinate of the point.
   *
   * @return the x coordinate
   */
  public int getX() {
    return coordinateX;
  }

  /**
   * Returns the y coordinate of the point.
   *
   * @return the y coordinate
   */

  public int getY() {
    return coordinateY;
  }

  @Override
  public String toString() {
    return "(" + coordinateX + ", " + coordinateY + ")";
  }
}
