package me.ronygomes.reference.springboot;

import java.net.URI;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.ronygomes.reference.springboot.dao.TaskDao;
import me.ronygomes.reference.springboot.domain.Task;

@RestController
public class TaskResource {
    
    private TaskDao taskDao;

    public TaskResource(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @GetMapping("/tasks")
    public Collection<Task> retrieveAllTasks() {
        return taskDao.getAllTask();
    }

    @GetMapping("/tasks/{id}")
    public Task retrieveTask(@PathVariable int id) {
        Task task = taskDao.getTaskById(id);
        if (task == null) {
            throw new RecordNotFoundException("Unable to find Task with id: " + id);
        }

        return task;
    }
    
    @PostMapping("/tasks")
	public ResponseEntity<Task> createUser(@RequestBody Task task) {
		taskDao.saveTask(task);;
    
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(task.getId())
						.toUri();   

		return ResponseEntity.created(location).build();
	}

    @DeleteMapping("/tasks/{id}")
	public void deleteUser(@PathVariable int id) {
		taskDao.deleteById(id);
	}
}
