package datastructures.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar birthDay = new GregorianCalendar(1984, Calendar.OCTOBER, 20, 18, 30, 0);
        User firstUser = new User("First", 2, birthDay);
        User secondUser = new User("First", 2, birthDay);

        HashMap<User, Object> map = new HashMap<User, Object>();
        map.put(firstUser, new Object());
        map.put(secondUser, new Object());

        System.out.println(map);

        User s1 = null;
        User s2 = null;

        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s1.equals(s2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + '}';
    }
}
