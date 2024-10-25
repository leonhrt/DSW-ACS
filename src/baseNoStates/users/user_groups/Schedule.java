package baseNoStates.users.user_groups;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * The Schedule class represents a schedule for a user group that defines the time periods where the group
 * is allowed to perform actions.
 * The periods are defined by a start date, end date, start time, end time, and a set of weekdays.
 */
public class Schedule {
  private LocalDate fromDate;
  private LocalDate toDate;
  private LocalTime fromHour;
  private LocalTime toHour;
  private Set<DayOfWeek> weekdays;

  /**
   * Constructs a Schedule with the specified dates, times, and weekdays.
   *
   * @param fromDate The start date of the schedule
   * @param toDate The end date of the schedule
   * @param fromHour The start time each day for the schedule
   * @param toHour The end time each day for the schedule
   * @param weekdays The set of valid weekdays for the schedule
   */
  public Schedule(LocalDate fromDate, LocalDate toDate, LocalTime fromHour, LocalTime toHour,
                  Set<DayOfWeek> weekdays) {
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.fromHour = fromHour;
    this.toHour = toHour;
    this.weekdays = weekdays;
  }

  /**
   * Constructs an empty Schedule.
   */
  public Schedule() {
    weekdays = new HashSet<>();
  }

  /**
   * Determines if the provided LocalDateTime falls within the schedule's defined date, time,
   * and weekday ranges.
   *
   * @param now The current LocalDateTime to check against the schedule
   *
   * @return True if the provided date and time are allowed by the schedule, false otherwise.
   */
  public boolean allowedDateTime(LocalDateTime now) {
    return checkDate(now.toLocalDate()) &&
        checkTime(now.toLocalTime()) &&
        checkWeekday(now.getDayOfWeek());
  }

  /**
   * Checks if the given date falls within the schedule's date range.
   *
   * @param now The date to check
   *
   * @return True if the date is within the valid range, false otherwise
   */
  private boolean checkDate(LocalDate now) {
    return (now.isAfter(fromDate) || now.equals(fromDate)) &&
        (now.isBefore(toDate) || now.equals(toDate));
  }

  /**
   * Checks if the given time falls within the schedule's time range for a day.
   *
   * @param now The time to check
   *
   * @return True if the time is within the valid range, false otherwise.
   */
  private boolean checkTime(LocalTime now) {
    return (now.isAfter(fromHour) || now.equals(fromHour)) &&
        (now.isBefore(toHour) || now.equals(toHour));
  }

  /**
   * Checks if the given weekday is within the valid weekdays of the schedule.
   *
   * @param now The weekday to check
   * @return True if the weekday is allowed, false otherwise
   */
  private boolean checkWeekday(DayOfWeek now) {
    return weekdays.contains(now);
  }
}
