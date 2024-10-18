package baseNoStates;

public class UnlockedState implements DoorState {
  @Override
  public void open(Door door) {
    if (door.isClosed()) {        //Check if the door is closed so it can be opened
      System.out.println("Opening the door...");
      door.setClosed(false);
    } else {
      System.out.println("The door is already open");
    }
  }

  @Override
  public void close(Door door) {
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
  public void lock(Door door) {
    if (!door.isClosed()) {       //Door cannot be locked if it's open
      System.out.println("Can't lock the door because it's open");
    } else {
      System.out.println("The door is already locked");
      door.setState(new LockedState());
    }
  }

  @Override
  public void unlock(Door door) {
    System.out.println("The door is already unlocked");
  }

  @Override
  public String getStateName() {
    return "unlocked";
  }
}
