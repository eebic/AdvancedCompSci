import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToDoList {
    private List<Category> categories;

    public ToDoList() {
        this.categories = new ArrayList<>();
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void addTask(Task task, String categoryName) {
        for (Category category : categories) {
            if (category.getCategoryName().equals(categoryName)) {
                category.addTask(task);
                return;
            }
        }
        // Create a new category if it doesn't exist
        Category newCategory = new Category(categoryName);
        newCategory.addTask(task);
        categories.add(newCategory);
    }

    public List<Task> getTasks() {
        return categories.stream()
                         .flatMap(category -> category.getTasks().stream())
                         .collect(Collectors.toList());
    }

    public List<Task> getTasksByCategory(String categoryName) {
        return categories.stream()
                         .filter(category -> category.getCategoryName().equals(categoryName))
                         .flatMap(category -> category.getTasks().stream())
                         .collect(Collectors.toList());
    }

    public List<Task> getIncompleteTasks() {
        return getTasks().stream()
                         .filter(task -> !task.isCompleted())
                         .collect(Collectors.toList());
    }

    public void printTasks() {
        getTasks().forEach(System.out::println);
    }

    public void sortTasksByDueDate() {
        getTasks().sort((t1, t2) -> t1.getDueDate().compareTo(t2.getDueDate()));
    }

    public void printTasksByCategory() {
        for (Category category : categories) {
            System.out.println(category);
            for (Task task : category.getTasks()) {
                System.out.println("  " + task);
            }
        }
    }
}
