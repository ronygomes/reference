package me.ronygomes.reference.springboot.dao;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import me.ronygomes.reference.springboot.domain.Task;

@Component
public class TaskDao {
    
    private static int idCount = 1;

    private static Map<Integer, Task> DATABASE = new HashMap<>();

    static {
        DATABASE.put(idCount, new Task(idCount++, "Task 1", LocalDate.now()));
        DATABASE.put(idCount, new Task(idCount++, "Task 2", LocalDate.now()));
        DATABASE.put(idCount, new Task(idCount++, "Task 3", LocalDate.now()));
    }

    public Collection<Task> getAllTask() {
        return DATABASE.values();
    }

    public Task getTaskById(int id) {
        return DATABASE.get(id);
    }

    public void saveTask(Task task) {
        task.setId(idCount);
        DATABASE.put(idCount++, task);
    }

    public void deleteById(int id) {
        DATABASE.remove(id);
    }
}
