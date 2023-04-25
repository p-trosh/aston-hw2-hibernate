package ru.trosh.astontrainee.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.trosh.astontrainee.config.HibernateUtil;
import ru.trosh.astontrainee.domain.Worker;

import java.util.List;

@Repository
public class WorkerDAOImpl implements WorkerDAO{

    public Session openSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public Worker create(Worker entity) {
        try (Session session = openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public Worker selectById(Long id) {
        try (Session session = openSession()) {
            session.beginTransaction();
            Worker worker = session.load(Worker.class, id);
            worker.getTasks().size();
            session.getTransaction().commit();
            return worker;
        }
    }

    @Override
    public List<Worker> selectAll() {
        try (Session session = openSession()) {
            return session.createQuery("SELECT w FROM Worker w", Worker.class).getResultList();
        }
    }

    @Override
    public Worker update(Worker entity) {
        try (Session session = openSession()) {
            session.beginTransaction();
            Worker worker = session.load(Worker.class, entity.getId());
            worker.setFirstName(entity.getFirstName());
            worker.setLastName(entity.getLastName());
            worker.setSpeciality(entity.getSpeciality());
            worker.setDepartment(entity.getDepartment());
            worker.getTasks().size();
            session.update(worker);
            session.getTransaction().commit();
            return worker;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = openSession()) {
            session.beginTransaction();
            Worker worker = new Worker();
            worker.setId(id);
            session.delete(worker);
            session.getTransaction().commit();
        }
    }
}
