package me.ronygomes.reference.springboot.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Note: If field named changed, need to update here also
// IDE can't help!
@JsonIgnoreProperties("internal2")
@JsonFilter("TaskInternal3Filter")
public class Task {
    
    private int id;
    private String task;
    private LocalDate dueDate;

    @JsonIgnore
    private String internal1;

    private String internal2;

    private String internal3;

    public Task() {
        this(0, "N/A", LocalDate.now());
    }

    public Task(int id, String task, LocalDate dueDate) {
        this.id = id;
        this.task = task;
        this.dueDate = dueDate;

        this.internal1 = "Internal 1";
        this.internal2 = "Internal 2";
        this.internal3 = "Internal 3";
    }

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getInternal1() {
        return internal1;
    }

    public String getInternal2() {
        return internal2;
    }

    public String getInternal3() {
        return internal3;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setInternal1(String internal1) {
        this.internal1 = internal1;
    }

    public void setInternal2(String internal2) {
        this.internal2 = internal2;
    }

    public void setInternal3(String internal3) {
        this.internal3 = internal3;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((task == null) ? 0 : task.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Task other = (Task) obj;
        if (id != other.id)
            return false;
        if (task == null) {
            if (other.task != null)
                return false;
        } else if (!task.equals(other.task))
            return false;
        return true;
    }
}
