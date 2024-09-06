import java.util.ArrayList;
import java.util.List;

public class Category {
    private String categoryName;
    private List<Task> tasks;

    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.tasks = new ArrayList<>();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    @Override
    public String toString() {
        return categoryName + " (" + tasks.size() + " tasks)";
    }
}
