import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
    private LocalDateTime date;
    private Timer timer;
    private int period;
    private static Clock uniqueInstance = null;

    private Clock(int period) {
        this.period = period;
        timer = new Timer();
    }

    public void start() {
        TimerTask repeatedTask = new TimerTask() {
          public void run() {
              date = LocalDateTime.now();
              System.out.println("run() executed at " + date);
          }
        };
        timer.scheduleAtFixedRate(repeatedTask, 0 , 1000 * period);
    }

    public void stop() {
        timer.cancel();
    }

    public static synchronized Clock getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Clock(1);
        }
        return uniqueInstance;
    }

    public int getPeriod() {
        return period;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
