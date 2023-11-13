package org.danilskryl.javarush.dao;

import org.danilskryl.javarush.domain.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public TaskDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Task> findAllTasks(int offset, int limit) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Task", Task.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .list();
    }

    public Integer countAllTasks() {
        return Math.toIntExact(sessionFactory.getCurrentSession()
                .createQuery("SELECT COUNT(*) FROM Task", Long.class)
                .getSingleResult());
    }

    public Task findTaskById(int id) {
        return sessionFactory.getCurrentSession()
                .get(Task.class, id);
    }

    public Task saveTask(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(task);

        return task;
    }

    public void deleteTaskById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Task task = session.get(Task.class, id);
        if (task != null)
            session.remove(task);
        else
            throw new RuntimeException("Task with id " + id + " not found");
    }

    public Task updateTask(Task task) {
        return sessionFactory.getCurrentSession()
                .merge(task);
    }
}
