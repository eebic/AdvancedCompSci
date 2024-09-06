public class User {
    private String userName;
    private ToDoList toDoList;

    public User(String userName) {
        this.userName = userName;
        this.toDoList = new ToDoList();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ToDoList getToDoList() {
        return toDoList;
    }
}
