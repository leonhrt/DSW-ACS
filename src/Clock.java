import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
    private LocalDateTime date;
    private Timer timer;
    private int period;

    public Clock(int period) {
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

    public int getPeriod() {
        return period;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
