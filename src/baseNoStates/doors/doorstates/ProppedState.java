package baseNoStates.doors.doorstates;

import baseNoStates.doors.Door;

public class ProppedState extends DoorState {
  public ProppedState(Door door) {
    super(door);
    name = States.PROPPED;
  }

  @Override
  public void open() {
    System.out.println("I mean, the door is open, but propped");
  }

  @Override
  public void close() {
    System.out.println("Closing the door...");
    door.setState(new LockedState(door));
    door.setClosed(true);
  }

  @Override
  public void lock() {
    System.out.println("Can't lock the door because it's propped");
  }

  @Override
  public void unlock() {
    System.out.println("It's propped right now, can't unlock");
  }

  @Override
  public void unlockShortly() {
    System.out.println("Can't unlock shortly because it's propped");
  }
}
