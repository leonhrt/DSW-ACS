package baseNoStates.doors.doorstates;

import baseNoStates.doors.Door;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Observable;
import java.util.Observer;

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
    if (door.isClosed()) {
      System.out.println("Opening the door...");
      door.setClosed(false);
    } else {
      System.out.println("The door is already open");
    }
  }

  @Override
  public void close() {
    if (door.isClosed()) {
      System.out.println("The door is already closed");
    } else {
      System.out.println("Closing the door...");
      door.setClosed(true);
    }
  }

  @Override
  public void lock() {
    System.out.println("The door will be locked in 10 seconds automatically, be patient");
  }

  @Override
  public void unlock() {
    System.out.println("The door is unlocked temporarily, so can't unlock");
  }

  @Override
  public void unlockShortly() {
    System.out.println("The door is already in unlock shortly state");
  }

  @Override
  public void update(Observable o, Object arg) {
    LocalTime currentTime = clock.getDate().toLocalTime();
    int elapsedTime = (int) clockStartTime.until(currentTime, ChronoUnit.SECONDS);

    if (elapsedTime < 10) {
      return;
    }

    String state;
    if (door.isClosed()) {
      door.setState(new LockedState(door));
      state = States.LOCKED;
    } else {
      door.setState(new ProppedState(door));
      state = States.PROPPED;
    }

    System.out.println("The door " + door.getId() + " is now in " + state + " state");

    clock.deleteObserver(this);
  }
}
