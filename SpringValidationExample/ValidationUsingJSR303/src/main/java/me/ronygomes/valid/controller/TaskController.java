package me.ronygomes.valid.controller;

import me.ronygomes.valid.domain.Task;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TaskController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String showForm(ModelMap model) {
        model.put("task", new Task());
        return "new";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String processTask(@Valid @ModelAttribute Task task,
                              BindingResult result) {
        if (!result.hasErrors()) {
            return "success";
        }
        return "new";
    }
}
