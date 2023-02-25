package ru.trosh.astontrainee.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.trosh.astontrainee.config.HibernateUtil;
import ru.trosh.astontrainee.domain.Speciality;

import java.util.List;

@Repository
public class SpecialityDAOImpl implements SpecialityDAO{

    public Session openSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public Speciality create(Speciality entity) {
        try (Session session = openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public Speciality selectById(Long id) {
        try (Session session = openSession()) {
            session.beginTransaction();
            Speciality speciality = session.load(Speciality.class, id);
            speciality.getWorkers().size();
            session.getTransaction().commit();
            return speciality;
        }
    }

    @Override
    public List<Speciality> selectAll() {
        try (Session session = openSession()) {
            return session.createQuery("SELECT s FROM Speciality s", Speciality.class).getResultList();
        }
    }

    @Override
    public Speciality update(Speciality entity) {
        try (Session session = openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = openSession()) {
            session.beginTransaction();
            Speciality speciality = new Speciality();
            speciality.setId(id);
            session.delete(speciality);
            session.getTransaction().commit();
        }
    }
}
