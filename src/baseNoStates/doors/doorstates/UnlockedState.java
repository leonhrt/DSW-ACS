package baseNoStates.doors.doorstates;

import baseNoStates.doors.Door;

public class UnlockedState extends DoorState {
  public UnlockedState(Door door) {
    super(door);
    name = States.UNLOCKED;
  }

  @Override
  public void open() {
    if (door.isClosed()) {        //Check if the door is closed so it can be opened
      System.out.println("Opening the door...");
      door.setClosed(false);
    } else {
      System.out.println("The door is already open");
    }
  }

  @Override
  public void close() {
    if (!door.isClosed()) {       //Check if the door is open so it can be closed
      System.out.println("Closing the door...");
      door.setClosed(true);
    } else {
      System.out.println("The door is already closed");
    }
  }

  //as the actual state is unlocked, we set the door's state to
  //locked with a new LockedState
  @Override
  public void lock() {
    if (!door.isClosed()) {       //Door cannot be locked if it's open
      System.out.println("Can't lock the door because it's open");
    } else {
      System.out.println("The door is already locked");
      door.setState(new LockedState(door));
    }
  }

  @Override
  public void unlock() {
    System.out.println("The door is already unlocked");
  }

  @Override
  public void unlockShortly() {
    System.out.println("The door is already unlocked, can't unlock shortly");
  }
}
