package baseNoStates;

import java.util.ArrayList;

public class UserGroup {
    private String name;
    private ArrayList<String> actions;
    private ArrayList<User> users;
    private ArrayList<Area> availableAreas;
    private ArrayList<Schedule> schedules;

    public UserGroup(String name, ArrayList<String> actions, ArrayList<User> users,
                     ArrayList<Area> availableAreas, ArrayList<Schedule> schedules) {
        this.name = name;
        this.actions = actions;
        this.users = users;
        this.availableAreas = availableAreas;
        this.schedules = schedules;
    }
}
