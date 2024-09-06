import java.time.LocalDate;

public class Task {
    private String taskName;
    private LocalDate dueDate;
    private boolean isCompleted;

    public Task(String taskName, LocalDate dueDate) {
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String toString() {
        return taskName + " (Due: " + dueDate + ", Completed: " + isCompleted + ")";
    }
}
