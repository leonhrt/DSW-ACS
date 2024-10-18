package baseNoStates;

public class UnlockedState implements DoorState{
  @Override
  public void open(Door door) {
    if(door.isClosed()){
      System.out.println("Opening the door...");
      door.setClosed(false);
    } else {
      System.out.println("The door is already open");
    }
  }

  @Override
  public void close(Door door) {
    if(!door.isClosed()){
      System.out.println("Closing the door...");
      door.setClosed(true);
    } else {
      System.out.println("The door is already closed");
    }
  }

  @Override
  public void lock(Door door) {
    if(!door.isClosed()){
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
  public String getStateName(){
    return "unlocked";
  }
}
