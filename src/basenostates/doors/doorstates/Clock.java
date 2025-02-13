package basenostates.doors.doorstates;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Clock class represents a singleton clock that provides the current date and time.
 * It extends an Observable, meaning it can notify observers whenever the time updates.
 *
 * <p>This clock runs at a regular interval, updating the current time and notifying its observers.
 * It can be started and stopped, and there is only one instance of the clock available throughout
 * the application (Singleton pattern).
 *
 * <p>It also is used in the Observer pattern as the Observable class.
 */
public class Clock extends Observable {
  // Variable used to store the current date and time
  private LocalDateTime date;
  // Timer used to schedule the updates
  private Timer timer;
  // Interval between updates in milliseconds
  private int period;
  // The unique instance of the clock (Singleton pattern).
  private static Clock uniqueInstance = null;

  private static final Logger milestone1 = LoggerFactory.getLogger("firstMilestone");

  /**
   * Private constructor to avoid instantiation from outside the class (Singleton pattern).
   * Initializes the clock with a period of 1 second between updates.
   */
  private Clock() {
    this.period = 1;
    timer = new Timer();
  }

  /**
   * Starts the clock, updating the current time every period seconds
   * and notifying all registered observers.
   */
  public void start() {
    TimerTask repeatedTask = new TimerTask() {
      public void run() {
        date = LocalDateTime.now();
        setChanged();          // Notify that the time has changed
        notifyObservers();    // Notify all observers that the time has changed
        milestone1.debug("run() executed at " + date);
      }
    };
    timer.scheduleAtFixedRate(repeatedTask, 0, 1000L * period);
  }

  /**
   * Stops the clock by canceling the timer.
   */
  public void stop() {
    timer.cancel();
  }

  /**
   * Returns the unique instance of the Clock.
   * If no instance exists, a new one is created.
   * This ensures that there is only one clock in the system.
   *
   * @return The singleton instance of the Clock
   */
  public static synchronized Clock getInstance() {
    if (uniqueInstance == null) {
      uniqueInstance = new Clock();
    }
    return uniqueInstance;
  }

  /**
   * Returns the current date and time managed by the clock.
   *
   * @return The current LocalDateTime
   */
  public LocalDateTime getDate() {
    return date;
  }
}
