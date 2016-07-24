package me.ronygomes.valid.validator;

import me.ronygomes.valid.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.Set;

@Component
public class TaskValidator implements Validator {

    @Autowired
    private javax.validation.Validator validator;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Task.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Task task = (Task) target;
        Set<ConstraintViolation<Task>> violations = validator.validate(task);

        for (ConstraintViolation<Task> violation : violations) {
            Path path = violation.getPropertyPath();
            errors.rejectValue(path.iterator().next().getName(), "size.between.limit", new Object[]{"5", "100"}, "");
        }

        long startDate = task.getStartDate().getTime();
        long endDate = task.getEndDate().getTime();

        if (endDate < startDate) {
            errors.reject("end.date.before.start.date");
        }
    }
}
