package basenostates.doors.doorstates;

import basenostates.doors.Door;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The ProppedState class represents the state of a door when it is propped.
 *
 * <p>This class is the implementation of the abstract class DoorState for the State Pattern
 */
public class ProppedState extends DoorState {

  private static final Logger milestone1 = LoggerFactory.getLogger("firstMilestone");

  /**
   * Main constructor of the class, used to define the state of the door
   * to the propped state.
   *
   * @param door The door which will have the LockedState.
   */
  public ProppedState(Door door) {
    super(door);
    name = States.PROPPED;
  }

  /**
   * Notifies the user that the door is propped and cannot be opened.
   */
  @Override
  public void open() {
    milestone1.warn("I mean, the door " + door.getId() + " is open, but propped");
  }

  /**
   * Notifies the user that te door is going to be closed,
   * sets the door to closed and sets the new state (Locked).
   */
  @Override
  public void close() {
    milestone1.info("Closing the door " + door.getId());
    door.setState(new LockedState(door));
    door.setClosed(true);
  }

  /**
   * Notifies the user that the door because it's propped.
   */
  @Override
  public void lock() {
    milestone1.warn("Can't lock the door " + door.getId() + " because it's propped");
  }

  /**
   * Notifies the user that the door is propped, so it can't be unlocked.
   */
  @Override
  public void unlock() {
    milestone1.warn(door.getId() + " it's propped right now, can't unlock");
  }

  /**
   * Notifies the user that the door is propped and can't be unlocked shortly.
   */
  @Override
  public void unlockShortly() {
    milestone1.warn("Can't unlock shortly " + door.getId()
        + " because it's propped");
  }
}
