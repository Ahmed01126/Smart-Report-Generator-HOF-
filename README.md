
# ReportProcessor

A Java utility to process and generate reports from a list of users using functional programming concepts like filtering, transformation, sorting, aggregation, and chaining transformations.

---

## Features

- **Filter users** based on custom conditions (e.g., age, city).
- **Transform users** by applying custom modifications (e.g., rename, increment age).
- **Sort users** by any attribute (e.g., age, salary).
- **Aggregate user data** (e.g., total salary).
- **Chain multiple transformations** to apply them sequentially.

---

## Project Structure

- `User` class: Represents a user with `name`, `age`, `salary`, and `city`.
- `ReportProcessor` class: Contains methods to process lists of users using functional interfaces.

---

## Usage Example

```java
List<User> users = List.of(
    new User("Ahmed", 25, 5000.0, "Cairo"),
    new User("Sara", 30, 6000.0, "Alex"),
    new User("Ali", 35, 7000.0, "Giza"),
    new User("Mona", 28, 8000.0, "Fayoum")
);

ReportProcessor reportProcessor = new ReportProcessor();

// Filter users older than 30
List<User> filtered = reportProcessor.filterUsers(users, user -> user.getAge() > 30);

// Transform user names and increment age
List<User> transformed = reportProcessor.transformUsers(users,
    user -> new User("MR/ " + user.getName(), user.getAge() + 1, user.getSalary(), user.getCity()));

// Aggregate total salary
Double totalSalary = reportProcessor.aggregateUsers(users, User::getSalary);

// Sort users by age ascending
List<User> sorted = reportProcessor.sortUsers(users, Comparator.comparingInt(User::getAge));

// Chain salary increase and city uppercase transformations on a single user
Function<User, User> chained = reportProcessor.chainTransformations(
    user -> {
        user.setSalary(user.getSalary() * 1.1);
        return user;
    },
    user -> {
        user.setCity(user.getCity().toUpperCase());
        return user;
    }
);

User updatedUser = chained.apply(new User("Ali", 35, 7000.0, "Giza"));
```

---

## How to Run

1. Compile the project:

```bash
javac User.java ReportProcessor.java
```

2. Run:

```bash
java ReportProcessor
```

---

## Requirements

- Java 8 or higher (for lambda and functional interface support).


