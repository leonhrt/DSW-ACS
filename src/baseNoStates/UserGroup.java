package baseNoStates;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UserGroup {
    public String name;
    public Set<String> actions;
    public ArrayList<User> users;
    public ArrayList<Space> availableSpaces;
    public Schedule schedule;

    public UserGroup(String name, Set<String> actions, ArrayList<User> users,
                     ArrayList<Space> availableSpaces, Schedule schedule) {
        this.name = name;
        this.actions = actions;
        this.users = users;
        this.availableSpaces = availableSpaces;
        this.schedule = schedule;
    }

    public UserGroup(String name) {
        this.name = name;
        this.actions = new HashSet<>();
        this.availableSpaces = new ArrayList<>();
        this.users = new ArrayList<>();
        this.schedule = new Schedule();
    }

    public UserGroup(String name, Set<String> actions, ArrayList<Space> availableSpaces,
                     Schedule schedule) {
        this.name = name;
        this.actions = actions;
        this.availableSpaces = availableSpaces;
        this.users = new ArrayList<>();
        this.schedule = schedule;
    }

    public boolean checkAllowedDateTime(LocalDateTime now) {
        return schedule.allowedDateTime(now);
    }

    public boolean canDoAction(String action) {
        return actions.contains(action);
    }

    public boolean userCanBeInSpace(Space space) {
        return availableSpaces.contains(space);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public String getName() {
        return name;
    }
}
