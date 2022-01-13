package datastructures.statistic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int changed = 0;
        int deleted = 0;

        Map<Integer, String> currentMap = new HashMap<>();
        for (User user : current) {
            currentMap.put(user.getId(), user.getName());
        }

        for (User user : previous) {
            String currentUserName = currentMap.get(user.getId());
            if (currentUserName != null && Objects.equals(currentUserName, user.getName())) {
                currentMap.remove(user.getId());
            } else if (currentUserName != null && !Objects.equals(currentUserName, user.getName())) {
                changed += 1;
                currentMap.remove(user.getId());
            } else if (currentUserName == null)  {
                deleted += 1;
            }
        }

        int added = currentMap.size();
        return  new Info(added, changed, deleted);
    }
}
