package baseNoStates;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Observable;
import java.util.Observer;
import java.time.Duration;

public class UnlockShortlyState extends DoorState implements Observer {
  private LocalTime clockStartTime;
  private Clock clock;

  public UnlockShortlyState(Door door) {
    super(door);
    name = States.UNLOCKED_SHORTLY;
    clock = Clock.getInstance();
    clock.addObserver(this);
    clockStartTime = LocalTime.now();
  }

  @Override
  public void open() {
    System.out.println("Oppening the door...");
    door.setClosed(false);
  }

  @Override
  public void close() {
    System.out.println("Closing the door...");
    door.setClosed(true);
  }

  @Override
  public void lock() {
    System.out.println("The door will be locked in 10 seconds automatically, be patient");
  }

  @Override
  public void unlock() {
    System.out.println("The door is unlocked temporarily");
  }

  @Override
  public void unlockShortly() {
    System.out.println("The door is already in unlock shortly state");
  }

  @Override
  public void update(Observable o, Object arg) {
    LocalTime currentTime = clock.getDate().toLocalTime();
    int elapsedTime = (int) clockStartTime.until(currentTime, ChronoUnit.SECONDS);

    /*if (elapsedTime >= 10) {
      if (this.isClosed()) {
        door.setState(new LockedState());
      } else {
        door.setState(new ProppedState());
      }
      clock.deleteObserver(this);
    }*/
  }
}
