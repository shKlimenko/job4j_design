package statistic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;

        Map<Integer, String> currentMap = new HashMap<>();
        for (User user : current) {
            currentMap.put(user.getId(), user.getName());
        }

        for (User user : previous) {
            String currentUserName = currentMap.get(user.getId());
            if (currentUserName != null && currentUserName.equals(user.getName())) {
                currentMap.remove(user.getId());
            } else if (currentUserName != null && !currentUserName.equals(user.getName())) {
                changed += 1;
            } else if (currentUserName == null)  {
                deleted += 1;
            }
        }

        added = currentMap.size();
        Info info = new Info(added, changed, deleted);
        System.out.println(added + " " + changed + " " + deleted);
        return  info;
    }
}
