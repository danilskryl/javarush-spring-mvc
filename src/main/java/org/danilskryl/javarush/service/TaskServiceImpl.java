package org.danilskryl.javarush.service;

import org.danilskryl.javarush.dao.TaskDAO;
import org.danilskryl.javarush.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskDAO taskDAO;

    @Autowired
    public TaskServiceImpl(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public List<Task> getTasks() {
        return taskDAO.getAllTasks();
    }

    public Task getTask(int id) {
        return taskDAO.findTaskById(id);
    }
}
