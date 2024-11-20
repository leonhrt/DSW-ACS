package basenostates.doors.doorstates;

import basenostates.doors.Door;

/**
 * The DoorState class is an abstract representation of a door's state in te system.
 * It defines the common interface and behavior that all concrete door states must implement.
 *
 * <p>This class is the abstract parent class for the State Pattern.
 */
public abstract class DoorState {
  protected Door door;
  protected String name;

  /**
   * Constructs a new DoorState with the specified door.
   *
   * @param door The door that this state is associated with
   */
  public DoorState(Door door) {
    this.door = door;
  }

  /**
   * Method that will allow the user to open the door if conditions
   * are met, depending on door state and permissions.
   */
  public abstract void open();

  /**
   * Method that will allow the user to close the door if conditions
   * are met, depending on door state and permissions.
   */
  public abstract void close();

  /**
   * Method that will allow the user to lock the door if conditions
   * are met, depending on door state and permissions.
   */
  public abstract void lock();

  /**
   * Method that will allow the user to unlock the door if conditions
   * are met, depending on door state and permissions.
   */
  public abstract void unlock();

  /**
   * Method that will allow the user to unlock shortly the door if conditions
   * are met, depending on door state and permissions.
   */
  public abstract void unlockShortly();

  /**
   * Returns the name of the current state of the door.
   *
   * @return The name of the current state
   */
  public String getStateName() {
    return name;
  }
}
