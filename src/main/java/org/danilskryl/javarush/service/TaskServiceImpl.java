package org.danilskryl.javarush.service;

import org.danilskryl.javarush.dao.TaskDAO;
import org.danilskryl.javarush.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskDAO taskDAO;

    @Autowired
    public TaskServiceImpl(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findTasks(int offset, int limit) {
        return taskDAO.findAllTasks(offset, limit);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer countAllTasks() {
        return taskDAO.countAllTasks();
    }

    @Override
    @Transactional(readOnly = true)
    public Task findTask(int id) {
        return taskDAO.findTaskById(id);
    }

    @Override
    @Transactional
    public Task saveTask(Task task) {
        return taskDAO.saveTask(task);
    }

    @Override
    @Transactional
    public void deleteTask(int id) {
        taskDAO.deleteTaskById(id);
    }

    @Override
    @Transactional
    public Task updateTask(Task task) {
        return taskDAO.updateTask(task);
    }
}
