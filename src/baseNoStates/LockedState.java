package baseNoStates;

public class LockedState extends DoorState {
  public LockedState(Door door) {
    super(door);
    name = States.LOCKED;
  }

  @Override
  public void open() {
    System.out.println("Can't open the door, it's locked");
  }

  @Override
  public void close() {
    System.out.println("The door is already closed and locked");
  }

  @Override
  public void lock() {
    System.out.println("The door is already locked");
  }

  //as the actual state is locked, we set the door's state to
  //unlocked with a new UnlockedState
  @Override
  public void unlock() {
    System.out.println("Unlocking the door");
    door.setState(new UnlockedState(door));
  }

  @Override
  public void unlockShortly() {
    System.out.println("Unlocking the door for 10 seconds");
    door.setState(new UnlockShortlyState(door));
  }
}
