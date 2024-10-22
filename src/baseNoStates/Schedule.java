package baseNoStates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Schedule {
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalTime fromHour;
    private LocalTime toHour;
    private ArrayList<DayOfWeek> weekdays;

    public Schedule(LocalDate fromDate, LocalDate toDate, LocalTime fromHour, LocalTime toHour,
                    ArrayList<DayOfWeek> weekdays) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromHour = fromHour;
        this.toHour = toHour;
        this.weekdays = weekdays;
    }

    // Temporal constructor
    public Schedule(){}

    public boolean allowedDateTime(LocalDateTime now) {
        return checkDate(now.toLocalDate()) &&
                checkTime(now.toLocalTime()) &&
                checkWeekday(now.getDayOfWeek());
    }

    private boolean checkDate(LocalDate now) {
        return (now.isAfter(fromDate) || now.equals(fromDate)) &&
                (now.isBefore(toDate) || now.equals(toDate));
    }

    private boolean checkTime(LocalTime now) {
        return (now.isAfter(fromHour) || now.equals(fromHour)) &&
                (now.isBefore(toHour) || now.equals(toHour));
    }

    private boolean checkWeekday(DayOfWeek now) {
        return weekdays.contains(now);
    }
}
