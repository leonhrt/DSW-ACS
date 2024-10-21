package baseNoStates;

import java.util.ArrayList;

public class DirectoryUserGroups {
    private static final ArrayList<UserGroup> userGroups = new ArrayList<>();

    // TODO: implement this
    public static void makeUserGroups() {
        userGroups.add(new UserGroup("Admin", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        userGroups.add(new UserGroup("Manager", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
        userGroups.add(new UserGroup("Employee", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }

    // TODO: implement this
    public static User findUserByCredential(String credential) {
        return null;
    }
}
