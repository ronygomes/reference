package me.ronygomes.reference.springboot;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

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
    public MappingJacksonValue retrieveTask(@PathVariable int id,
                                            @RequestParam(defaultValue = "false") boolean showInternal3) {

        Task task = taskDao.getTaskById(id);
        if (task == null) {
            throw new RecordNotFoundException("Unable to find Task with id: " + id);
        }

        MappingJacksonValue mjv = new MappingJacksonValue(task);
        mjv.setFilters(createFilter(showInternal3));

        return mjv;
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

    private FilterProvider createFilter(boolean showInternal3) {
        Set<String> p = new HashSet<>(Arrays.asList("id", "task", "dueDate"));
        if (showInternal3) {
            p.add("internal3");
        }
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(p);
        FilterProvider filters = new SimpleFilterProvider().addFilter("TaskInternal3Filter", filter);
        return filters;
    }
}
