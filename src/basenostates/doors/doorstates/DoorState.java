package basenostates.doors.doorstates;

import basenostates.doors.Door;

/**
 * The DoorState class is an abstract representation of a door's state in te system.
 * It defines the common interface and behavior that all concrete door states must implement.
 *
 * This class is the abstract parent class for the State Pattern.
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

  public abstract void open();
  public abstract void close();
  public abstract void lock();
  public abstract void unlock();
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
