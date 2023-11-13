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

    public List<Task> getAllTasks() {
        try (Session session = sessionFactory.getCurrentSession()) {
            return session.createQuery("FROM Task", Task.class).list();
        }
    }

    public Task findTaskById(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            return session.get(Task.class, id);
        }
    }

    public Task saveTask(Task task) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            session.persist(task);
            session.getTransaction().commit();

            return task;
        }
    }

    public void deleteTaskById(int id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            Task task = session.get(Task.class, id);
            session.remove(task);
            session.getTransaction().commit();
        }
    }

    public Task updateTask(Task task) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            session.merge(task);
            session.getTransaction().commit();

            return task;
        }
    }
}
