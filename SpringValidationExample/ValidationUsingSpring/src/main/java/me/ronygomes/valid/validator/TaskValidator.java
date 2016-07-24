package me.ronygomes.valid.validator;

import me.ronygomes.valid.domain.Task;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TaskValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Task.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Task task = (Task) target;
        long startDate = task.getStartDate().getTime();
        long endDate = task.getEndDate().getTime();

        if (endDate < startDate) {
            errors.reject("end.date.before.start.date");
        }
    }
}
