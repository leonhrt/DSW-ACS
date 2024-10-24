package baseNoStates;

public class UnlockShortlyState implements DoorState {
  @Override
  public void open(Door door) {
    System.out.println("Oppening the door...");
    door.setClosed(false);
  }

  @Override
  public void close(Door door) {
    System.out.println("Closing the door...");
    door.setClosed(true);
  }

  @Override
  public void lock(Door door) {
    System.out.println("The door will be locked in 10 seconds automatically, be patient");
  }

  @Override
  public void unlock(Door door) {
    System.out.println("The door is unlocked temporarily");
  }

  @Override
  public void unlockShortly(Door door) {
    System.out.println("The door is already in unlock shortly state");
  }

  @Override
  public String getStateName() {
    return "unlocked_shortly";
  }
}
