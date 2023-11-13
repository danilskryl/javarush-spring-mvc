package org.danilskryl.javarush.controller;

import org.danilskryl.javarush.domain.Task;
import org.danilskryl.javarush.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public String allTasks(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
                           Model model) {
        List<Task> tasks = service.findTasks((page - 1) * limit, limit);
        model.addAttribute("allTasks", tasks);

        int totalPages = (int) Math.ceil(1.0 * service.countAllTasks() / limit);
        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("currentPage", page);

        return "all-tasks-page";
    }

    @GetMapping("/{id}/edit")
    public String editTaskForm(@PathVariable int id, Model model) {
        model.addAttribute("editTask", service.findTask(id));

        return "task-edit-page";
    }

    @GetMapping("/new")
    public String newTaskForm(Model model) {
        model.addAttribute("newTask", new Task());

        return "new-task-form-page";
    }

    @PostMapping
    public String addTask(@ModelAttribute("newTask") Task task) {
        service.saveTask(task);

        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id) {
        service.deleteTask(id);

        return "redirect:/tasks";
    }

    @PutMapping
    public String updateTask(@ModelAttribute("editTask") Task task) {
        service.updateTask(task);

        return "redirect:/tasks";
    }
}
