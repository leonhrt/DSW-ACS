package baseNoStates.doors.doorstates;

import baseNoStates.doors.Door;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The UnlockedState class represents the state of a door when it is unlocked.
 *
 * This class is the implementation of the abstract class DoorState for the State Pattern
 */
public class UnlockedState extends DoorState {

  private static final Logger milestone1 = LoggerFactory.getLogger("firstMilestone");
  //private static final Logger milestone2 = LoggerFactory.getLogger("secondMilestone");
  public UnlockedState(Door door) {
    super(door);
    name = States.UNLOCKED;
  }

  /**
   * Checks if the door is closed.
   * If it is, it opens the door.
   * Otherwise, it notifies the user that the door is already open.
   */
  @Override
  public void open() {
    if (door.isClosed()) {        //Check if the door is closed so it can be opened
      milestone1.info("Opening the door " +door.getId());
      door.setClosed(false);
    } else {
      milestone1.warn("The door " +door.getId()+ " is already open");
    }
  }

  /**
   * Checks if the door is open.
   * If it is, it closes the door.
   * Otherwise, it notifies the user that the door is already closed.
   */
  @Override
  public void close() {
    if (!door.isClosed()) {       //Check if the door is open so it can be closed
      milestone1.info("Closing the door "+door.getId());
      door.setClosed(true);
    } else {
      milestone1.warn("The door " +door.getId()+ "  is already closed");
    }
  }

  /**
   * As the actual state is unlocked, we set the door's state to
   * locked with a new LockedState
   */
  @Override
  public void lock() {
    if (!door.isClosed()) {       //Door cannot be locked if it's open
      milestone1.warn ("Can't lock the door " +door.getId()+ " because it's open");
    } else {
      milestone1.info("Locking the door " + door.getId());
      door.setState(new LockedState(door));
    }
  }

  /**
   * Notifies the user that the door is already unlocked.
   */
  @Override
  public void unlock() {
    milestone1.warn("The door " +door.getId()+ "  is already unlocked");
  }

  /**
   * Notifies the user that an unlocked door cannot be unlocked shortly.
   */
  @Override
  public void unlockShortly() {
    milestone1.warn("The door " +door.getId()+ " is already unlocked, can't unlock shortly");
  }
}
