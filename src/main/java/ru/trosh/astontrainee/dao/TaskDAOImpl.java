package ru.trosh.astontrainee.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.trosh.astontrainee.config.HibernateUtil;
import ru.trosh.astontrainee.domain.Task;
import ru.trosh.astontrainee.domain.Worker;

import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO{

    public Session openSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public Task create(Task entity) {
        try (Session session = openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public Task selectById(Long id) {
        try (Session session = openSession()) {
            session.beginTransaction();
            Task task = session.load(Task.class, id);
            task.getWorkers().size();
            session.getTransaction().commit();
            return task;
        }
    }

    @Override
    public List<Task> selectAll() {
        try (Session session = openSession()) {
            return session.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        }
    }

    @Override
    public Task update(Task entity) {
        try (Session session = openSession()) {
            session.beginTransaction();
            Task task = session.load(Task.class, entity.getId());
            task.setTitle(entity.getTitle());
            task.setDescription(entity.getDescription());
            task.setDepartment(entity.getDepartment());
            task.getWorkers().size();
            session.update(task);
            session.getTransaction().commit();
            return task;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = openSession()) {
            session.beginTransaction();
            Task task = new Task();
            task.setId(id);
            session.delete(task);
            session.getTransaction().commit();
        }
    }

    @Override
    public void addTaskToWorker(Long taskId, Long workerId) {
        try (Session session = openSession()) {
            session.beginTransaction();
            Task task = session.load(Task.class, taskId);
            Worker worker = session.load(Worker.class, workerId);
            task.getWorkers().add(worker);
            session.update(task);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteTaskFromWorker(Long taskId, Long workerId) {
        try (Session session = openSession()) {
            session.beginTransaction();
            Task task = session.get(Task.class, taskId);
            Worker worker = session.get(Worker.class, workerId);
            task.getWorkers().remove(worker);
            session.update(task);
            session.getTransaction().commit();
        }
    }
}
