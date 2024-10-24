package baseNoStates.doors.doorstates;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends Observable {
    private LocalDateTime date;
    private Timer timer;
    private int period;
    private static Clock uniqueInstance = null;

    private Clock() {
        this.period = 1;
        timer = new Timer();
    }

    public void start() {
        TimerTask repeatedTask = new TimerTask() {
          public void run() {
              date = LocalDateTime.now();
              setChanged();
              notifyObservers();
              System.out.println("run() executed at " + date);
          }
        };
        timer.scheduleAtFixedRate(repeatedTask, 0 , 1000L * period);
    }

    public void stop() {
        timer.cancel();
    }

    public static synchronized Clock getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Clock();
        }
        return uniqueInstance;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
