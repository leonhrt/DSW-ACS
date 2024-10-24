package baseNoStates.users.user_groups;

import baseNoStates.areas.Area;
import baseNoStates.areas.DirectoryAreas;
import baseNoStates.areas.Space;
import baseNoStates.doors.doorstates.Actions;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class DirectoryUserGroups {
  private static final ArrayList<UserGroup> userGroups = new ArrayList<>();

  public static void makeUserGroups() {
    // All the available areas of the ACS
    ArrayList<Area> availableAreas = DirectoryAreas.getAllAreas();

    // All the spaces of the available areas
    ArrayList<Space> availableSpaces = new ArrayList<>();
    for (Area area : availableAreas) {
      if (area instanceof Space) {
        availableSpaces.add((Space) area);
      }
    }

    // All the actions available
    Set<String> allActions = new HashSet<>(List.of(
        Actions.LOCK,
        Actions.UNLOCK,
        Actions.UNLOCK_SHORTLY,
        Actions.OPEN,
        Actions.CLOSE)
    );

    // Administrator: Can do all actions
    Set<String> adminActions = new HashSet<>(allActions);

    // Administrator: Can access all the spaces
    ArrayList<Space> adminSpaces = new ArrayList<>(availableSpaces);

    // Administrator: The schedule. This group always has access at any moment
    Schedule adminSchedule = new Schedule(
        LocalDate.of(2024, 1, 1), // From January 1st 2024
        LocalDate.of(2100, 1, 1), // To January 1st 2100
        LocalTime.of(0, 0),                 // From 0:00h
        LocalTime.of(23, 59),               // To 23:59h
        new HashSet<>(List.of(DayOfWeek.values()))      // All days of the week
    );

    // Manager: Can do all actions
    Set<String> managerActions = new HashSet<>(allActions);

    // Manager: Can access all the spaces
    ArrayList<Space> managerSpaces = new ArrayList<>(availableSpaces);

    // Manager: The schedule.
    // This group has access from September 1st 2024 to March 1st 2025.
    // All weekdays plus Saturday.
    // From 8:00h to 20:00h.
    Schedule managerSchedule = new Schedule(
        LocalDate.of(2024, 9, 1),     // From September 1st 2024
        LocalDate.of(2025, 3, 1),     // To March 1st 2025
        LocalTime.of(8, 0),                     // From 8:00h
        LocalTime.of(20, 0),                    // To 20:00h
        // Weekdays + Saturday
        new HashSet<>(EnumSet.complementOf(EnumSet.of(DayOfWeek.SUNDAY)))
    );

    // Employee: Can open, close and unlock shortly
    Set<String> employeeActions = new HashSet<>(allActions);
    employeeActions.removeAll(List.of(Actions.LOCK, Actions.UNLOCK));

    // Employee: Can access all the spaces but the parking
    ArrayList<Space> employeeSpaces = new ArrayList<>(availableSpaces);
    employeeSpaces.remove((Space) DirectoryAreas.findAreaById("parking"));

    // Employee: The schedule.
    // This group has access from September 1st 2024 to March 1st 2025.
    // All weekdays.
    // From 9:00h to 17:00h.
    Schedule employeeSchedule = new Schedule(
        LocalDate.of(2024, 9, 1),     // From September 1st 2024
        LocalDate.of(2025, 3, 1),     // To March 1st 2025
        LocalTime.of(9, 0),                     // From 9:00h
        LocalTime.of(17, 0),                    // To 17:00h
        // Weekdays
        new HashSet<>(EnumSet.complementOf(
            EnumSet.of(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY)
        ))
    );

    userGroups.add(new UserGroup("Administrator", adminActions, adminSpaces, adminSchedule));
    userGroups.add(new UserGroup("Manager", managerActions, managerSpaces, managerSchedule));
    userGroups.add(new UserGroup("Employee", employeeActions, employeeSpaces, employeeSchedule));
    userGroups.add(new UserGroup("Blank"));
  }

  public static UserGroup getUserGroupByName(String name) {
    for (UserGroup userGroup : userGroups) {
      if (userGroup.getName().equals(name)) {
        return userGroup;
      }
    }
    return null;
  }
}
