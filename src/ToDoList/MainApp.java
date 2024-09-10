import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    private static List<String> categories;

    static {
        categories = new ArrayList<>();
        categories.add("Work");
        categories.add("Personal");
        categories.add("Study");
        categories.add("Fitness");
    }

    public static void main(String[] args) {
        System.out.print("Welcome to the To-Do List App :)\n");
        System.out.print("To get started, please enter your name: ");
        String username = scanner.nextLine();
        User user = new User(username);

        // Initialize default categories
        for (String categoryName : categories) {
            user.getToDoList().addCategory(new Category(categoryName));
        }

        boolean running = true;
        while (running) {
            displayMenu(user);

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addTask(user);
                    break;
                case 2:
                    viewTasks(user);
                    break;
                case 3:
                    viewIncompleteTasks(user);
                    break;
                case 4:
                    editTask(user);
                    break;
                case 5:
                    markTaskAsComplete(user);
                    break;
                case 6:
                    sortTasksByCategory(user);
                    break;
                case 7:
                    clearConsole();
                    System.out.println("Bye bye! :)");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }

            if (option != 7) {
                System.out.print("\nPress enter to return to menu...");
                scanner.nextLine();
                clearConsole();
            }
        }
        scanner.close();
    }

    private static void displayMenu(User user) {
        clearConsole();
        System.out.println(user.getUserName() + "'s To-Do Menu");
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. View Incomplete Tasks");
        System.out.println("4. Edit Task");
        System.out.println("5. Mark Task as Complete");
        System.out.println("6. Sort Tasks by Category");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addTask(User user) {
        clearConsole();
        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();

        System.out.print("Enter due date (yyyy-mm-dd): ");
        String dateString = scanner.nextLine();
        LocalDate dueDate = LocalDate.parse(dateString, dateFormatter);

        System.out.println("Select a category:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
        System.out.print("Choose a category number: ");
        int categoryIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        String category = categories.get(categoryIndex - 1);

        Task task = new Task(taskName, dueDate);
        user.getToDoList().addTask(task, category);
        System.out.println("Task added under category: " + category);
    }

    private static void viewTasks(User user) {
    clearConsole();
    System.out.println("\nAll Tasks for " + user.getUserName() + ":");
    List<Task> tasks = user.getToDoList().getTasks();
    if (tasks.isEmpty()) {
        System.out.println("No tasks available.");
    } else {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}

private static void viewIncompleteTasks(User user) {
    clearConsole();
    System.out.println("\nIncomplete Tasks for " + user.getUserName() + ":");
    List<Task> tasks = user.getToDoList().getIncompleteTasks();
    if (tasks.isEmpty()) {
        System.out.println("No incomplete tasks.");
    } else {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}

    private static void editTask(User user) {
        clearConsole();
        viewTasks(user);
        System.out.print("Enter the number of the task you want to edit: ");
        int taskIndex = scanner.nextInt(); // 1-based index
        scanner.nextLine(); // Consume newline

        // Convert to 0-based index
        taskIndex -= 1;

        List<Task> tasks = user.getToDoList().getTasks();
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);

            System.out.print("Enter new task name (leave empty to keep current): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                task.setTaskName(newName);
            }

            System.out.print("Enter new due date (yyyy-MM-dd, leave empty to keep current): ");
            String newDateString = scanner.nextLine();
            if (!newDateString.isEmpty()) {
                LocalDate newDueDate = LocalDate.parse(newDateString, dateFormatter);
                task.setDueDate(newDueDate);
            }

            System.out.println("Task updated.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void markTaskAsComplete(User user) {
        clearConsole();
        viewTasks(user);
        System.out.print("Enter the number of the task you want to mark as complete: ");
        int taskIndex = scanner.nextInt(); // 1-based index
        scanner.nextLine(); // Consume newline

        // Convert to 0-based index
        taskIndex -= 1;

        List<Task> tasks = user.getToDoList().getTasks();
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task task = tasks.get(taskIndex);
            task.setCompleted(true);
            System.out.println("Task marked as complete.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void sortTasksByCategory(User user) {
        clearConsole();
        System.out.println("\nTasks Sorted by Category:");
        user.getToDoList().printTasksByCategory();
    }

    private static void clearConsole() {
        // System.out.print("\033[H\033[2J"); // For Unix-based systems
        System.out.print("\033[H\033[2J"); // For Windows
        System.out.flush();
    }
}
