import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ReportProcessor {
    public List<User> filterUsers(List<User> users, Predicate<User> predicate) {
        List<User> filteredUsers = new ArrayList<>();
        for (User user : users) {
            if (predicate.test(user)) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }

    public List<User> transformUsers(List<User> users, Function<User, User> transformer) {
        List<User> transformedUsers = new ArrayList<>();
        for (User user : users) {
            transformedUsers.add(transformer.apply(user));
        }
        return transformedUsers;
    }

    public List<User> sortUsers(List<User> users, Comparator<User> comparator) {
        List<User> sortedUsers = new ArrayList<>(users);
        sortedUsers.sort(comparator);
        return sortedUsers;
    }

    public Double aggregateUsers(List<User> users, Function<User, Double> aggregator) {
        double sumSalary = 0;
        for (User user : users) {
            sumSalary += aggregator.apply(user);
        }
        return sumSalary;
    }

    public Function<User, User> chainTransformations(Function<User, User>... transformers) {
        Function<User, User> chainedTransformations = user -> {
            for (Function<User, User> transformer : transformers) {
                user = transformer.apply(user);
            }
            return user;
        };
        return chainedTransformations;
    }


    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.addAll(List.of(
                new User("Ahmed", 25, 5000.0, "Cairo"),
                new User("Sara", 30, 6000.0, "Alex"),
                new User("Ali", 35, 7000.0, "Giza"),
                new User("Mona", 28, 8000.0, "Fayoum")
        ));
        ReportProcessor reportProcessor = new ReportProcessor();
        System.out.println(reportProcessor.filterUsers(users, user -> user.getAge() > 30));
        System.out.println(reportProcessor.transformUsers(users,
                user ->
                        new User("MR/ " + user.getName(), user.getAge() + 1,
                                user.getSalary(), user.getCity())));
        System.out.println(reportProcessor.aggregateUsers(users, User::getSalary));
        System.out.println(reportProcessor.sortUsers(users, Comparator.comparingInt(User::getAge)));
        System.out.println(reportProcessor.chainTransformations(
                user -> {
                    user.setSalary(user.getSalary() * 1.1);
                    return user;
                },
                user -> {
                    user.setCity(user.getCity().toUpperCase());
                    return user;
                }
        ).apply(new User("Ali", 35, 7000.0, "Giza")));
    }
}
