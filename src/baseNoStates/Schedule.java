package baseNoStates;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalTime fromHour;
    private LocalTime toHour;
    private DayOfWeek[] weekdays;

    public Schedule(LocalDate fromDate, LocalDate toDate, LocalTime fromHour, LocalTime toHour, DayOfWeek[] weekdays) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromHour = fromHour;
        this.toHour = toHour;
        this.weekdays = weekdays;
    }
}
