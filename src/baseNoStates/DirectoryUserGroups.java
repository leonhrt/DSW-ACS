package baseNoStates;

import java.util.ArrayList;

public class DirectoryUserGroups {
    private static final ArrayList<UserGroup> userGroups = new ArrayList<>();

    // TODO: implement this
    public static void makeUserGroups() {
        userGroups.add(new UserGroup("Admin", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Schedule()));
        userGroups.add(new UserGroup("Manager", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Schedule()));
        userGroups.add(new UserGroup("Employee", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Schedule()));
        userGroups.add(new UserGroup("Blank", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new Schedule()));
    }

    // TODO: implement this
    public static User findUserByCredential(String credential) {
        return null;
    }
}
