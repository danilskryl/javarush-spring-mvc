package org.danilskryl.javarush.service;

import org.danilskryl.javarush.domain.Task;

import java.util.List;

public interface TaskService {
    Task findTask(int id);

    List<Task> findTasks(int offset, int limit);
    Integer countAllTasks();

    Task saveTask(Task task);

    void deleteTask(int id);

    Task updateTask(Task task);
}
