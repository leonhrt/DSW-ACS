package baseNoStates;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserGroup {
    private String name;
    private ArrayList<String> actions;
    private ArrayList<User> users;
    private ArrayList<Area> availableAreas;
    private Schedule schedule;

    public UserGroup(String name, ArrayList<String> actions, ArrayList<User> users,
                     ArrayList<Area> availableAreas, Schedule schedule) {
        this.name = name;
        this.actions = actions;
        this.users = users;
        this.availableAreas = availableAreas;
        this.schedule = schedule;
    }

    public boolean checkAllowedDateTime(LocalDateTime now) {
        return schedule.allowedDateTime(now);
    }
}
