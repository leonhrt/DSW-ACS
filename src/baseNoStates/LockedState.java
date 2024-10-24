package baseNoStates;

public class LockedState implements DoorState {
  @Override
  public void open(Door door) {
    System.out.println("Can't open the door, it's locked");
  }

  @Override
  public void close(Door door) {
    System.out.println("The door is already closed and locked");
  }

  @Override
  public void lock(Door door) {
    System.out.println("The door is already locked");
  }

  //as the actual state is locked, we set the door's state to
  //unlocked with a new UnlockedState
  @Override
  public void unlock(Door door) {
    System.out.println("Unlocking the door");
    door.setState(new UnlockedState());
  }

  @Override
  public void unlockShortly(Door door) {
    System.out.println("Unlocking the door for 10 seconds");
    door.setState(new UnlockShortlyState());
  }

  @Override
  public String getStateName() {
    return "locked";
  }
}
